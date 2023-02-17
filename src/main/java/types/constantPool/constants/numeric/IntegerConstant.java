package types.constantPool.constants.numeric;

import enums.ConstantPoolTags;
import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;
import interfaces.ConstantValue;

import java.util.List;

public class IntegerConstant extends ConstantPoolElement implements ClassFileElement, ConstantValue {

    private Integer value;

    public IntegerConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Integer;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "IntegerConstant{" +
                "value=" + value +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
