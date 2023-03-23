package com.example.parser.types.attributes.util.types.lineNumberTable;

import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
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
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.startPc, 2));
        bytes.addAll(WritingUtil.writeBytes(this.lineNumber, 2));
        return bytes;
    }
}
