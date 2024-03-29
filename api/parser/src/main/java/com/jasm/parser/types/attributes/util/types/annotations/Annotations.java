package com.jasm.parser.types.attributes.util.types.annotations;

import com.jasm.parser.interfaces.ClassFileElement;

import java.util.ArrayList;
import java.util.List;

public class Annotations implements ClassFileElement {

    private List<Annotation> annotations;

    public Annotations() {
        this.annotations = new ArrayList<>();
    }

    public Annotations(Integer numAnnotations) {
        this.annotations = new ArrayList<>(numAnnotations);
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void addAnnotation(Annotation annotation) {
        this.annotations.add(annotation);
    }

    @Override
    public List<Byte> toHex() {
        return this.annotations
                .stream()
                .map(ClassFileElement::toHex)
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }
}
