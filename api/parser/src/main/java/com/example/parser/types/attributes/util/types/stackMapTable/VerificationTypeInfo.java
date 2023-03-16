package com.example.parser.types.attributes.util.types.stackMapTable;

import com.example.parser.interfaces.ClassFileElement;

import java.util.List;

public class VerificationTypeInfo implements ClassFileElement {

    protected Integer tag;
    protected String name;

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
