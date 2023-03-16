package com.example.parser.types.attributes.util.types.annotations.elementValue;

import com.example.parser.types.attributes.util.types.annotations.Annotation;

import java.util.List;

public class AnnotationValue extends ElementValueType {
    private Annotation annotationValue;

    public AnnotationValue(Annotation annotationValue) {
        this.annotationValue = annotationValue;
    }

    public Annotation getAnnotationValue() {
        return annotationValue;
    }

    public void setAnnotationValue(Annotation annotationValue) {
        this.annotationValue = annotationValue;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
