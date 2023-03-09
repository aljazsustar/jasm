package interfaces;

import enums.AccessFlags;
import types.attributes.Attributes;

public abstract class MethodBase {

    protected AccessFlags accessFlags;
    protected Integer nameIndex;
    protected Integer descriptorIndex;
    protected Integer attributesCount;
    protected Attributes attributes;

    public AccessFlags getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(AccessFlags accessFlags) {
        this.accessFlags = accessFlags;
    }

    public Integer getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(Integer nameIndex) {
        this.nameIndex = nameIndex;
    }

    public Integer getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(Integer descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public Integer getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(Integer attributesCount) {
        this.attributesCount = attributesCount;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
