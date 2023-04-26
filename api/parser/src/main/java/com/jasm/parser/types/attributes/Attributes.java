package com.jasm.parser.types.attributes;

import com.jasm.parser.interfaces.AttributeBase;
import com.jasm.parser.interfaces.ClassFileElement;

import java.util.ArrayList;
import java.util.List;

public class Attributes implements ClassFileElement {

    private final List<AttributeBase> attributes;

    public Attributes() {
        this.attributes = new ArrayList<>();
    }

    public Attributes(Integer attributesCount) {
        this.attributes = new ArrayList<>(attributesCount);
    }

    public void add(AttributeBase attribute) {
        this.attributes.add(attribute);
    }

    public void add(AttributeBase attribute, Integer index) {
        this.attributes.add(index, attribute);
    }

    public List<AttributeBase> getAttributes() {
        return attributes;
    }

    @Override
    public List<Byte> toHex() {
        return this.attributes
                .stream()
                .map(AttributeBase::toHex)
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("attributes = [\n");

        for (AttributeBase attributeBase : this.attributes) {
            sb.append("\t{\n\t\t").append(attributeBase != null ? attributeBase.toString() : "").append("\n\t}\n");
        }

        sb.append("]");
        return sb.toString();
    }
}
