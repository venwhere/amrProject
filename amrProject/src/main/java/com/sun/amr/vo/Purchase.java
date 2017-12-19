package com.sun.amr.vo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class Purchase implements Serializable{
	private Integer pid;  //申请单的编号
	private Emp emp;
	private Integer meid;  //审核人的信息
	private String title;  //标题
	private Double total;   //总金额
	private Integer status;  //审核状态
	private Date pubdate;  //申请日期
	private String note;
	private List<Details> allDetails;  //所有待购清单
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	public Integer getMeid() {
		return meid;
	}
	public void setMeid(Integer meid) {
		this.meid = meid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Details> getAllDetails() {
		return allDetails;
	}
	public void setAllDetails(List<Details> allDetails) {
		this.allDetails = allDetails;
	}
	@Override
	public String toString() {
		return "Purchase [pid=" + pid + ", emp=" + emp + ", meid=" + meid + ", title=" + title + ", total=" + total
				+ ", status=" + status + ", pubdate=" + pubdate + ", note=" + note + ", allDetails=" + allDetails + "]";
	}
}
