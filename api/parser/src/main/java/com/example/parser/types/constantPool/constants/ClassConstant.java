package com.example.parser.types.constantPool.constants;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class ClassConstant extends ConstantPoolElement implements ClassFileElement {

    private Utf8Constant className;
    private Integer nameIndex;

    public ClassConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Class;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.nameIndex, 2));
        return bytes;
    }

    public Utf8Constant getClassName() {
        return className;
    }

    public void setClassName(Utf8Constant className) {
        this.className = className;
    }

    public Integer getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(Integer nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public String toString() {
        return "ClassConstant{" +
                "className=" + className +
                ", nameIndex=" + nameIndex +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
