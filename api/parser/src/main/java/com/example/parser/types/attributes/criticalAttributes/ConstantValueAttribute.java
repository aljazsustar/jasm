package com.example.parser.types.attributes.criticalAttributes;

import com.example.parser.interfaces.AttributeBase;
import com.example.parser.interfaces.ConstantValue;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class ConstantValueAttribute extends AttributeBase {

    private Integer constantValueIndex;
    private ConstantValue constantValue;

    public ConstantValueAttribute(Integer attributeNameIndex, Utf8Constant attributeName, Long attributeLength, Integer constantValueIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeName = attributeName;
        this.attributeLength = attributeLength;
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.attributeNameIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.attributeLength, 4));
        bytes.addAll(WritingUtil.writeBytes(this.constantValueIndex, 2));
        return bytes;
    }

    public Integer getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public void setAttributeNameIndex(Integer attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    public Integer getConstantValueIndex() {
        return constantValueIndex;
    }

    public void setConstantValueIndex(Integer constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }

    public ConstantValue getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(ConstantValue constantValue) {
        this.constantValue = constantValue;
    }

    @Override
    public String toString() {
        return "ConstantValueAttribute{" +
                "attributeNameIndex=" + attributeNameIndex +
                ", constantValueIndex=" + constantValueIndex +
                ", constantValue=" + constantValue +
                ", attributeName=" + attributeName +
                ", attributeLength=" + attributeLength +
                '}';
    }
}
