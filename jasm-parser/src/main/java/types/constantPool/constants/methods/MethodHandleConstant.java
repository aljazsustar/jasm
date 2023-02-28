package types.constantPool.constants.methods;

import enums.ConstantPoolTags;
import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;

import java.util.List;

public class MethodHandleConstant extends ConstantPoolElement implements ClassFileElement {

    private Integer referenceKind;
    private Integer referenceIndex;

    public MethodHandleConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_MethodHandle;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Integer getReferenceKind() {
        return referenceKind;
    }

    public void setReferenceKind(Integer referenceKind) {
        this.referenceKind = referenceKind;
    }

    public Integer getReferenceIndex() {
        return referenceIndex;
    }

    public void setReferenceIndex(Integer referenceIndex) {
        this.referenceIndex = referenceIndex;
    }

    @Override
    public String toString() {
        return "MethodHandleConstant{" +
                "referenceKind=" + referenceKind +
                ", referenceIndex=" + referenceIndex +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
