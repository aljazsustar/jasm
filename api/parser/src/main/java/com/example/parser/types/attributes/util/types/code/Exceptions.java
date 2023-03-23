package com.example.parser.types.attributes.util.types.code;

import com.example.parser.interfaces.ClassFileElement;

import java.util.ArrayList;
import java.util.List;

public class Exceptions implements ClassFileElement {

    private final List<Exception> exceptions;

    public Exceptions() {
        this.exceptions = new ArrayList<>();
    }

    public Exceptions(Integer exceptionTableLength) {
        this.exceptions = new ArrayList<>(exceptionTableLength);
    }


    public void addException(Exception exception) {
        this.exceptions.add(exception);
    }

    @Override
    public List<Byte> toHex() {
        return this.exceptions
                .stream()
                .map(ClassFileElement::toHex)
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Exception exception : this.exceptions) {
            sb.append(" ").append(exception.toString()).append(", ");
        }

        sb.append("]");
        return sb.toString();
    }
}
