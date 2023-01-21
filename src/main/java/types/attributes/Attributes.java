package types.attributes;

import interfaces.ClassFileElement;

import java.util.List;

public class Attributes implements ClassFileElement {

    List<AttributeInfo> attributes;

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
