package com.jasm.jasm.v1.models;

public class Error {
    private Integer code;
    private String summary;
    private String details;

    public Error(String summary, String details) {
        this.summary = summary;
        this.details = details;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
