package com.example.parser.types.constantPool.constants.methods;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ArgVisitor;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.interfaces.Visitor;
import com.example.parser.types.constantPool.constants.ClassConstant;
import com.example.parser.types.constantPool.constants.NameAndTypeConstant;
import com.example.parser.util.WritingUtil;
import com.example.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.ArrayList;
import java.util.List;

public class InterfaceMethodRefConstant extends ConstantPoolElement implements ClassFileElement {

    private ClassConstant classConstant;
    private NameAndTypeConstant nameAndTypeConstant;
    private Integer classIndex;
    private Integer nameAndTypeIndex;

    public InterfaceMethodRefConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_InterfaceMethodref;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.classIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.nameAndTypeIndex, 2));
        return bytes;
    }

    public ClassConstant getClassConstant() {
        return classConstant;
    }

    public void setClassConstant(ClassConstant classConstant) {
        this.classConstant = classConstant;
    }

    public NameAndTypeConstant getNameAndTypeConstant() {
        return nameAndTypeConstant;
    }

    public void setNameAndTypeConstant(NameAndTypeConstant nameAndTypeConstant) {
        this.nameAndTypeConstant = nameAndTypeConstant;
    }

    public Integer getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(Integer classIndex) {
        this.classIndex = classIndex;
    }

    public Integer getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(Integer nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public String toString() {
        return "InterfaceMethodRefConstant{" +
                "classConstant=" + classConstant +
                ", nameAndTypeConstant=" + nameAndTypeConstant +
                ", classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
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
