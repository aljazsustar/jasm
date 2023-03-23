package com.example.parser.types.constantPool.constants.strings;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.interfaces.ConstantValue;
import com.example.parser.util.WritingUtil;

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
}
