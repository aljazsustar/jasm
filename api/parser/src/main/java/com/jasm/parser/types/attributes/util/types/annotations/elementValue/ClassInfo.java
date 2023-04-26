package com.jasm.parser.types.attributes.util.types.annotations.elementValue;

import com.jasm.parser.types.constantPool.constants.ClassConstant;
import com.jasm.parser.util.WritingUtil;

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
        return WritingUtil.writeBytes(this.classInfoIndex, 2);
    }
}
