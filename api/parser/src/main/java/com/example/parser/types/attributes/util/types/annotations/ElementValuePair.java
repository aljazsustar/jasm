package com.example.parser.types.attributes.util.types.annotations;

import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.types.attributes.util.types.annotations.elementValue.ElementValueType;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class ElementValuePair<T extends ElementValueType> implements ClassFileElement {
    Integer elementNameIndex;
    Utf8Constant elementName;
    ElementValue<T> elementValue;

    public ElementValuePair(Integer elementNameIndex, Utf8Constant elementName, ElementValue<T> elementValue) {
        this.elementNameIndex = elementNameIndex;
        this.elementName = elementName;
        this.elementValue = elementValue;
    }

    public Integer getElementNameIndex() {
        return elementNameIndex;
    }

    public void setElementNameIndex(Integer elementNameIndex) {
        this.elementNameIndex = elementNameIndex;
    }

    public Utf8Constant getElementName() {
        return elementName;
    }

    public void setElementName(Utf8Constant elementName) {
        this.elementName = elementName;
    }

    public ElementValue<T> getElementValue() {
        return elementValue;
    }

    public void setElementValue(ElementValue<T> elementValue) {
        this.elementValue = elementValue;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
