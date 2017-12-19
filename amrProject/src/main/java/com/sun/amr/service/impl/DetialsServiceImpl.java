package com.sun.amr.service.impl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sun.amr.dao.IDetailsDAO;
import com.sun.amr.dao.IResDAO;
import com.sun.amr.dao.ISubtypeDAO;
import com.sun.amr.dao.ITypeDAO;
import com.sun.amr.service.IDetailsService;
import com.sun.amr.service.abs.AbstractService;
import com.sun.amr.vo.Details;
import com.sun.amr.vo.Emp;
import com.sun.amr.vo.Res;
@Service
public class DetialsServiceImpl extends AbstractService implements IDetailsService{
	@Resource
	private ITypeDAO typeDAO;
	@Resource
	private ISubtypeDAO Subtype;
	@Resource 
	private IDetailsDAO detailsDAO;
	@Resource
	private ISubtypeDAO subtypeDAO;
	@Resource
	private IResDAO resDAO;
	@Override
	public Map<String, Object> addPre(int eid) throws Exception {
		if (!super.checkAuth(eid, 25)) {
			return null;
		}
		Map<String, Object> map=new HashMap<String,Object>();
		//保存一级类别类目
		map.put("allTypes", this.typeDAO.findAll());
		return map;
	}
	@Override
	public boolean add(Details vo, int eid) throws Exception {
		if (!super.checkAuth(eid, 25)) {
			return false;
		}
		vo.setEmp(new Emp());   //指定出是那个雇员进行的操作
		vo.getEmp().setEid(eid);
		vo.setAmount(1);  //设置数量
		return this.detailsDAO.doCreate(vo)>0;
	}
	@Override
	public List<Details> listDetails(int eid) throws Exception {
		if (!super.checkAuth(eid, 25)) {
			return null;
		}
		List<Details> list=this.detailsDAO.findAllDetails(eid);
		return list;
	}
	@Override
	public Map<String, Object> rm(int eid, List<Integer> ids) throws Exception {
		Map<String, Object> map=new HashMap<String,Object>();
		boolean flag=true;  //定义一个删除的标记
		if (!super.checkAuth(eid, 25)) {//需要25号权限
			flag=false;   
		}
		if (ids.size()==0) {//如果集合中没有编号，就不需要执行删除
			flag=false;
		}
		if (flag) {
			//查询出需要删除的所有清单信息
			List<Details> allDetails=this.detailsDAO.findAllByPhoto(ids);
			//进行迭代
			Iterator<Details> iterD=allDetails.iterator();
			while(iterD.hasNext()) {
				Details details=iterD.next();
				//每个清单都有添加人的信息，这里是验证现在操作的人是否和添加的人是同一个编号，如果不是则阻止操作删除
				if (!details.getEmp().getEid().equals(eid)) {
					flag=false;
					break;
				}
			}
			if (flag) {
				//实行删除
				flag=this.detailsDAO.doRemoveBatch(ids)>0;
				if (flag) {
					//如果删除成功则将查询到的清单信息保存到map中继续返回，目的在控制层实现照片的删除
					map.put("allDetails", allDetails);
				}
			}
		}
		//保存标记，看是否要删除照片
		map.put("flag", flag);
		return map;
	}
	@Override
	public Map<String, Object> editAmount(int eid,Map<Integer, Integer> dinfo,List<Integer> ids) throws Exception{
		Map<String, Object> map=new HashMap<String,Object>();
		boolean flag=true;//定义修改符号
		if (!super.checkAuth(eid, 25)) {//yanz
			flag=false;
		}
		if (flag) {
			//遍历Map集合取得编号和变量
			Set<Map.Entry<Integer, Integer>> set=dinfo.entrySet();
			//迭代set集合
			Iterator<Map.Entry<Integer, Integer>> iter=set.iterator();
			while(iter.hasNext()) {
				//取得每个键对值
				Map.Entry<Integer, Integer> mEntry=iter.next();
				Details vo=new Details();
				vo.setDid(mEntry.getKey());   //取出对应的编号保存到vo对象中
				vo.setAmount(mEntry.getValue());   //取出对应的数量保存到vo对象中
				vo.setEmp(new Emp());
				vo.getEmp().setEid(eid);//保存当前操作人的编号，目的是在sql语句中验证添加清单的人和现在操作的人是否是同一个
				if (this.detailsDAO.doUpdateAmount(vo)==0) {//修改数量
					flag=false;
				}
			}
			//以下的操作是删除数量为0的清单
			if (flag) {
				if (ids.size()>0) {
					//先查询要删除的清单消息 
					List<Details> allDetails=this.detailsDAO.findAllByPhoto(ids);
					Iterator<Details> iterD=allDetails.iterator();
					while(iterD.hasNext()) {
						Details details=iterD.next();
						//判断当前操作人是否为清单添加者
						if (!details.getEmp().getEid().equals(eid)) {
							flag=false;
							break;
						}
					}
					if (flag) {
						//删除数量为0的清单
						flag=this.detailsDAO.doRemoveBatch(ids)>0;
						if (flag) {
							//将删除的清单的信息保存到map集合中返回到控制层实现对应的照片的删除
							map.put("allDetails", allDetails);
						}
					}
				}
			}
		}
		//保存最终的标记
		map.put("flag", flag);
		return map;
	}
	@Override
	public Map<String, Object> editPre(int eid, int did) throws Exception {
		if (!super.checkAuth(eid, 25)) {   //需要25号权限
			return null;
		}
		//取得清单的基本信息
		Details details=this.detailsDAO.findByIdForPrebuy(eid, did);
		if (details==null) {
			return null;
		}
		Map<String, Object> map=new HashMap<String,Object>();
		//保存一级类别信息
		map.put("allTypes", this.typeDAO.findAll());
		//保存的是当前二级类别信息
		map.put("allSubtypes", this.subtypeDAO.findAllByType(details.getType().getTid()));
		//保存基本信息
		map.put("details", details);
		return map;
	}
	@Override
	public boolean edit(int eid, Details vo) throws Exception {
		if (!super.checkAuth(eid, 25)) {//25号权限
			return false;
		}
		return this.detailsDAO.doUpdatePrebuy(vo)>0;
	}
	@Override
	public boolean addAppend(int eid, int rid) throws Exception {
		if (!super.checkAuth(eid, 25)) {//验证25号权限
			return false;
		}
		//根据rid查询出要追加的用品信息，用来生成追加的待购清单
		Details details=this.detailsDAO.findByDetailsExists(eid, rid);
		if (details==null) {
			//根据rid查询出要追加的用品信息，用来生成追加待购清单
			Res res=this.resDAO.findById(rid);
			Details vo=new Details();
			//将用品信息保存到待购清单对象中
			vo.setRes(res);
			vo.setType(res.getType());
			vo.setSubtype(res.getSubtype());
			vo.setTitle(res.getTitle());
			vo.setEmp(new Emp());
			vo.getEmp().setEid(eid);
			vo.setPrice(res.getPrice());
			vo.setAmount(1); //数量暂时设置为1
			vo.setPhoto(res.getPhoto());
			vo.setRflag(res.getRflag());
			//将追加的清单信息保存到数据表中
			return this.detailsDAO.doCreate(vo)>0;
		}else {//如果rid不为null，此时审核是追加的待购清单，则在用品的原有的数量伤加上新追加的数量
			return this.detailsDAO.doUpdateAppendAmount(details.getDid())>0;
		}
	}
}














