package com.example.parser.types.constantPool.constants;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class PackageConstant extends ConstantPoolElement implements ClassFileElement {

    private Integer nameIndex;
    private Utf8Constant name;

    public PackageConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Package;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Integer getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(Integer nameIndex) {
        this.nameIndex = nameIndex;
    }

    public Utf8Constant getName() {
        return name;
    }

    public void setName(Utf8Constant name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PackageConstant{" +
                "nameIndex=" + nameIndex +
                ", name=" + name +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}