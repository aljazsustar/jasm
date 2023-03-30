package com.example.parser.types.constantPool.constants.strings;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.*;
import com.example.parser.util.WritingUtil;
import com.example.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.ArrayList;
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
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.stringIndex, 2));
        return bytes;
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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(ArgVisitor<List<ConstantPoolElementJsonFormat>> visitor, List<ConstantPoolElementJsonFormat> arg) {
        visitor.visit(this, arg);
    }
}
