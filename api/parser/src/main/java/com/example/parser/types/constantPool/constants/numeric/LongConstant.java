package com.example.parser.types.constantPool.constants.numeric;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.interfaces.ConstantValue;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class LongConstant extends ConstantPoolElement implements ClassFileElement, ConstantValue {

    private Long value;

    public LongConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Long;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.value, 8));
        return bytes;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongConstant{" +
                "value=" + value +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
