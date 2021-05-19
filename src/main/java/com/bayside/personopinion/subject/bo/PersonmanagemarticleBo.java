package com.bayside.personopinion.subject.bo;

import java.util.Date;

public class PersonmanagemarticleBo {
    private String id;

    private String persionid;

    private String articleid;

    private String userid;

    private String keywordRule;

    private Boolean reportinfo;

    private Boolean attention;

    private Boolean warning;

    private Boolean readsign;

    private Boolean briefing;

    private String emotion;

    private String formats;

    private Date updatetime;

    private String source;

    private String title;

    private String url;

    private String author;

    private Date pubdate;

    private Date attentiontime;

    private Integer repeatcount;

    private Integer commtcount;

    private Integer collection;

    private String content;
    
    private String tittle;

    private String dataSource;
    
    private String startTime;
    
    private String endTime;
    
    public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPersionid() {
        return persionid;
    }

    public void setPersionid(String persionid) {
        this.persionid = persionid == null ? null : persionid.trim();
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid == null ? null : articleid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getKeywordRule() {
        return keywordRule;
    }

    public void setKeywordRule(String keywordRule) {
        this.keywordRule = keywordRule == null ? null : keywordRule.trim();
    }

    public Boolean getReportinfo() {
        return reportinfo;
    }

    public void setReportinfo(Boolean reportinfo) {
        this.reportinfo = reportinfo;
    }

    public Boolean getAttention() {
        return attention;
    }

    public void setAttention(Boolean attention) {
        this.attention = attention;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    public Boolean getReadsign() {
        return readsign;
    }

    public void setReadsign(Boolean readsign) {
        this.readsign = readsign;
    }

    public Boolean getBriefing() {
        return briefing;
    }

    public void setBriefing(Boolean briefing) {
        this.briefing = briefing;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion == null ? null : emotion.trim();
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats == null ? null : formats.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public Date getAttentiontime() {
        return attentiontime;
    }

    public void setAttentiontime(Date attentiontime) {
        this.attentiontime = attentiontime;
    }

    public Integer getRepeatcount() {
        return repeatcount;
    }

    public void setRepeatcount(Integer repeatcount) {
        this.repeatcount = repeatcount;
    }

    public Integer getCommtcount() {
        return commtcount;
    }

    public void setCommtcount(Integer commtcount) {
        this.commtcount = commtcount;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
}