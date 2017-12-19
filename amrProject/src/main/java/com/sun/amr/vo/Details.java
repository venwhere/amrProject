package com.sun.amr.vo;
import java.io.Serializable;
public class Details implements Serializable{
	private Integer did;  //详情的编号
	private Type type;
	private Subtype subtype;
	private Emp emp;
	private String title;
	private Double price;
	private Integer amount;
	private String photo;
	private Integer rflag;  //特殊标记，表示是否要归还
	private Res res;
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Subtype getSubtype() {
		return subtype;
	}
	public void setSubtype(Subtype subtype) {
		this.subtype = subtype;
	}
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getRflag() {
		return rflag;
	}
	public void setRflag(Integer rflag) {
		this.rflag = rflag;
	}
	public Res getRes() {
		return res;
	}
	public void setRes(Res res) {
		this.res = res;
	}
	@Override
	public String toString() {
		return "Details [did=" + did + ", type=" + type + ", subtype=" + subtype + ", emp=" + emp + ", title=" + title
				+ ", price=" + price + ", amount=" + amount + ", photo=" + photo + ", rflag=" + rflag + ", res=" + res
				+ "]";
	}
}


















