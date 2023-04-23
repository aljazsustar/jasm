package com.example.parser.interfaces;

public abstract class VerificationTypeInfoBase implements ClassFileElement {
    protected Integer tag;

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }
}