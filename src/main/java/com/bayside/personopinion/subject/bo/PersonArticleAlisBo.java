package com.bayside.personopinion.subject.bo;

import java.util.Date;

public class PersonArticleAlisBo {
    private Date updateTime;
	private Integer infoAcount;
	private Integer negativeAcount;
	private Integer neutralAcount;
	private Integer positiveAcount;
	
	public Integer getInfoAcount() {
		return infoAcount;
	}
	public void setInfoAcount(Integer infoAcount) {
		this.infoAcount = infoAcount;
	}
	public Integer getNegativeAcount() {
		return negativeAcount;
	}
	public void setNegativeAcount(Integer negativeAcount) {
		this.negativeAcount = negativeAcount;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getNeutralAcount() {
		return neutralAcount;
	}
	public void setNeutralAcount(Integer neutralAcount) {
		this.neutralAcount = neutralAcount;
	}
	public Integer getPositiveAcount() {
		return positiveAcount;
	}
	public void setPositiveAcount(Integer positiveAcount) {
		this.positiveAcount = positiveAcount;
	}

}
