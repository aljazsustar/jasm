package com.example.parser.interfaces;

import com.example.parser.types.constantPool.constants.strings.Utf8Constant;


public abstract class AttributeBase implements ClassFileElement {
    protected Utf8Constant attributeName;
    protected Integer attributeNameIndex;
    protected Long attributeLength;

    public Utf8Constant getAttributeName() {
        return attributeName;
    }

    public Long getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(Long attributeLength) {
        this.attributeLength = attributeLength;
    }
}
