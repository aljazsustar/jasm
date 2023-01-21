package types.methods;

import interfaces.ClassFileElement;

import java.util.List;

public class Methods implements ClassFileElement {

    List<MethodInfo> methods;

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
