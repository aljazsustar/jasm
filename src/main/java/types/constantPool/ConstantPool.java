package types.constantPool;

import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;

import java.util.List;

public class ConstantPool implements ClassFileElement {
    List<ConstantPoolElement> elements;

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
