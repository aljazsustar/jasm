package com.jasm.parser.types.fields;

import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.enums.AccessFlags;
import com.jasm.parser.interfaces.FieldBase;
import com.jasm.parser.types.attributes.Attributes;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
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
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.accessFlags.getIntValue(), 2));
        bytes.addAll(WritingUtil.writeBytes(this.nameIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.descriptorIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.attributesCount, 2));
        bytes.addAll(this.attributes.toHex());
        return bytes;
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
