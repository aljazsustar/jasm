package com.example.parser.types.attributes.optionalAttributes;

import com.example.parser.interfaces.AttributeBase;
import com.example.parser.types.attributes.util.types.annotations.Annotations;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class RuntimeVisibleAnnotations extends AttributeBase {

    private Integer numAnnotations;
    private Annotations annotations;

    public RuntimeVisibleAnnotations(Utf8Constant attributeName, Long attributeLength, Integer numAnnotations, Annotations annotations) {
        this.attributeName = attributeName;
        this.attributeLength = attributeLength;
        this.numAnnotations = numAnnotations;
        this.annotations = annotations;
    }

    public RuntimeVisibleAnnotations(Integer numAnnotations, Annotations attributes) {
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
        return null;
    }
}
