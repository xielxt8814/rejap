package com.bayside.personopinion.subject.bo;

import java.util.Date;

public class PersonmanageBoQingGan {
	private String persionid;
	private String startTime;
	private String endTime;
	
	private int info_acount;
	private int negative_acount;
	private int neutral_acount;
	private int positive_acount;
	public String getPersionid() {
		return persionid;
	}
	public void setPersionid(String persionid) {
		this.persionid = persionid;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getInfo_acount() {
		return info_acount;
	}
	public void setInfo_acount(int info_acount) {
		this.info_acount = info_acount;
	}
	public int getNegative_acount() {
		return negative_acount;
	}
	public void setNegative_acount(int negative_acount) {
		this.negative_acount = negative_acount;
	}
	public int getNeutral_acount() {
		return neutral_acount;
	}
	public void setNeutral_acount(int neutral_acount) {
		this.neutral_acount = neutral_acount;
	}
	public int getPositive_acount() {
		return positive_acount;
	}
	public void setPositive_acount(int positive_acount) {
		this.positive_acount = positive_acount;
	}
}
