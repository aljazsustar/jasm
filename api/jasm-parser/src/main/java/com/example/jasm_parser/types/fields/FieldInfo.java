package com.example.jasm_parser.types.fields;

import com.example.jasm_parser.enums.AccessFlags;
import com.example.jasm_parser.interfaces.ClassFileElement;
import com.example.jasm_parser.interfaces.FieldBase;
import com.example.jasm_parser.types.attributes.Attributes;

import java.util.List;

public class FieldInfo extends FieldBase implements ClassFileElement {

    public FieldInfo(AccessFlags accessFlags, Integer nameIndex, Integer descriptorIndex, Integer attributesCount, Attributes attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "accessFlags=" + accessFlags +
                ", nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex +
                ", attributesCount=" + attributesCount +
                ", attributes=" + attributes +
                '}';
    }
}
