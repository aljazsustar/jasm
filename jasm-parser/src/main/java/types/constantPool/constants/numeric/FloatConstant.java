package types.constantPool.constants.numeric;

import enums.ConstantPoolTags;
import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;
import interfaces.ConstantValue;

import java.util.List;

public class FloatConstant extends ConstantPoolElement implements ClassFileElement, ConstantValue {

    private Float value;

    public FloatConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Float;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FloatConstant{" +
                "value=" + value +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
