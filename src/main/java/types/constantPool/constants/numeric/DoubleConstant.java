package types.constantPool.constants.numeric;

import enums.ConstantPoolTags;
import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;

import java.util.List;

public class DoubleConstant extends ConstantPoolElement implements ClassFileElement {

    private Double value;

    public DoubleConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Double;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DoubleConstant{" +
                "value=" + value +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
