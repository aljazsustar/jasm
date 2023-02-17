package interfaces;

import enums.AccessFlags;
import types.attributes.Attributes;

public abstract class FieldBase {
    protected AccessFlags accessFlags;
    protected Integer nameIndex;
    protected Integer descriptorIndex;
    protected Integer attributesCount;
    protected Attributes attributes;
}
