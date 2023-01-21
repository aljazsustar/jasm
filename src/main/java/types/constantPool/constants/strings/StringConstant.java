package types.constantPool.constants.strings;

import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;

import java.util.List;

public class StringConstant extends ConstantPoolElement implements ClassFileElement {

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
