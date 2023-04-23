package com.example.parser.interfaces;

public abstract class StackMapFrameBase implements ClassFileElement {
    protected Integer frameType;

    public Integer getFrameType() {
        return frameType;
    }

    public void setFrameType(Integer frameType) {
        this.frameType = frameType;
    }
}
