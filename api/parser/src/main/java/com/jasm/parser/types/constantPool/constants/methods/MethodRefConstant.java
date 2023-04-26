package com.jasm.parser.types.constantPool.constants.methods;

import com.jasm.parser.enums.ConstantPoolTags;
import com.jasm.parser.interfaces.ArgVisitor;
import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.interfaces.ConstantPoolElement;
import com.jasm.parser.interfaces.Visitor;
import com.jasm.parser.types.constantPool.constants.ClassConstant;
import com.jasm.parser.types.constantPool.constants.NameAndTypeConstant;
import com.jasm.parser.util.WritingUtil;
import com.jasm.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.ArrayList;
import java.util.List;

public class MethodRefConstant extends ConstantPoolElement implements ClassFileElement {

    private NameAndTypeConstant nameAndTypeConstant;
    private ClassConstant classConstant;
    private Integer nameAndTypeIndex;
    private Integer classConstantIndex;

    public MethodRefConstant(Integer constantPoolIndex) {
        this.constantPoolIndex = constantPoolIndex;
        this.tag = ConstantPoolTags.CONSTANT_Methodref;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.classConstantIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.nameAndTypeIndex, 2));
        return bytes;
    }

    public NameAndTypeConstant getNameAndTypeConstant() {
        return nameAndTypeConstant;
    }

    public void setNameAndTypeConstant(NameAndTypeConstant nameAndTypeConstant) {
        this.nameAndTypeConstant = nameAndTypeConstant;
    }

    public Integer getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(Integer nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public ClassConstant getClassConstant() {
        return classConstant;
    }

    public void setClassConstant(ClassConstant classConstant) {
        this.classConstant = classConstant;
    }

    public Integer getClassConstantIndex() {
        return classConstantIndex;
    }

    public void setClassConstantIndex(Integer classConstantIndex) {
        this.classConstantIndex = classConstantIndex;
    }

    @Override
    public String toString() {
        return "MethodRefConstant {" +
                "nameAndTypeConstant=" + (nameAndTypeConstant != null ? nameAndTypeConstant.toString() : "") +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                ", classConstantIndex=" + classConstantIndex +
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
