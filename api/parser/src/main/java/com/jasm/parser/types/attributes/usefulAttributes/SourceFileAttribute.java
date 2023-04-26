package com.jasm.parser.types.attributes.usefulAttributes;

import com.jasm.parser.interfaces.AttributeBase;
import com.jasm.parser.types.constantPool.constants.strings.Utf8Constant;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class SourceFileAttribute extends AttributeBase {

    private Integer sourceFileIndex;
    private Utf8Constant sourceFileName;

    public SourceFileAttribute(Integer attributeNameIndex, Utf8Constant attributeName, Long attributeLength, Integer sourceFileIndex, Utf8Constant sourceFileName) {
        this.attributeNameIndex = attributeNameIndex;
        this.sourceFileIndex = sourceFileIndex;
        this.sourceFileName = sourceFileName;
        this.attributeName = attributeName;
        this.attributeLength = attributeLength;
    }


    public Integer getSourceFileIndex() {
        return sourceFileIndex;
    }

    public void setSourceFileIndex(Integer sourceFileIndex) {
        this.sourceFileIndex = sourceFileIndex;
    }

    public Utf8Constant getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(Utf8Constant sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.attributeNameIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.attributeLength, 4));
        bytes.addAll(WritingUtil.writeBytes(this.sourceFileIndex, 2));
        return bytes;
    }

    @Override
    public String toString() {
        return "SourceFileAttribute{" +
                "sourceFileIndex=" + sourceFileIndex +
                ", sourceFileName=" + sourceFileName +
                ", attributeName=" + attributeName +
                ", attributeLength=" + attributeLength +
                '}';
    }
}
