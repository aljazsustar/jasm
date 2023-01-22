package types.constantPool.constants;

import enums.ConstantPoolTags;
import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;
import types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class ClassConstant extends ConstantPoolElement implements ClassFileElement {

    private Utf8Constant className;
    private Integer nameIndex;

    public ClassConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Class;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Utf8Constant getClassName() {
        return className;
    }

    public void setClassName(Utf8Constant className) {
        this.className = className;
    }

    public Integer getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(Integer nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public String toString() {
        return "ClassConstant{" +
                "className=" + className +
                ", nameIndex=" + nameIndex +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
