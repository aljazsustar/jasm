package com.example.jasm_parser.types.constantPool.constants.numeric;

import com.example.jasm_parser.enums.ConstantPoolTags;
import com.example.jasm_parser.interfaces.ClassFileElement;
import com.example.jasm_parser.interfaces.ConstantPoolElement;
import com.example.jasm_parser.interfaces.ConstantValue;

import java.util.List;

public class FloatConstant extends ConstantPoolElement implements ClassFileElement, ConstantValue {

    private Float value;

    public FloatConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Float;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
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
}
