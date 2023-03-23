package com.example.parser.types.constantPool.constants.methods;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class MethodHandleConstant extends ConstantPoolElement implements ClassFileElement {

    private Integer referenceKind;
    private Integer referenceIndex;

    public MethodHandleConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_MethodHandle;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.referenceKind, 1));
        bytes.addAll(WritingUtil.writeBytes(this.referenceKind, 2));
        return bytes;
    }

    public Integer getReferenceKind() {
        return referenceKind;
    }

    public void setReferenceKind(Integer referenceKind) {
        this.referenceKind = referenceKind;
    }

    public Integer getReferenceIndex() {
        return referenceIndex;
    }

    public void setReferenceIndex(Integer referenceIndex) {
        this.referenceIndex = referenceIndex;
    }

    @Override
    public String toString() {
        return "MethodHandleConstant{" +
                "referenceKind=" + referenceKind +
                ", referenceIndex=" + referenceIndex +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
