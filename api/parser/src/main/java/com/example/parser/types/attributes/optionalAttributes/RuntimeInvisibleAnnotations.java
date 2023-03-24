package com.example.parser.types.attributes.optionalAttributes;

import com.example.parser.interfaces.AttributeBase;
import com.example.parser.types.attributes.util.types.annotations.Annotations;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class RuntimeInvisibleAnnotations extends AttributeBase {

    private Integer numAnnotations;
    private Annotations annotations;

    public RuntimeInvisibleAnnotations(Integer attributeNameIndex, Utf8Constant attributeName, Long attributeLength, Integer numAnnotations, Annotations annotations) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeName = attributeName;
        this.attributeLength = attributeLength;
        this.numAnnotations = numAnnotations;
        this.annotations = annotations;
    }

    public RuntimeInvisibleAnnotations(Integer numAnnotations, Annotations attributes) {
        super();
    }

    public Integer getNumAnnotations() {
        return numAnnotations;
    }

    public void setNumAnnotations(Integer numAnnotations) {
        this.numAnnotations = numAnnotations;
    }

    public Annotations getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotations annotations) {
        this.annotations = annotations;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.attributeNameIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.attributeLength, 4));
        bytes.addAll(this.annotations.toHex());
        return bytes;
    }
}
