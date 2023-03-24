package com.example.parser.types.attributes.util.types.annotations;

import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.types.attributes.util.types.annotations.elementValue.ElementValueType;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class ElementValue<T extends ElementValueType> implements ClassFileElement {
    private char tag;
    private T value;

    public ElementValue(char tag, T value) {
        this.tag = tag;
        this.value = value;
    }

    public char getTag() {
        return tag;
    }

    public void setTag(char tag) {
        this.tag = tag;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(Character.getNumericValue(this.tag), 1));
        bytes.addAll(this.value.toHex());
        return bytes;
    }
}
