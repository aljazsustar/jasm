package types.attributes;

import interfaces.AttributeBase;
import interfaces.ClassFileElement;

import java.util.ArrayList;
import java.util.List;

public class Attributes implements ClassFileElement {

    List<AttributeBase> attributes;

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

    @Override
    public List<Byte> toHex() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("attributes = [\n");

        for (AttributeBase attributeBase : this.attributes) {
            sb.append("\t{\n\t\t").append(attributeBase.toString()).append("\n\t}\n");
        }

        sb.append("]");
        return sb.toString();
    }
}
