package com.sun.util;

import com.sun.amr.action.abs.AbstractAction;

public class SplitUtil {
	private int cp=1;    //默认是第一页
	private int ls=6;    //默认每页显示三条信息
	private String col;  //模糊查询的字段
	private String kw;   //模糊查询的关键字
	private AbstractAction action;   //Action的父类对象，主要的目的是调用该父类的抽象方法取得一些子类单独定义的信息
	public SplitUtil() {}
	public SplitUtil(AbstractAction action) {
		this.action=action;
	}
	
	public void setCp(String cp) {
		try {
			this.cp=Integer.parseInt(cp);   //设置当前页，如果出现了异常按照默认处理为1
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public void setLs(String ls) {
		try {
			this.ls=Integer.parseInt(ls);     //设置每页显示的数据量，默认是3
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public void setCol(String col) {
		if (col==null||"".equals(col)) {//如果传递字段是空或者是null，直接取得子类提供的默认字段
			this.col=this.action.getDefaultColumn();
		}else {
			this.col=col;
		}
	}
	public void setKw(String kw) {
		if ("".equals(kw)) {
			this.kw=null;
		}else {
			this.kw=kw;
		}
	}
	public int getCurrentPage() {
		return cp;
	}
	public int getLineSize() {
		return ls;
	}
	public String getColumn() {
		return col;
	}
	public String getKeyword() {
		return kw;
	}
}





















