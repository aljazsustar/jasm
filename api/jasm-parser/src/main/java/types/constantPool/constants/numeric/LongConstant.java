package types.constantPool.constants.numeric;

import enums.ConstantPoolTags;
import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;
import interfaces.ConstantValue;

import java.util.List;

public class LongConstant extends ConstantPoolElement implements ClassFileElement, ConstantValue {

    private Long value;

    public LongConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Long;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongConstant{" +
                "value=" + value +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
