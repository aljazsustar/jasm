package com.example.jasm_parser.types.constantPool.constants;

import com.example.jasm_parser.enums.ConstantPoolTags;
import com.example.jasm_parser.interfaces.ClassFileElement;
import com.example.jasm_parser.interfaces.ConstantPoolElement;

import java.util.List;

public class FieldRefConstant extends ConstantPoolElement implements ClassFileElement {

    private NameAndTypeConstant nameAndTypeConstant;
    private ClassConstant classConstant;
    private Integer classIndex;
    private Integer nameAndTypeIndex;

    public FieldRefConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Fieldref;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public NameAndTypeConstant getNameAndTypeConstant() {
        return nameAndTypeConstant;
    }

    public void setNameAndTypeConstant(NameAndTypeConstant nameAndTypeConstant) {
        this.nameAndTypeConstant = nameAndTypeConstant;
    }

    public ClassConstant getClassConstant() {
        return classConstant;
    }

    public void setClassConstant(ClassConstant classConstant) {
        this.classConstant = classConstant;
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
        return "FieldRefConstant{" +
                "nameAndTypeConstant=" + (nameAndTypeConstant != null ? nameAndTypeConstant.toString() : "") +
                ", classConstant=" + (classConstant != null ? classConstant.toString() : "") +
                ", classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
