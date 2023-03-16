package com.example.parser.types.constantPool.constants.methods;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.types.constantPool.constants.ClassConstant;
import com.example.parser.types.constantPool.constants.NameAndTypeConstant;

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
        return null;
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
}
