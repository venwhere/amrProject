package com.sun.amr.vo;
import java.io.Serializable;
@SuppressWarnings("serial")
public class Level implements Serializable{
	private Integer lid;
	private String title;
	private Double losal;
	private Double hisal;
	public Integer getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getLosal() {
		return losal;
	}
	public void setLosal(Double losal) {
		this.losal = losal;
	}
	public Double getHisal() {
		return hisal;
	}
	public void setHisal(Double hisal) {
		this.hisal = hisal;
	}
	@Override
	public String toString() {
		return "Level [lid=" + lid + ", title=" + title + ", losal=" + losal + ", hisal=" + hisal + "]";
	}
}
