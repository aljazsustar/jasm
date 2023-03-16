package com.example.parser.types.attributes.util.types.lineNumberTable;

import com.example.parser.interfaces.ClassFileElement;

import java.util.List;

public class LineNumberTableElement implements ClassFileElement {

    Integer startPc;
    Integer lineNumber;

    public LineNumberTableElement(Integer startPc, Integer lineNumber) {
        this.startPc = startPc;
        this.lineNumber = lineNumber;
    }

    public Integer getStartPc() {
        return startPc;
    }

    public void setStartPc(Integer startPc) {
        this.startPc = startPc;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "LineNumberTableElement{" +
                "startPc=" + startPc +
                ", lineNumber=" + lineNumber +
                '}';
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
