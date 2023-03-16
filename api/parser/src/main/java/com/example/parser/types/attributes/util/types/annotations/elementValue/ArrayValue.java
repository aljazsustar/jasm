package com.example.parser.types.attributes.util.types.annotations.elementValue;

import com.example.parser.types.attributes.util.types.annotations.ElementValue;

import java.util.ArrayList;
import java.util.List;

public class ArrayValue<T extends ElementValueType> extends ElementValueType {
    private Integer numValues;
    private List<ElementValue<?>> values;

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
        return null;
    }
}
