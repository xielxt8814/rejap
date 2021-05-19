package com.bayside.personopinion.subject.model;

import java.util.Date;

public class Subjectarticle {
    private String id;

    private String tittle;

    private Date pubdate;

    private String dataSource;

    private String author;

    private String emotion;

    private String formats;

    private Integer readcount;

    private Integer commtcount;

    private Integer repeatcount;

    private Integer aggreecount;

    private Integer score;

    private Integer similarnum;

    private Integer relateWord;

    private Integer opinionWord;

    private Integer warningWord;

    private Integer newsindex;

    private Integer searchNum;

    private Date updatetime;

    private String viewPoint;

    private String domain;

    private String simhashcode;

    private Boolean screening;
    
    private String content;

    private String url;

    private String contentType;

    private String negativeWord;

    private String positiveWord;

    private String imgurl;

    private String abstrac;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle == null ? null : tittle.trim();
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
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

    public Integer getReadcount() {
        return readcount;
    }

    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }

    public Integer getCommtcount() {
        return commtcount;
    }

    public void setCommtcount(Integer commtcount) {
        this.commtcount = commtcount;
    }

    public Integer getRepeatcount() {
        return repeatcount;
    }

    public void setRepeatcount(Integer repeatcount) {
        this.repeatcount = repeatcount;
    }

    public Integer getAggreecount() {
        return aggreecount;
    }

    public void setAggreecount(Integer aggreecount) {
        this.aggreecount = aggreecount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSimilarnum() {
        return similarnum;
    }

    public void setSimilarnum(Integer similarnum) {
        this.similarnum = similarnum;
    }

    public Integer getRelateWord() {
        return relateWord;
    }

    public void setRelateWord(Integer relateWord) {
        this.relateWord = relateWord;
    }

    public Integer getOpinionWord() {
        return opinionWord;
    }

    public void setOpinionWord(Integer opinionWord) {
        this.opinionWord = opinionWord;
    }

    public Integer getWarningWord() {
        return warningWord;
    }

    public void setWarningWord(Integer warningWord) {
        this.warningWord = warningWord;
    }

    public Integer getNewsindex() {
        return newsindex;
    }

    public void setNewsindex(Integer newsindex) {
        this.newsindex = newsindex;
    }

    public Integer getSearchNum() {
        return searchNum;
    }

    public void setSearchNum(Integer searchNum) {
        this.searchNum = searchNum;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getViewPoint() {
        return viewPoint;
    }

    public void setViewPoint(String viewPoint) {
        this.viewPoint = viewPoint == null ? null : viewPoint.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getSimhashcode() {
        return simhashcode;
    }

    public void setSimhashcode(String simhashcode) {
        this.simhashcode = simhashcode == null ? null : simhashcode.trim();
    }

    public Boolean getScreening() {
        return screening;
    }

    public void setScreening(Boolean screening) {
        this.screening = screening;
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getNegativeWord() {
		return negativeWord;
	}

	public void setNegativeWord(String negativeWord) {
		this.negativeWord = negativeWord;
	}

	public String getPositiveWord() {
		return positiveWord;
	}

	public void setPositiveWord(String positiveWord) {
		this.positiveWord = positiveWord;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getAbstrac() {
		return abstrac;
	}

	public void setAbstrac(String abstrac) {
		this.abstrac = abstrac;
	}
}