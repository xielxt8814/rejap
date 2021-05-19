package com.bayside.personopinion.personuser.model;

import java.util.Date;

public class PersonPower {
	private String id;
	private String userid;
	private String personid;
	private String paytypeid;
	private String payname;
	private Double paymoney;
	private Date expirytime;
	private int cishu;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getPaytypeid() {
		return paytypeid;
	}
	public void setPaytypeid(String paytypeid) {
		this.paytypeid = paytypeid;
	}
	public String getPayname() {
		return payname;
	}
	public void setPayname(String payname) {
		this.payname = payname;
	}
	public Double getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(Double paymoney) {
		this.paymoney = paymoney;
	}
	public Date getExpirytime() {
		return expirytime;
	}
	public void setExpirytime(Date expirytime) {
		this.expirytime = expirytime;
	}
	public int getCishu() {
		return cishu;
	}
	public void setCishu(int cishu) {
		this.cishu = cishu;
	}
}
