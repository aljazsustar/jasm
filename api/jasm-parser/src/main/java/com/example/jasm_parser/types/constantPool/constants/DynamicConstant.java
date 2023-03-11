package com.example.jasm_parser.types.constantPool.constants;

import com.example.jasm_parser.enums.ConstantPoolTags;
import com.example.jasm_parser.interfaces.ClassFileElement;
import com.example.jasm_parser.interfaces.ConstantPoolElement;

import java.util.List;

public class DynamicConstant extends ConstantPoolElement implements ClassFileElement {

    private Integer bootstrapMethodAttrIndex;
    //TODO: add bootstapMethodAttr type
    private Integer nameAndTypeIndex;
    private NameAndTypeConstant nameAndType;

    public DynamicConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Dynamic;
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
        return "DynamicConstant{" +
                "bootstrapMethodAttrIndex=" + bootstrapMethodAttrIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                ", nameAndType=" + nameAndType +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
