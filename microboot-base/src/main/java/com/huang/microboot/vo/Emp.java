
package com.huang.microboot.vo;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
public class Emp implements Serializable{
	@NotNull(message="编号不能为空")
	@Digits(integer=4,fraction=0,message="编号必须是数字")
	private Integer eid;
	@NotNull(message="名字不能为空")
	@Length(max=20,min=8,message="必须为8~20为字符")
	private String name;
	@NotNull(message="年龄不能为空")
	@Digits(integer=3,fraction=0,message="年龄不合法")
	private Integer age;
	@NotNull(message="薪资不能为空")
	@Digits(integer=20,fraction=2,message="薪资不合法")
	private Double sal;
	@NotNull(message="邮箱不能为空")
	@Email(message="邮箱格式不对")
	private String email;
	private Date hiredate;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", name=" + name + ", age=" + age + ", sal=" + sal + ", email=" + email
				+ ", hiredate=" + hiredate + "]";
	}
}
