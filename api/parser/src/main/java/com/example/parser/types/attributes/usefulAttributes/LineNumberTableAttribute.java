package com.example.parser.types.attributes.usefulAttributes;

import com.example.parser.interfaces.AttributeBase;
import com.example.parser.types.attributes.util.types.lineNumberTable.LineNumberTable;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class LineNumberTableAttribute extends AttributeBase {

    private LineNumberTable lineNumberTable;
    private Integer lineNumberTableLength;

    public LineNumberTableAttribute(Utf8Constant attributeName, Long attributeLength, Integer lineNumberTableLength, LineNumberTable lineNumberTable) {
        this.attributeName = attributeName;
        this.attributeLength = attributeLength;
        this.lineNumberTableLength = lineNumberTableLength;
        this.lineNumberTable = lineNumberTable;
    }

    public LineNumberTable getLineNumberTable() {
        return lineNumberTable;
    }

    public void setLineNumberTable(LineNumberTable lineNumberTable) {
        this.lineNumberTable = lineNumberTable;
    }

    public Integer getLineNumberTableLength() {
        return lineNumberTableLength;
    }

    public void setLineNumberTableLength(Integer lineNumberTableLength) {
        this.lineNumberTableLength = lineNumberTableLength;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.attributeNameIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.attributeLength, 4));
        bytes.addAll(WritingUtil.writeBytes(this.lineNumberTableLength, 2));
        bytes.addAll(this.lineNumberTable.toHex());
        return bytes;
    }

    @Override
    public String toString() {
        return "LineNumberTableAttribute{" +
                "lineNumberTable=" + lineNumberTable +
                ", lineNumberTableLength=" + lineNumberTableLength +
                ", attributeName=" + attributeName +
                ", attributeLength=" + attributeLength +
                '}';
    }
}
