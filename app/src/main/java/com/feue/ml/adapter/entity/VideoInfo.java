package com.feue.ml.adapter.entity;

public class VideoInfo {
    private Long id;
    private Integer order;
    private String name;
    private String about;
    private String src;
    private Integer duration;
    private Long chapterId;

    public VideoInfo() {
    }

    public VideoInfo(String name, String about, Integer duration) {
        this.name = name;
        this.about = about;
        this.duration = duration;
    }

    public VideoInfo(Long id, Integer order, String name, String about, String src, Integer duration, Long chapterId) {
        this.id = id;
        this.order = order;
        this.name = name;
        this.about = about;
        this.src = src;
        this.duration = duration;
        this.chapterId = chapterId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }
}
