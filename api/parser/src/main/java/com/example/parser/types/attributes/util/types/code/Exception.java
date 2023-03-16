package com.example.parser.types.attributes.util.types.code;

import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.types.constantPool.constants.ClassConstant;

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
        return null;
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
