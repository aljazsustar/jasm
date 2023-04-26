package com.jasm.parser.types.attributes.util.types.annotations.elementValue;

import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.types.attributes.util.types.annotations.ElementValue;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class ArrayValue<T extends ElementValueType> extends ElementValueType {
    private final List<ElementValue<?>> values;
    private Integer numValues;

    public ArrayValue(Integer numValues) {
        this.numValues = numValues;
        this.values = new ArrayList<>(numValues);
    }

    public Integer getNumValues() {
        return numValues;
    }

    public void setNumValues(Integer numValues) {
        this.numValues = numValues;
    }

    public List<ElementValue<?>> getValues() {
        return values;
    }

    public void addElementValue(ElementValue<? extends ElementValueType> elementValue) {
        this.values.add(elementValue);
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.numValues, 2));
        bytes.addAll(this.values
                .stream()
                .map(ClassFileElement::toHex)
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                }));
        return bytes;
    }
}
