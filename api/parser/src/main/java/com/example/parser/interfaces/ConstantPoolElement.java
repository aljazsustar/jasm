package com.example.parser.interfaces;

public abstract class ConstantPoolElement implements ClassFileElement {
    protected int tag;
    protected Integer constantPoolIndex;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Integer getConstantPoolIndex() {
        return constantPoolIndex;
    }

    public void setConstantPoolIndex(Integer constantPoolIndex) {
        this.constantPoolIndex = constantPoolIndex;
    }
}
