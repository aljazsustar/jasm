package types.constantPool.constants;

import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;
import types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class NameAndTypeConstant extends ConstantPoolElement implements ClassFileElement {

    private Utf8Constant name;
    private Utf8Constant type;

    @Override
    public List<Byte> toHex() {
        return null;
    }

    @Override
    public String toString() {
        return "NameAndTypeConstant{" +
                "name=" + name.toString() +
                ", type=" + type.toString() +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
