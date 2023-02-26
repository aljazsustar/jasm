package types.attributes.criticalAttributes;

import interfaces.AttributeBase;
import interfaces.ClassFileElement;

import java.util.List;

public class StackMapTableAttribute extends AttributeBase implements ClassFileElement {
    @Override
    public List<Byte> toHex() {
        return null;
    }
}
