package com.jasm.parser.types.interfaces;

import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.types.constantPool.constants.ClassConstant;

import java.util.ArrayList;
import java.util.List;

public class Interfaces implements ClassFileElement {

    List<ClassConstant> interfaces;

    public Interfaces(Integer interfacesCount) {
        this.interfaces = new ArrayList<>(interfacesCount);
    }

    public void addToInterfaces(ClassConstant element) {
        this.interfaces.add(element);
    }

    public ClassConstant getInterface(Integer interfacesIndex) {
        return this.interfaces.get(interfacesIndex);
    }

    @Override
    public List<Byte> toHex() {
        return this.interfaces
                .stream()
                .map(ClassConstant::toHex)
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Interfaces = ");

        for (ClassConstant classConstant : this.interfaces) {
            sb.append("{\n");
            sb.append(classConstant.toString());
            sb.append("}\n");
        }
        return sb.toString();
    }
}
