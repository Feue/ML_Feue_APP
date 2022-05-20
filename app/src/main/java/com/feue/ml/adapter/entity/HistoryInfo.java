package com.feue.ml.adapter.entity;

import java.util.Date;

public class HistoryInfo {

    private Long chapterId;
    private String chapterName;
    private String chapterAbout;
    private Date time;
    private String timeStr;
    private String imageSrc;

    public HistoryInfo() {
    }

    public HistoryInfo(String chapterName, String chapterAbout, String timeStr, String imageSrc) {
        this.chapterName = chapterName;
        this.chapterAbout = chapterAbout;
        this.timeStr = timeStr;
        this.imageSrc = imageSrc;
    }

    public HistoryInfo(Long chapterId, String chapterName, String chapterAbout, Date time, String timeStr, String imageSrc) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.chapterAbout = chapterAbout;
        this.time = time;
        this.timeStr = timeStr;
        this.imageSrc = imageSrc;
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

    public String getChapterAbout() {
        return chapterAbout;
    }

    public void setChapterAbout(String chapterAbout) {
        this.chapterAbout = chapterAbout;
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

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    @Override
    public String toString() {
        return "HistoryInfo{" +
                "chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\'' +
                ", time=" + time +
                ", timeStr='" + timeStr + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                '}';
    }
}
