package com.example.parser.types.constantPool.constants;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
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
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.bootstrapMethodAttrIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.nameAndTypeIndex, 2));
        return bytes;
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
