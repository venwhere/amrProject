package com.huang.microboot.vo;
public class Dept {
	private Integer deptno;
	private String title;
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", title=" + title + "]";
	}
}
