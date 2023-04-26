package com.jasm.parser.types.attributes.util.types.annotations;

import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.types.constantPool.constants.strings.Utf8Constant;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class Annotation implements ClassFileElement {

    private Integer typeIndex;
    private Utf8Constant type;
    private Integer numElementValuePairs;
    private ElementValuePairs elementValuePairs;

    public Annotation(Integer typeIndex, Utf8Constant type, Integer numElementValuePairs, ElementValuePairs elementValuePairs) {
        this.typeIndex = typeIndex;
        this.type = type;
        this.numElementValuePairs = numElementValuePairs;
        this.elementValuePairs = elementValuePairs;
    }

    public Integer getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(Integer typeIndex) {
        this.typeIndex = typeIndex;
    }

    public Utf8Constant getType() {
        return type;
    }

    public void setType(Utf8Constant type) {
        this.type = type;
    }

    public Integer getNumElementValuePairs() {
        return numElementValuePairs;
    }

    public void setNumElementValuePairs(Integer numElementValuePairs) {
        this.numElementValuePairs = numElementValuePairs;
    }

    public ElementValuePairs getElementValuePairs() {
        return elementValuePairs;
    }

    public void setElementValuePairs(ElementValuePairs elementValuePairs) {
        this.elementValuePairs = elementValuePairs;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.typeIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.numElementValuePairs, 2));
        bytes.addAll(this.elementValuePairs.toHex());
        return bytes;
    }
}
