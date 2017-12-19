package com.sun.amr.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sun.amr.dao.IDetailsDAO;
import com.sun.amr.dao.IEmpDAO;
import com.sun.amr.dao.IPurchaseDAO;
import com.sun.amr.dao.IResDAO;
import com.sun.amr.service.IPurchaseService;
import com.sun.amr.service.abs.AbstractService;
import com.sun.amr.vo.Details;
import com.sun.amr.vo.Emp;
import com.sun.amr.vo.Page;
import com.sun.amr.vo.Purchase;
import com.sun.amr.vo.Res;
import com.sun.util.MathUtil;
@Service
public class PurchaseServiceImpl extends AbstractService implements IPurchaseService{
	@Resource
	private IDetailsDAO detailsDAO;
	@Resource
	private IPurchaseDAO purchaseDAO;
	@Resource
	private IEmpDAO empDAO;
	@Resource
	private IResDAO resDAO;
	@Override
	public boolean add(int eid, Purchase vo) throws Exception {
		if (!super.checkAuth( eid, 30)) {//Ȩ����֤30��
			return false;
		}
		//��ѯ����ǰ�û������д����嵥��Ϣ��Ŀ���������������嵥��Ϣ
		List<Details> allDetails=this.detailsDAO.findAllDetails(eid);
		if (allDetails.size()==0) {//���û�оͷ���false
			return false;
		}
		double sum=0.0;//�����ܽ��ı���
		Iterator<Details> iter=allDetails.iterator();
		while(iter.hasNext()) {
			Details details=iter.next();
			//�����ܽ��
			sum+=details.getPrice()*details.getAmount();
		}
		if (sum<0.0) {
			return false;
		}
		vo.setEmp(new Emp());  //������ǰ�û��Ķ��������ˣ�
		vo.getEmp().setEid(eid);  //���������˵ı��
		vo.setPubdate(new Date());
		vo.setStatus(0);
		vo.setTotal(MathUtil.round(sum, 2));//�������룬������λС��
		if (this.purchaseDAO.doCreate(vo)>0) {
			//����޸ĳɹ��Զ�����pid
			return this.detailsDAO.doUpdateByPurchase(vo.getPid(), eid)>0;
		}
		return false;
	}
	@Override
	public Map<String, Object> listByEmp(int eid, int currentPage, int lineSize) throws Exception {
		if (!super.checkAuth(eid, 27)) {//��֤Ȩ��
			return null;
		}
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		//�����ѯ����������sql����л�ȡ
		paramMap.put("eid", eid);
		paramMap.put("start", (currentPage-1)*lineSize);
		paramMap.put("lineSize", lineSize);
		//�����������뵥����Ϣ
		map.put("allPurchases", this.purchaseDAO.findAllByEmp(paramMap));
		//ͳ��������
		Integer count=this.purchaseDAO.getAllCountByEmp(eid);
		Integer allPages=count/lineSize+(count%lineSize==0?0:1);
		Page page=new Page(currentPage,allPages);
		page.setCount(count);
		map.put("pager", page);
		return map;
	}
	@Override
	public Purchase getByEmp(int eid, int pid) throws Exception {
		if (!super.checkAuth(eid, 27)) {//��֤Ȩ��
			return null;
		}
		Purchase purchase=this.purchaseDAO.findByIdAndEmp(pid, eid);
		if (purchase!=null) {
			//�����뵥�Ĵ����嵥��Ϣ���浽���뵥��������
			purchase.setAllDetails(this.detailsDAO.findAllByPurchase(pid));
			//��ѯ�����˵���Ϣ
			purchase.setEmp(this.empDAO.findById(purchase.getEmp().getEid()));
		}
		return purchase;
	}
	@Override
	public Map<String, Object> listSimple(int eid, int currentPage, int lineSize) throws Exception {
		if (!super.checkAuth(eid, 41)) {//��֤Ȩ��
			return null;
		}
		Map<String, Object> map=new HashMap<String,Object>();
		int start=(currentPage-1)*lineSize;
		//�����������뵥����Ϣ
		map.put("allPurchases", this.purchaseDAO.findAllSimpleSplit(start, lineSize));
		//ͳ��������
		Integer count=this.purchaseDAO.getAllCountSimple();
		Integer allPages=count/lineSize+(count%lineSize==0?0:1);
		Page page=new Page(currentPage,allPages);
		page.setCount(count);
		map.put("pager", page);
		return map;
	}
	@Override
	public Purchase show(int eid, int pid,int actid) throws Exception {
		if (!super.checkAuth(eid, actid)) {
			return null;
		}
		//���뵥��Ϣ����
		Purchase purchase=null;
		if (actid==27) {
			purchase=this.purchaseDAO.findByIdAndEmp(pid, eid);
		}else if (actid==41) {
			purchase=this.purchaseDAO.findById(pid);
		}
		if (purchase!=null) {
			//�����뵥�Ĵ����嵥��Ϣ���浽���뵥��������
			purchase.setAllDetails(this.detailsDAO.findAllByPurchase(pid));
			//��ѯ�����˵���Ϣ
			purchase.setEmp(this.empDAO.findById(purchase.getEmp().getEid()));
		}
		return purchase;
	}
	
	
	@Override
	public boolean editStatus(int eid, int pid, int status) throws Exception {
		if (!super.checkAuth1(eid, 4, 5, 42)) {//�漰�ʽ�Ĳ�������֤Ҫ�ϸ�
			System.out.println("!super.checkAuth1(eid, 4, 5, 42)");
			return false;
		}
		if (this.purchaseDAO.doUpdateStatus(pid, status, eid)>0) {
			if (status==1) {//��ʾ���ͨ��
				//��ѯ����ɸ����뵥�����д����嵥��Ϣ
				List<Details> allDetails=this.detailsDAO.findAllByPurchase(pid);
				Iterator<Details> iter=allDetails.iterator();
				while(iter.hasNext()) {
					//ÿ��ȡ��һ�������嵥
					Details details=iter.next();
					System.out.println(details.getRes());
					if (details.getRes().getRid()==null) {
						Res vo=new Res(); //�����칫��Ʒ����
						vo.setType(details.getType());
						vo.setSubtype(details.getSubtype());
						vo.setTitle(details.getTitle());
						vo.setPrice(details.getPrice());
						vo.setIndate(new Date());
						vo.setPhoto(details.getPhoto());
						vo.setRflag(details.getRflag());
						vo.setAmount(details.getAmount());
						//�����ݲ������ݿ�
						this.resDAO.doCreate(vo);
					}else {//��������嵥�Ѿ���֮ǰ���ǰ칫��Ʒ���޸���������
						this.resDAO.doUpdateAmount(details.getRes().getRid(), details.getAmount());
					}
				}
			
			}
		}
		return true;
	}
	
	
}























