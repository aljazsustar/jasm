package com.example.parser.types.constantPool;

import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.ConstantPoolElement;

import java.util.ArrayList;
import java.util.List;

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
}
