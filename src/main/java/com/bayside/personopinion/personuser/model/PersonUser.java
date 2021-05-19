package com.bayside.personopinion.personuser.model;

import java.util.Date;

public class PersonUser {
    private String id;

    private String name;

    private Date registertime;

    private Date endtime;

    private Integer status;

    private Integer isvip;

    private String isqq;

    private String isweixin;

    private String isweibo;

    private String registerip;

    private String registerdevice;
    
    private String telephone;
    
    private String password;
    
    private String weibouid;
    
    private String wechatuid;
    
    private String qquid;
    
    private Date vipexpiredtime;
    
    private String personid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsvip() {
        return isvip;
    }

    public void setIsvip(Integer isvip) {
        this.isvip = isvip;
    }

    public String getIsqq() {
        return isqq;
    }

    public void setIsqq(String isqq) {
        this.isqq = isqq == null ? null : isqq.trim();
    }

    public String getIsweixin() {
        return isweixin;
    }

    public void setIsweixin(String isweixin) {
        this.isweixin = isweixin == null ? null : isweixin.trim();
    }

    public String getIsweibo() {
        return isweibo;
    }

    public void setIsweibo(String isweibo) {
        this.isweibo = isweibo == null ? null : isweibo.trim();
    }

    public String getRegisterip() {
        return registerip;
    }

    public void setRegisterip(String registerip) {
        this.registerip = registerip == null ? null : registerip.trim();
    }

    public String getRegisterdevice() {
        return registerdevice;
    }

    public void setRegisterdevice(String registerdevice) {
        this.registerdevice = registerdevice == null ? null : registerdevice.trim();
    }

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWeibouid() {
		return weibouid;
	}

	public void setWeibouid(String weibouid) {
		this.weibouid = weibouid;
	}

	public String getWechatuid() {
		return wechatuid;
	}

	public void setWechatuid(String wechatuid) {
		this.wechatuid = wechatuid;
	}

	public String getQquid() {
		return qquid;
	}

	public void setQquid(String qquid) {
		this.qquid = qquid;
	}

	public Date getVipexpiredtime() {
		return vipexpiredtime;
	}

	public void setVipexpiredtime(Date vipexpiredtime) {
		this.vipexpiredtime = vipexpiredtime;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}
}