package com.example.parser.types.constantPool.constants.numeric;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.*;
import com.example.parser.util.WritingUtil;
import com.example.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.ArrayList;
import java.util.List;

public class FloatConstant extends ConstantPoolElement implements ClassFileElement, ConstantValue {

    private Float value;

    public FloatConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Float;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.value, 4));
        return bytes;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FloatConstant{" +
                "value=" + value +
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
