package com.jasm.parser.types.constantPool.constants.strings;

import com.jasm.parser.enums.ConstantPoolTags;
import com.jasm.parser.interfaces.*;
import com.jasm.parser.util.WritingUtil;
import com.jasm.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.ArrayList;
import java.util.List;

public class Utf8Constant extends ConstantPoolElement implements ClassFileElement, ConstantValue {

    private String value;

    public Utf8Constant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Utf8;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.value.length(), 2));
        bytes.addAll(WritingUtil.primitiveToObjectByteList(this.value.getBytes()));
        return bytes;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Utf8Constant{" +
                "value='" + value + '\'' +
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
