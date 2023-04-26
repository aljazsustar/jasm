package com.jasm.parser.types.attributes.util.types.annotations;

import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.types.attributes.util.types.annotations.elementValue.ElementValueType;
import com.jasm.parser.types.constantPool.constants.strings.Utf8Constant;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
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
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.elementNameIndex, 2));
        bytes.addAll(this.elementValue.toHex());
        return bytes;
    }
}
