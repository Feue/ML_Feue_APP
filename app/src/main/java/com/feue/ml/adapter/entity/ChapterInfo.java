package com.feue.ml.adapter.entity;

import java.util.Date;

public class ChapterInfo {

    private Long id;
    private Integer order;
    private String name;
    private String about;
    private Long courseId;
    private Date startTime;
    private Date endTime;

    public ChapterInfo() {
    }

    public ChapterInfo(Integer order, String name, String about) {
        this.order = order;
        this.name = name;
        this.about = about;
    }

    public ChapterInfo(Long id, Integer order, String name, String about, Long courseId, Date startTime, Date endTime) {
        this.id = id;
        this.order = order;
        this.name = name;
        this.about = about;
        this.courseId = courseId;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ChapterInfo{" +
                "id=" + id +
                ", order=" + order +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", courseId=" + courseId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
