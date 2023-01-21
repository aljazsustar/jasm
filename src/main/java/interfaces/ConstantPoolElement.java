package interfaces;

import enums.ConstantPoolTags;

public abstract class ConstantPoolElement implements ClassFileElement {
    protected ConstantPoolTags tag;
    protected Long constantPoolIndex;
}
