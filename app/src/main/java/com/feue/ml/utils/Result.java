package com.feue.ml.utils;

import java.util.Map;

public class Result {
    private Integer code;
    private Map<String, Object> result;
    private String message;
    private String requestUrl;

    public Result() {
    }

    public Result(Integer code, Map<String, Object> result, String message, String requestUrl) {
        this.code = code;
        this.result = result;
        this.message = message;
        this.requestUrl = requestUrl;
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                '}';
    }
}
