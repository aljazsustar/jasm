package com.example.jasm_parser.types.attributes.criticalAttributes;

import com.example.jasm_parser.interfaces.AttributeBase;
import com.example.jasm_parser.types.attributes.Attributes;
import com.example.jasm_parser.types.attributes.util.types.code.Code;
import com.example.jasm_parser.types.attributes.util.types.code.Exceptions;

import java.util.List;

public class CodeAttribute extends AttributeBase {

    private Integer maxStack;
    private Integer maxLocals;
    private Long codeLength;
    private Code code;
    private Integer exceptionTableLength;
    private Exceptions exceptionTable;
    private Integer attributesCount;
    private Attributes attributes;

    public CodeAttribute(Long attributeLength, Integer maxStack, Integer maxLocals, Long codeLength, Code code, Integer exceptionTableLength,
                         Exceptions exceptionTable, Integer attributesCount, Attributes attributes) {
        this.attributeLength = attributeLength;
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLength = codeLength;
        this.code = code;
        this.exceptionTableLength = exceptionTableLength;
        this.exceptionTable = exceptionTable;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Integer getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(Integer maxStack) {
        this.maxStack = maxStack;
    }

    public Integer getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(Integer maxLocals) {
        this.maxLocals = maxLocals;
    }

    public Long getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Long codeLength) {
        this.codeLength = codeLength;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Integer getExceptionTableLength() {
        return exceptionTableLength;
    }

    public void setExceptionTableLength(Integer exceptionTableLength) {
        this.exceptionTableLength = exceptionTableLength;
    }

    public Exceptions getExceptionTable() {
        return exceptionTable;
    }

    public void setExceptionTable(Exceptions exceptionTable) {
        this.exceptionTable = exceptionTable;
    }

    public Integer getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(Integer attributesCount) {
        this.attributesCount = attributesCount;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "CodeAttribute{" +
                "maxStack=" + maxStack +
                ", maxLocals=" + maxLocals +
                ", codeLength=" + codeLength +
                ", code=" + code +
                ", exceptionTableLength=" + exceptionTableLength +
                ", exceptionTable=" + exceptionTable +
                ", attributesCount=" + attributesCount +
                ", attributes=" + attributes +
                '}';
    }
}
