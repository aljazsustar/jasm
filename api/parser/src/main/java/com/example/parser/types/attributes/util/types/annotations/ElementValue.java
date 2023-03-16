package com.example.parser.types.attributes.util.types.annotations;

import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.types.attributes.util.types.annotations.elementValue.ElementValueType;

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
        return null;
    }
}
