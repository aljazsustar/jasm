package com.jasm.parser.types.attributes.util.types.annotations.elementValue;

import com.jasm.parser.interfaces.ConstantValue;
import com.jasm.parser.util.WritingUtil;

import java.util.List;

public class ConstValue extends ElementValueType {
    private Integer constValueIndex;
    private ConstantValue constValue;

    public ConstValue(Integer constValueIndex, ConstantValue constValue) {
        this.constValueIndex = constValueIndex;
        this.constValue = constValue;
    }

    public Integer getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(Integer constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

    public ConstantValue getConstValue() {
        return constValue;
    }

    public void setConstValue(ConstantValue constValue) {
        this.constValue = constValue;
    }

    @Override
    public List<Byte> toHex() {
        return WritingUtil.writeBytes(this.constValueIndex, 2);
    }
}
