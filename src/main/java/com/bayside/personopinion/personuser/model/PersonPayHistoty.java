package com.bayside.personopinion.personuser.model;

import java.util.Date;

public class PersonPayHistoty {
	private String id;
	private String userid;
	private Double receipt_amount;
	private String costdevice;
	private String costnumber;
	private String costtype;
	private String requestip;
	private Integer status;
	private Date refundTime;
	private Double refund_fee;
	private Date costTime;
	private String personid;
	private String ordernumber;
	private String paynumber;
	private String viptype;
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
	public Double getReceipt_amount() {
		return receipt_amount;
	}
	public void setReceipt_amount(Double receipt_amount) {
		this.receipt_amount = receipt_amount;
	}
	public String getCostdevice() {
		return costdevice;
	}
	public void setCostdevice(String costdevice) {
		this.costdevice = costdevice;
	}
	public String getCostnumber() {
		return costnumber;
	}
	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}
	public String getCosttype() {
		return costtype;
	}
	public void setCosttype(String costtype) {
		this.costtype = costtype;
	}
	public String getRequestip() {
		return requestip;
	}
	public void setRequestip(String requestip) {
		this.requestip = requestip;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	public Double getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(Double refund_fee) {
		this.refund_fee = refund_fee;
	}
	public Date getCostTime() {
		return costTime;
	}
	public void setCostTime(Date costTime) {
		this.costTime = costTime;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getPaynumber() {
		return paynumber;
	}
	public void setPaynumber(String paynumber) {
		this.paynumber = paynumber;
	}
	public String getViptype() {
		return viptype;
	}
	public void setViptype(String viptype) {
		this.viptype = viptype;
	}
}
