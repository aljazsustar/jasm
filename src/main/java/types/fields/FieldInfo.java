package types.fields;

import enums.AccessFlags;
import interfaces.AttributeBase;
import interfaces.ClassFileElement;
import interfaces.FieldBase;
import types.attributes.Attributes;

import java.util.List;

public class FieldInfo extends FieldBase implements ClassFileElement {

    public FieldInfo(AccessFlags accessFlags, Integer nameIndex, Integer descriptorIndex, Integer attributesCount, Attributes attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
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

    @Override
    public String toString() {
        return "FieldInfo{" +
                "accessFlags=" + accessFlags +
                ", nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex +
                ", attributesCount=" + attributesCount +
                ", attributes=" + attributes +
                '}';
    }
}
