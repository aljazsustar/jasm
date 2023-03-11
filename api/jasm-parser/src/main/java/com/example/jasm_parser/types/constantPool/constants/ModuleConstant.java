package com.example.jasm_parser.types.constantPool.constants;

import com.example.jasm_parser.enums.ConstantPoolTags;
import com.example.jasm_parser.interfaces.ClassFileElement;
import com.example.jasm_parser.interfaces.ConstantPoolElement;
import com.example.jasm_parser.types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class ModuleConstant extends ConstantPoolElement implements ClassFileElement {

    private Integer nameIndex;
    private Utf8Constant name;

    public ModuleConstant(Integer constantPoolIndex) {
        this.tag = ConstantPoolTags.CONSTANT_Module;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Integer getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(Integer nameIndex) {
        this.nameIndex = nameIndex;
    }

    public Utf8Constant getName() {
        return name;
    }

    public void setName(Utf8Constant name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ModuleConstant{" +
                "nameIndex=" + nameIndex +
                ", name=" + name +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
