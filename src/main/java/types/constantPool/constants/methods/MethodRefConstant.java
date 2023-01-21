package types.constantPool.constants.methods;

import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;
import types.constantPool.constants.NameAndTypeConstant;

import java.util.List;

public class MethodRefConstant extends ConstantPoolElement implements ClassFileElement {

    NameAndTypeConstant nameAndTypeConstant;

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
