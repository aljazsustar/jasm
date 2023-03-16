package com.example.parser.types.attributes.criticalAttributes;

import com.example.parser.interfaces.AttributeBase;
import com.example.parser.interfaces.ConstantValue;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class ConstantValueAttribute extends AttributeBase {

    private Integer attributeNameIndex;
    private Integer constantValueIndex;
    private ConstantValue constantValue;

    public ConstantValueAttribute(Utf8Constant attributeName, Long attributeLength, Integer constantValueIndex) {
        this.attributeName = attributeName;
        this.attributeLength = attributeLength;
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
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
