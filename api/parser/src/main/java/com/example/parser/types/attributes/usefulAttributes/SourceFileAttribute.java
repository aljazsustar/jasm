package com.example.parser.types.attributes.usefulAttributes;

import com.example.parser.interfaces.AttributeBase;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class SourceFileAttribute extends AttributeBase {

    private Integer sourceFileIndex;
    private Utf8Constant sourceFileName;

    public SourceFileAttribute(Utf8Constant attributeName, Long attributeLength, Integer sourceFileIndex, Utf8Constant sourceFileName) {
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
        return null;
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
