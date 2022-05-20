package com.feue.ml.adapter.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CourseInfo {

    private Long id;
    private String name;
    private String about;
    private Long userId;
    private String teacherName;
    private Date startTime;
    private Date endTime;
    private Long categoryId;
    private String category;
    private String imageUrl;

    public CourseInfo(Long id, String name, String about, Long userId, String teacherName, Date startTime, Date endTime, Long categoryId, String category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.userId = userId;
        this.teacherName = teacherName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.categoryId = categoryId;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public CourseInfo(String name, String about, String imageUrl) {
        this.name = name;
        this.about = about;
        this.imageUrl = imageUrl;
        this.teacherName = "Feue";
        this.category = "C/C++";

        Random random = new Random();
        this.id = random.nextLong();
        this.userId = random.nextLong();
        this.endTime = new Date();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.MONTH, -1);
        this.startTime = date.getTime();
        this.categoryId = random.nextLong();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", userId='" + userId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", categoryId=" + categoryId +
                '}';
    }
}
