package com.sun.amr.vo;
import java.io.Serializable;
import java.util.List;
public class Type implements Serializable{
	private Integer tid;   //一级类别编号
	private String title;
	private List<Subtype> subtypes;
	private List<Details> allDetails;  //每个类别对应相应的清单详情信息
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Subtype> getSubtypes() {
		return subtypes;
	}
	public void setSubtypes(List<Subtype> subtypes) {
		this.subtypes = subtypes;
	}
	public List<Details> getAllDetails() {
		return allDetails;
	}
	public void setAllDetails(List<Details> allDetails) {
		this.allDetails = allDetails;
	}
	@Override
	public String toString() {
		return "Type [tid=" + tid + ", title=" + title + ", subtypes=" + subtypes + ", allDetails=" + allDetails + "]";
	}
}
