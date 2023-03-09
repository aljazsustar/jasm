package types.constantPool.constants.methods;

import enums.ConstantPoolTags;
import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;
import types.constantPool.constants.ClassConstant;
import types.constantPool.constants.NameAndTypeConstant;

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
        return null;
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
}
