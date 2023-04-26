package com.jasm.parser.types.attributes.util.types.code;

import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.types.constantPool.constants.ClassConstant;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class Exception implements ClassFileElement {

    Integer startPc;
    Integer endPc;
    Integer handlerPc;
    Integer catchTypeIndex;
    ClassConstant catchType;

    public Exception(Integer startPc, Integer endPc, Integer handlerPc, Integer catchTypeIndex, ClassConstant catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchTypeIndex = catchTypeIndex;
        this.catchType = catchType;
    }

    public Integer getStartPc() {
        return startPc;
    }

    public void setStartPc(Integer startPc) {
        this.startPc = startPc;
    }

    public Integer getEndPc() {
        return endPc;
    }

    public void setEndPc(Integer endPc) {
        this.endPc = endPc;
    }

    public Integer getHandlerPc() {
        return handlerPc;
    }

    public void setHandlerPc(Integer handlerPc) {
        this.handlerPc = handlerPc;
    }

    public Integer getCatchTypeIndex() {
        return catchTypeIndex;
    }

    public void setCatchTypeIndex(Integer catchTypeIndex) {
        this.catchTypeIndex = catchTypeIndex;
    }

    public ClassConstant getCatchType() {
        return catchType;
    }

    public void setCatchType(ClassConstant catchType) {
        this.catchType = catchType;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.startPc, 2));
        bytes.addAll(WritingUtil.writeBytes(this.endPc, 2));
        bytes.addAll(WritingUtil.writeBytes(this.handlerPc, 2));
        bytes.addAll(WritingUtil.writeBytes(this.catchTypeIndex, 2));
        return bytes;
    }

    @Override
    public String toString() {
        return "Exception{" +
                "startPc=" + startPc +
                ", endPc=" + endPc +
                ", handlerPc=" + handlerPc +
                ", catchTypeIndex=" + catchTypeIndex +
                ", catchType=" + catchType +
                '}';
    }
}
