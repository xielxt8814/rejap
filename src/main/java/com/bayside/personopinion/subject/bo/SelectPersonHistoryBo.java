package com.bayside.personopinion.subject.bo;

/** 查询个人历史舆情信息 接参 */
public class SelectPersonHistoryBo {
	/** 用户ID */
	private String userid;
	private String persionid;
	/** 情感倾向 */
	private String emotion;
	/** 媒体类型 : 文章所属载体(新闻、博客、论坛、微博) */
	private String formats;
	/** 起始时间 */
	private String starttime;
	/** 截止时间 */
	private String endtime;
	/**  */
	private String timetype;
	/** 按时间排序，  1 升序， 0降序 */
	private String timeorder;
	/** 按文章像似数排序，  1 升序， 0降序 */
	private String similarnumorder;
	/**按文章相关性排序，  1 升序， 0降序*/
	private String dependencyorder;
	
	/**通过ID模糊查询*/
	private String tittle;

	public String getPersionid() {
		return persionid;
	}

	public void setPersionid(String persionid) {
		this.persionid = persionid;
	}

	public String getSimilarnumorder() {
		return similarnumorder;
	}

	public void setSimilarnumorder(String similarnumorder) {
		this.similarnumorder = similarnumorder;
	}

	public String getDependencyorder() {
		return dependencyorder;
	}

	public void setDependencyorder(String dependencyorder) {
		this.dependencyorder = dependencyorder;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public String getFormats() {
		return formats;
	}

	public void setFormats(String formats) {
		this.formats = formats;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTimetype() {
		return timetype;
	}

	public void setTimetype(String timetype) {
		this.timetype = timetype;
	}

	public String getTimeorder() {
		return timeorder;
	}

	public void setTimeorder(String timeorder) {
		this.timeorder = timeorder;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
}
