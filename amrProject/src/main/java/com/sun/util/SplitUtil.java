package com.sun.util;

import com.sun.amr.action.abs.AbstractAction;

public class SplitUtil {
	private int cp=1;    //Ĭ���ǵ�һҳ
	private int ls=6;    //Ĭ��ÿҳ��ʾ������Ϣ
	private String col;  //ģ����ѯ���ֶ�
	private String kw;   //ģ����ѯ�Ĺؼ���
	private AbstractAction action;   //Action�ĸ��������Ҫ��Ŀ���ǵ��øø���ĳ��󷽷�ȡ��һЩ���൥���������Ϣ
	public SplitUtil() {}
	public SplitUtil(AbstractAction action) {
		this.action=action;
	}
	
	public void setCp(String cp) {
		try {
			this.cp=Integer.parseInt(cp);   //���õ�ǰҳ������������쳣����Ĭ�ϴ���Ϊ1
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public void setLs(String ls) {
		try {
			this.ls=Integer.parseInt(ls);     //����ÿҳ��ʾ����������Ĭ����3
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public void setCol(String col) {
		if (col==null||"".equals(col)) {//��������ֶ��ǿջ�����null��ֱ��ȡ�������ṩ��Ĭ���ֶ�
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





















