package types.attributes;

import interfaces.AttributeBase;
import interfaces.ClassFileElement;

import java.util.ArrayList;
import java.util.List;

public class Attributes implements ClassFileElement {

    List<AttributeBase> attributes;

    public Attributes() {
        this.attributes = new ArrayList<>();
    }

    public void add(AttributeBase attribute) {
        this.attributes.add(attribute);
    }

    public void add(AttributeBase attribute, Integer index) {
        this.attributes.add(index, attribute);
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
