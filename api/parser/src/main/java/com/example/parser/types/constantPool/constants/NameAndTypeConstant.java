package com.example.parser.types.constantPool.constants;

import com.example.parser.enums.ConstantPoolTags;
import com.example.parser.interfaces.ArgVisitor;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;
import com.example.parser.interfaces.Visitor;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;
import com.example.parser.util.WritingUtil;
import com.example.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.ArrayList;
import java.util.List;

public class NameAndTypeConstant extends ConstantPoolElement implements ClassFileElement {

    private Utf8Constant name;
    private Utf8Constant type;
    private Integer nameIndex;
    private Integer descriptorIndex;

    public NameAndTypeConstant(Integer constantPoolIndex) {
        this.constantPoolIndex = constantPoolIndex;
        this.tag = ConstantPoolTags.CONSTANT_NameAndType;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        bytes.addAll(WritingUtil.writeBytes(this.tag, 1));
        bytes.addAll(WritingUtil.writeBytes(this.nameIndex, 2));
        bytes.addAll(WritingUtil.writeBytes(this.descriptorIndex, 2));
        return bytes;
    }

    public Utf8Constant getName() {
        return name;
    }

    public void setName(Utf8Constant name) {
        this.name = name;
    }

    public Utf8Constant getType() {
        return type;
    }

    public void setType(Utf8Constant type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "NameAndTypeConstant{" +
                "name=" + (name != null ? name.toString() : "") +
                ", type=" + (type != null ? type.toString() : "") +
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
