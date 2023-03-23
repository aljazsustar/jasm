package com.example.parser.types.methods;

import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.types.attributes.util.types.annotations.Annotation;
import com.example.parser.util.types.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Methods implements ClassFileElement {

    private List<MethodInfo> methods;

    public Methods(Integer methodsCount) {
        this.methods = new ArrayList<>(methodsCount);
    }

    public Methods() {
        this.methods = new ArrayList<>();
    }

    public List<Annotation> getJasmAnnotations() {
        return methods.stream().map(MethodInfo::getJasmAnnotation).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<Pair<MethodInfo, Annotation>> getJasmAnnotationsPerMethod() {
        return methods.stream().map(el -> new Pair<>(el, el.getJasmAnnotation())).filter(el -> el.getSecond() != null).collect(Collectors.toList());
    }

    public void addMethod(MethodInfo methodInfo) {
        this.methods.add(methodInfo);
    }

    public void addMethod(Integer index, MethodInfo methodInfo) {
        this.methods.add(index, methodInfo);
    }

    @Override
    public List<Byte> toHex() {
        return this.methods
                .stream()
                .map(ClassFileElement::toHex)
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodInfo> methods) {
        this.methods = methods;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("methods = [\n");

        for (MethodInfo method : this.methods) {
            sb.append("\t{\n\t\t").append(method.toString()).append("\n\t}\n");
        }

        sb.append("]");
        return sb.toString();

    }
}
