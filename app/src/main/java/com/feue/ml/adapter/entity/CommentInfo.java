package com.feue.ml.adapter.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentInfo {
    private Long id;
    private Long userId;
    private String username;
    private String content;
    private Integer level;
    private Date time;
    private String timeStr;
    private Long chapterId;
    private String chapterName;

    public CommentInfo() {
    }

    public CommentInfo(String username, String content, String time, String chapterName) {
        this.username = username;
        this.content = content;
        this.timeStr = time;
        this.chapterName = chapterName;
    }

    public CommentInfo(Long id, Long userId, String username, String content, Integer level, Date time, Long chapterId, String chapterName) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.level = level;
        this.time = time;
        this.chapterId = chapterId;
        this.chapterName = chapterName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTimeStr() {
        if (timeStr == null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
            timeStr = format.format(time);
        }
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", level=" + level +
                ", time=" + time +
                ", chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\'' +
                '}';
    }
}
