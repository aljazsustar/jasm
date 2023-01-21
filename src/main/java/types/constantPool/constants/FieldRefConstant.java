package types.constantPool.constants;

import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;

import java.util.List;

public class FieldRefConstant extends ConstantPoolElement implements ClassFileElement {

    NameAndTypeConstant nameAndTypeConstant;

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
