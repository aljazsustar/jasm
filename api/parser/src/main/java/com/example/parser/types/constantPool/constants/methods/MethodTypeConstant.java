package com.example.parser.types.constantPool.constants.methods;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;

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
        return null;
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
}
