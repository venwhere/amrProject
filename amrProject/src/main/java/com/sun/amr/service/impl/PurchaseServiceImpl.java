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
		if (!super.checkAuth( eid, 30)) {//权限验证30号
			return false;
		}
		//查询出当前用户的所有待购清单信息，目的用来生成申请清单信息
		List<Details> allDetails=this.detailsDAO.findAllDetails(eid);
		if (allDetails.size()==0) {//如果没有就返回false
			return false;
		}
		double sum=0.0;//保存总金额的变量
		Iterator<Details> iter=allDetails.iterator();
		while(iter.hasNext()) {
			Details details=iter.next();
			//计算总金额
			sum+=details.getPrice()*details.getAmount();
		}
		if (sum<0.0) {
			return false;
		}
		vo.setEmp(new Emp());  //创建当前用户的对象（申请人）
		vo.getEmp().setEid(eid);  //设置申请人的编号
		vo.setPubdate(new Date());
		vo.setStatus(0);
		vo.setTotal(MathUtil.round(sum, 2));//四舍五入，保留两位小数
		if (this.purchaseDAO.doCreate(vo)>0) {
			//如果修改成功自动生成pid
			return this.detailsDAO.doUpdateByPurchase(vo.getPid(), eid)>0;
		}
		return false;
	}
	@Override
	public Map<String, Object> listByEmp(int eid, int currentPage, int lineSize) throws Exception {
		if (!super.checkAuth(eid, 27)) {//验证权限
			return null;
		}
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		//保存查询的条件，在sql语句中获取
		paramMap.put("eid", eid);
		paramMap.put("start", (currentPage-1)*lineSize);
		paramMap.put("lineSize", lineSize);
		//保存所有申请单的信息
		map.put("allPurchases", this.purchaseDAO.findAllByEmp(paramMap));
		//统计数据量
		Integer count=this.purchaseDAO.getAllCountByEmp(eid);
		Integer allPages=count/lineSize+(count%lineSize==0?0:1);
		Page page=new Page(currentPage,allPages);
		page.setCount(count);
		map.put("pager", page);
		return map;
	}
	@Override
	public Purchase getByEmp(int eid, int pid) throws Exception {
		if (!super.checkAuth(eid, 27)) {//验证权限
			return null;
		}
		Purchase purchase=this.purchaseDAO.findByIdAndEmp(pid, eid);
		if (purchase!=null) {
			//将申请单的待购清单信息保存到申请单的属性中
			purchase.setAllDetails(this.detailsDAO.findAllByPurchase(pid));
			//查询申请人的信息
			purchase.setEmp(this.empDAO.findById(purchase.getEmp().getEid()));
		}
		return purchase;
	}
	@Override
	public Map<String, Object> listSimple(int eid, int currentPage, int lineSize) throws Exception {
		if (!super.checkAuth(eid, 41)) {//验证权限
			return null;
		}
		Map<String, Object> map=new HashMap<String,Object>();
		int start=(currentPage-1)*lineSize;
		//保存所有申请单的信息
		map.put("allPurchases", this.purchaseDAO.findAllSimpleSplit(start, lineSize));
		//统计数据量
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
		//申请单信息对象
		Purchase purchase=null;
		if (actid==27) {
			purchase=this.purchaseDAO.findByIdAndEmp(pid, eid);
		}else if (actid==41) {
			purchase=this.purchaseDAO.findById(pid);
		}
		if (purchase!=null) {
			//将申请单的待购清单信息保存到申请单的属性中
			purchase.setAllDetails(this.detailsDAO.findAllByPurchase(pid));
			//查询申请人的信息
			purchase.setEmp(this.empDAO.findById(purchase.getEmp().getEid()));
		}
		return purchase;
	}
	
	
	@Override
	public boolean editStatus(int eid, int pid, int status) throws Exception {
		if (!super.checkAuth1(eid, 4, 5, 42)) {//涉及资金的操作，验证要严格
			System.out.println("!super.checkAuth1(eid, 4, 5, 42)");
			return false;
		}
		if (this.purchaseDAO.doUpdateStatus(pid, status, eid)>0) {
			if (status==1) {//表示审核通过
				//查询出组成该申请单的所有待购清单信息
				List<Details> allDetails=this.detailsDAO.findAllByPurchase(pid);
				Iterator<Details> iter=allDetails.iterator();
				while(iter.hasNext()) {
					//每次取出一个待购清单
					Details details=iter.next();
					System.out.println(details.getRes());
					if (details.getRes().getRid()==null) {
						Res vo=new Res(); //创建办公用品对象
						vo.setType(details.getType());
						vo.setSubtype(details.getSubtype());
						vo.setTitle(details.getTitle());
						vo.setPrice(details.getPrice());
						vo.setIndate(new Date());
						vo.setPhoto(details.getPhoto());
						vo.setRflag(details.getRflag());
						vo.setAmount(details.getAmount());
						//将数据插入数据库
						this.resDAO.doCreate(vo);
					}else {//如果待购清单已经在之前就是办公用品则修改数量即可
						this.resDAO.doUpdateAmount(details.getRes().getRid(), details.getAmount());
					}
				}
			
			}
		}
		return true;
	}
	
	
}























