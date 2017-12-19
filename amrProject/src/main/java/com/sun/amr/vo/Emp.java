package com.sun.amr.vo;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Emp implements Serializable{
	private Integer eid;
	private String name;
	private String password;
	private String phone;
	private Double salary;
	private String note;
	private Integer aflag;
	private String sex;
	private String photo;
	private Dept dept;
	private Level level;
	private Integer heid;
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getAflag() {
		return aflag;
	}
	public void setAflag(Integer aflag) {
		this.aflag = aflag;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public Integer getHeid() {
		return heid;
	}
	public void setHeid(Integer heid) {
		this.heid = heid;
	}
	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", name=" + name + ", password=" + password + ", phone=" + phone + ", salary="
				+ salary + ", note=" + note + ", aflag=" + aflag + ", sex=" + sex + ", photo=" + photo + ", heid="
				+ heid + "]";
	}
}
