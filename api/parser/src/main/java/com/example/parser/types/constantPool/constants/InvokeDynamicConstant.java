package com.example.parser.types.constantPool.constants;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ArgVisitor;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.interfaces.Visitor;
import com.example.parser.util.WritingUtil;
import com.example.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.ArrayList;
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
        return "InvokeDynamicConstant{" +
                "bootstrapMethodAttrIndex=" + bootstrapMethodAttrIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                ", nameAndType=" + nameAndType +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(ArgVisitor<List<ConstantPoolElementJsonFormat>> visitor, List<ConstantPoolElementJsonFormat> arg) {
        visitor.visit(this, arg);
    }
}
