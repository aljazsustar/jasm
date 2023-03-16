package com.example.parser.types.constantPool.constants;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;

import java.util.List;

public class InvokeDynamicConstant extends ConstantPoolElement implements ClassFileElement {

    private Integer bootstrapMethodAttrIndex;
    //TODO: dodaj tip za bootstrapMethodAttribute
    private Integer nameAndTypeIndex;
    private NameAndTypeConstant nameAndType;

    public InvokeDynamicConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_InvokeDynamic;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Integer getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public void setBootstrapMethodAttrIndex(Integer bootstrapMethodAttrIndex) {
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
    }

    public Integer getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(Integer nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public NameAndTypeConstant getNameAndType() {
        return nameAndType;
    }

    public void setNameAndType(NameAndTypeConstant nameAndType) {
        this.nameAndType = nameAndType;
    }

    @Override
    public String toString() {
        return "InvokeDynamicConstant{" +
                "bootstrapMethodAttrIndex=" + bootstrapMethodAttrIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                ", nameAndType=" + nameAndType +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
