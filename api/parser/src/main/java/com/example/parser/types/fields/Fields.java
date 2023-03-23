package com.example.parser.types.fields;

import com.example.parser.interfaces.ClassFileElement;

import java.util.ArrayList;
import java.util.List;

public class Fields implements ClassFileElement {

    List<FieldInfo> fields;

    public Fields(Integer fieldsSize) {
        this.fields = new ArrayList<>(fieldsSize);
    }

    public Fields() {
        this.fields = new ArrayList<>(10);
    }

    @Override
    public List<Byte> toHex() {
        return this.fields
                .stream()
                .map(ClassFileElement::toHex)
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }

    public List<FieldInfo> getFields() {
        return fields;
    }

    public void setFields(List<FieldInfo> fields) {
        this.fields = fields;
    }

    public void addField(FieldInfo fieldInfo) {
        this.fields.add(fieldInfo);
    }

    public void addField(Integer index, FieldInfo fieldInfo) {
        this.fields.add(index, fieldInfo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fields = [\n");

        for (FieldInfo field : this.fields) {
            sb.append("\t{\n\t\t").append(field.toString()).append("\n\t}\n");
        }

        sb.append("]");
        return sb.toString();
    }
}
