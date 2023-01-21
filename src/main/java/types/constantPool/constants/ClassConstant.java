package types.constantPool.constants;

import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;
import types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class ClassConstant extends ConstantPoolElement implements ClassFileElement {

    private Utf8Constant className;

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
