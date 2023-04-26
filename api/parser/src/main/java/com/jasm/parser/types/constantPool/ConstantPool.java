package com.jasm.parser.types.constantPool;

import com.jasm.parser.enums.ConstantPoolTags;
import com.jasm.parser.interfaces.ArgVisitor;
import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.interfaces.ConstantPoolElement;
import com.jasm.parser.interfaces.Visitor;
import com.jasm.parser.types.constantPool.constants.methods.MethodRefConstant;
import com.jasm.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConstantPool implements ClassFileElement {
    List<ConstantPoolElement> elements;

    public ConstantPool(Integer constantPoolSize) {
        this.elements = new ArrayList<>(constantPoolSize);
    }

    @Override
    public List<Byte> toHex() {
        return this.elements
                .stream()
                .map(el -> {
                    if (el != null) return el.toHex();
                    else return new ArrayList<Byte>();
                })
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }

    public void addToConstantPool(ConstantPoolElement element) {
        if (element != null)
            elements.add(element.getConstantPoolIndex(), element);
        else
            elements.add(element);
    }

    public Integer getSize() {
        return this.elements.size();
    }

    public List<MethodRefConstant> getMethods() {
        return this.elements
                .stream()
                .filter(el -> el.getTag() == ConstantPoolTags.CONSTANT_Methodref)
                .map(el -> (MethodRefConstant) el)
                .collect(Collectors.toList());
    }

    public MethodRefConstant getMethodByMethodName(String name) {
        return this.elements
                .stream()
                .filter(el -> el.getTag() == ConstantPoolTags.CONSTANT_Methodref)
                .map(el -> (MethodRefConstant) el)
                .filter(el -> el.getNameAndTypeConstant().getName().getValue().equals(name))
                .findFirst()
                .orElseThrow();
    }

    public MethodRefConstant getMethodByFullyQualifiedName(String type, String name) {
        return this.elements
                .stream()
                .filter(el -> el.getTag() == ConstantPoolTags.CONSTANT_Methodref)
                .map(el -> (MethodRefConstant) el)
                .filter(el -> el.getClassConstant().getClassName().getValue().equals(type)
                        && el.getNameAndTypeConstant().getName().getValue().equals(name))
                .findFirst().orElseThrow();
    }

    public ConstantPoolElement getConstantPoolElement(Integer constantPoolIndex) {
        return this.elements.get(constantPoolIndex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n  ConstantPool {\n   elements = \n    {");

        elements.forEach(el -> sb.append(el != null ? el.toString() : ""));

        sb.append("  }");
        sb.append("\n  }");
        return sb.toString();
    }

    public void accept(Visitor v) {
        for (ConstantPoolElement element : this.elements) {
            element.accept(v);
        }
    }

    public void accept(ArgVisitor<List<ConstantPoolElementJsonFormat>> visitor, List<ConstantPoolElementJsonFormat> arg) {
        for (ConstantPoolElement element : this.elements) {
            element.accept(visitor, arg);
        }
    }
}
