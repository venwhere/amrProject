package com.sun.amr.vo;
import java.io.Serializable;
import java.util.List;
@SuppressWarnings("serial")
public class Groups implements Serializable{
	private Integer gid;
	private String title;
	private String type;
	private List<Dept> allDepts;
	private List<Action> allActions;
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Dept> getAllDepts() {
		return allDepts;
	}
	public void setAllDepts(List<Dept> allDepts) {
		this.allDepts = allDepts;
	}
	public List<Action> getAllActions() {
		return allActions;
	}
	public void setAllActions(List<Action> allActions) {
		this.allActions = allActions;
	}
	@Override
	public String toString() {
		return "Groups [gid=" + gid + ", title=" + title + ", type=" + type + ", allDepts=" + allDepts + "]";
	}
}
