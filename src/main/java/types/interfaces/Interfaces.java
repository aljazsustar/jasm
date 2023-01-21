package types.interfaces;

import interfaces.ClassFileElement;
import types.constantPool.constants.ClassConstant;

import java.util.List;

public class Interfaces implements ClassFileElement {

    List<ClassConstant> interfaces;

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
