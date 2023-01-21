package types.attributes;

import interfaces.AttributeBase;
import interfaces.ClassFileElement;

import java.util.List;

public class Attributes implements ClassFileElement {

    List<AttributeBase> attributes;

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
