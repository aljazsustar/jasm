package com.example.parser.types.fields;

import com.example.parser.enums.AccessFlags;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.FieldBase;
import com.example.parser.types.attributes.Attributes;

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
