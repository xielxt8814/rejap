package com.bayside.personopinion.subject.model;

public class SubjectarticleWithBLOBs extends Subjectarticle {
    private String content;

    private String url;

    private String contentType;

    private String negativeWord;

    private String positiveWord;

    private String imgurl;

    private String abstrac;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public String getNegativeWord() {
        return negativeWord;
    }

    public void setNegativeWord(String negativeWord) {
        this.negativeWord = negativeWord == null ? null : negativeWord.trim();
    }

    public String getPositiveWord() {
        return positiveWord;
    }

    public void setPositiveWord(String positiveWord) {
        this.positiveWord = positiveWord == null ? null : positiveWord.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getAbstrac() {
        return abstrac;
    }

    public void setAbstrac(String abstrac) {
        this.abstrac = abstrac == null ? null : abstrac.trim();
    }
}