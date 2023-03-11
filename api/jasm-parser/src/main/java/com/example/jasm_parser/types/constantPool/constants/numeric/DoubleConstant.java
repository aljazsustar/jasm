package com.example.jasm_parser.types.constantPool.constants.numeric;

import com.example.jasm_parser.enums.ConstantPoolTags;
import com.example.jasm_parser.interfaces.ClassFileElement;
import com.example.jasm_parser.interfaces.ConstantPoolElement;
import com.example.jasm_parser.interfaces.ConstantValue;

import java.util.List;

public class DoubleConstant extends ConstantPoolElement implements ClassFileElement, ConstantValue {

    private Double value;

    public DoubleConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Double;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DoubleConstant{" +
                "value=" + value +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
