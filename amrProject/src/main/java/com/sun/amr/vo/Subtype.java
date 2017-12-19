package com.sun.amr.vo;
import java.io.Serializable;
import java.util.List;
public class Subtype implements Serializable{
	private Integer stid;  //二级类型的编号
	private Integer tid;
	private Type type;
	private String title;
	private List<Details> allDetails;  //有多个详情信息
	public Integer getStid() {
		return stid;
	}
	public void setStid(Integer stid) {
		this.stid = stid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Details> getAllDetails() {
		return allDetails;
	}
	public void setAllDetails(List<Details> allDetails) {
		this.allDetails = allDetails;
	}
	@Override
	public String toString() {
		return "Subtype [stid=" + stid + ", tid=" + tid + ", type=" + type + ", title=" + title + ", allDetails="
				+ allDetails + "]";
	}
}
