package com.feue.ml.adapter.entity;

import java.util.Date;

public class NoticeInfo {

    private Long id;
    private String name;
    private String content;
    private Date time;
    private String timeStr;
    private Long userId;
    private String username;

    public NoticeInfo() {
    }

    public NoticeInfo(String name, String content, String timeStr, String username) {
        this.name = name;
        this.content = content;
        this.timeStr = timeStr;
        this.username = username;
    }

    public NoticeInfo(Long id, String name, String content, Date time, String timeStr, Long userId, String username) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.time = time;
        this.timeStr = timeStr;
        this.userId = userId;
        this.username = username;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "NoticeInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", timeStr='" + timeStr + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
