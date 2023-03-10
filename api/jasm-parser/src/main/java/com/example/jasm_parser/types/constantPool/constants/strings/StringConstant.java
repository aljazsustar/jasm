package com.example.jasm_parser.types.constantPool.constants.strings;

import com.example.jasm_parser.enums.ConstantPoolTags;
import com.example.jasm_parser.interfaces.ClassFileElement;
import com.example.jasm_parser.interfaces.ConstantPoolElement;
import com.example.jasm_parser.interfaces.ConstantValue;

import java.util.List;

public class StringConstant extends ConstantPoolElement implements ClassFileElement, ConstantValue {

    private Utf8Constant value;
    private Integer stringIndex;

    public StringConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_String;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Utf8Constant getValue() {
        return value;
    }

    public void setValue(Utf8Constant value) {
        this.value = value;
    }

    public Integer getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(Integer stringIndex) {
        this.stringIndex = stringIndex;
    }

    @Override
    public String toString() {
        return "StringConstant{" +
                "value=" + (value != null ? value.toString() : "") +
                ", stringIndex=" + stringIndex +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
