package com.sun.amr.vo;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Dept implements Serializable{
	private Integer did;
	private String title;
	private Integer sflag;
	private List<Emp> allEmps;
	private List<Groups> allGroups;
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getSflag() {
		return sflag;
	}
	public void setSflag(Integer sflag) {
		this.sflag = sflag;
	}
	public List<Emp> getAllEmps() {
		return allEmps;
	}
	public void setAllEmps(List<Emp> allEmps) {
		this.allEmps = allEmps;
	}
	public List<Groups> getAllGroups() {
		return allGroups;
	}
	public void setAllGroups(List<Groups> allGroups) {
		this.allGroups = allGroups;
	}
	@Override
	public String toString() {
		return "Dept [did=" + did + ", title=" + title + ", sflag=" + sflag + ", allEmps=" + allEmps + "]";
	}
}
