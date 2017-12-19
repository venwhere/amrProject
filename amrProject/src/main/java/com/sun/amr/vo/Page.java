package com.sun.amr.vo;
import java.io.Serializable;
import java.util.Arrays;
public class Page implements Serializable{
	private Integer cp;
	private Integer allPages;
	private int[] pages;  //保存页面数字
	private Integer count;
	public Page() {}
	public Page(Integer cp,Integer allPages) {
		this.cp=cp;
		this.allPages=allPages;
	}
	public Integer getCp() {
		return cp;
	}
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	public Integer getAllPages() {
		return allPages;
	}
	public void setAllPages(Integer allPages) {
		this.allPages = allPages;
	}
	public void setPages(int[] pages) {
		this.pages = pages;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public boolean isFirst() {
		return this.cp>1;
	}
	public boolean isHasPre() {
		return this.cp>2;
	}
	public boolean isLast() {
		return this.cp<this.allPages;
	}
	public boolean isHasNext() {
		return this.cp<this.allPages-2;
	}
	public int[] getPages() {
		int start=this.cp-2;
		int end=this.cp+2;
		if(start<1) {
			start=1;
		}
		if(end>this.allPages) {
			end=this.allPages;
		}
		int result=end-start+1;
		this.pages=new int[result];
		for(int i=0;i<result;i++) {
			this.pages[i]=start++;
		}
		return pages;
	}
	@Override
	public String toString() {
		return "Page [cp=" + cp + ", allPages=" + allPages + ", pages=" + Arrays.toString(pages) + ", count=" + count
				+ "]";
	}
}



















