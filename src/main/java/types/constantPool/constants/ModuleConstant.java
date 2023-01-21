package types.constantPool.constants;

import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;

import java.util.List;

public class ModuleConstant extends ConstantPoolElement implements ClassFileElement {
    @Override
    public List<Byte> toHex() {
        return null;
    }
}
