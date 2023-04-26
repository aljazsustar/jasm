package com.jasm.parser.interfaces;

import com.jasm.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.List;

public abstract class ConstantPoolElement implements ClassFileElement {
    protected int tag;
    protected Integer constantPoolIndex;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Integer getConstantPoolIndex() {
        return constantPoolIndex;
    }

    public void setConstantPoolIndex(Integer constantPoolIndex) {
        this.constantPoolIndex = constantPoolIndex;
    }

    public abstract void accept(Visitor visitor);

    public abstract void accept(ArgVisitor<List<ConstantPoolElementJsonFormat>> visitor, List<ConstantPoolElementJsonFormat> arg);
}
