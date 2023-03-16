package com.example.parser.types.attributes.util.types.annotations.elementValue;

import com.example.parser.types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class EnumConstValue extends ElementValueType {
    private Integer typeNameIndex;
    private Integer constNameIndex;

    private Utf8Constant typeName;
    private Utf8Constant constName;

    public EnumConstValue(Integer typeNameIndex, Integer constNameIndex, Utf8Constant typeName, Utf8Constant constName) {
        this.typeNameIndex = typeNameIndex;
        this.constNameIndex = constNameIndex;
        this.typeName = typeName;
        this.constName = constName;
    }

    public Integer getTypeNameIndex() {
        return typeNameIndex;
    }

    public void setTypeNameIndex(Integer typeNameIndex) {
        this.typeNameIndex = typeNameIndex;
    }

    public Integer getConstNameIndex() {
        return constNameIndex;
    }

    public void setConstNameIndex(Integer constNameIndex) {
        this.constNameIndex = constNameIndex;
    }

    public Utf8Constant getTypeName() {
        return typeName;
    }

    public void setTypeName(Utf8Constant typeName) {
        this.typeName = typeName;
    }

    public Utf8Constant getConstName() {
        return constName;
    }

    public void setConstName(Utf8Constant constName) {
        this.constName = constName;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
