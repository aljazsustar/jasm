package com.example.parser.types.attributes.util.types.annotations.elementValue;

import com.example.parser.types.constantPool.constants.ClassConstant;

import java.util.List;

public class ClassInfo extends ElementValueType {

    private Integer classInfoIndex;
    private ClassConstant classInfo;

    public ClassInfo(Integer classInfoIndex, ClassConstant classInfo) {
        this.classInfoIndex = classInfoIndex;
        this.classInfo = classInfo;
    }

    public Integer getClassInfoIndex() {
        return classInfoIndex;
    }

    public void setClassInfoIndex(Integer classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    public ClassConstant getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassConstant classInfo) {
        this.classInfo = classInfo;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
