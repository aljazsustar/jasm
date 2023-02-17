package types.attributes.criticalAttributes;

import interfaces.AttributeBase;
import interfaces.ConstantValue;

import java.util.List;

public class ConstantValueAttribute extends AttributeBase {

    private Integer attributeNameIndex;
    private Integer constantValueIndex;
    private ConstantValue constantValue;

    public ConstantValueAttribute(Integer attributeNameIndex, Long attributeLength, Integer constantValueIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }


}
