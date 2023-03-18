package com.example.parser.types.methods;

import com.example.parser.enums.AccessFlags;
import com.example.parser.interfaces.AttributeBase;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.MethodBase;
import com.example.parser.types.attributes.Attributes;
import com.example.parser.types.attributes.criticalAttributes.CodeAttribute;
import com.example.parser.types.attributes.optionalAttributes.RuntimeInvisibleAnnotations;
import com.example.parser.types.attributes.util.types.annotations.Annotation;

import java.util.List;

public class MethodInfo extends MethodBase implements ClassFileElement {

    public MethodInfo(AccessFlags accessFlags, Integer nameIndex, Integer descriptorIndex, Integer attributesCount, Attributes attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
    }

    public Annotation getJasmAnnotation() {
        for (AttributeBase attribute : this.attributes.getAttributes()) {
            if (attribute.getAttributeName() != null && attribute.getAttributeName().getValue().equals("RuntimeInvisibleAnnotations")) {
                RuntimeInvisibleAnnotations runtimeInvisibleAnnotations = (RuntimeInvisibleAnnotations) attribute;
                if (runtimeInvisibleAnnotations.getAnnotations().getAnnotations().stream().anyMatch(el -> el.getType().getValue().endsWith("Jasm;")))
                    return runtimeInvisibleAnnotations.getAnnotations().getAnnotations().stream().filter(el -> el.getType().getValue().endsWith("Jasm;")).findFirst().get();
            }
        }

        return null;
    }

    public CodeAttribute getCodeAttribute() {
        return (CodeAttribute) this.getAttributes().getAttributes().stream()
                .filter(el -> el.getAttributeName().getValue().equals("Code"))
                .findFirst()
                .get();
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "accessFlags=" + accessFlags +
                ", nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex +
                ", attributesCount=" + attributesCount +
                ", attributes=" + attributes +
                '}';
    }
}
