package types.constantPool.constants.strings;

import enums.ConstantPoolTags;
import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;
import interfaces.ConstantValue;

import java.util.List;

public class Utf8Constant extends ConstantPoolElement implements ClassFileElement, ConstantValue {

    private String value;

    public Utf8Constant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Utf8;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Utf8Constant{" +
                "value='" + value + '\'' +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
