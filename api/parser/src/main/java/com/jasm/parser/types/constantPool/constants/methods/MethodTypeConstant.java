package com.jasm.parser.types.constantPool.constants.methods;

import com.jasm.parser.enums.ConstantPoolTags;
import com.jasm.parser.interfaces.ArgVisitor;
import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.interfaces.ConstantPoolElement;
import com.jasm.parser.interfaces.Visitor;
import com.jasm.parser.types.constantPool.constants.strings.Utf8Constant;
import com.jasm.parser.util.WritingUtil;
import com.jasm.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.ArrayList;
import java.util.List;

public class MethodTypeConstant extends ConstantPoolElement implements ClassFileElement {

    private Integer descriptorIndex;
    private Utf8Constant descriptor;

    public MethodTypeConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_MethodType;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.descriptorIndex, 2));
        return bytes;
    }

    public Integer getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(Integer descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public Utf8Constant getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Utf8Constant descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public String toString() {
        return "MethodTypeConstant{" +
                "descriptorIndex=" + descriptorIndex +
                ", descriptor=" + descriptor +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(ArgVisitor<List<ConstantPoolElementJsonFormat>> visitor, List<ConstantPoolElementJsonFormat> arg) {
        visitor.visit(this, arg);
    }
}
