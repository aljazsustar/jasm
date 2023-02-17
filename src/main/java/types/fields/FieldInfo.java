package types.fields;

import enums.AccessFlags;
import interfaces.AttributeBase;
import interfaces.ClassFileElement;
import interfaces.FieldBase;
import types.attributes.Attributes;

import java.util.List;

public class FieldInfo extends FieldBase implements ClassFileElement {

    public FieldInfo(Integer nameIndex, Integer descriptorIndex, Integer attributesCount) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = new Attributes();
    }

    public void addAttribute(AttributeBase attribute) {
        this.attributes.add(attribute);
    }

    public void addAccessFlags(Integer accessFlags) {
        this.accessFlags = new AccessFlags(accessFlags);
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
