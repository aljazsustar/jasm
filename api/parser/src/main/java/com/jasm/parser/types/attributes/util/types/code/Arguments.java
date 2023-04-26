package com.jasm.parser.types.attributes.util.types.code;

import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class Arguments implements ClassFileElement {

    private final List<Integer> arguments;

    public Arguments() {
        this.arguments = new ArrayList<>();
    }

    public void addArgument(Integer argument) {
        this.arguments.add(argument);
    }

    public List<Integer> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Integer argument : this.arguments) {
            sb.append(" ").append(argument).append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public List<Byte> toHex() {
        return this.arguments
                .stream()
                .map(el -> WritingUtil.writeBytes(el, 2))
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }

    public List<Byte> toHex(Integer argSize) {
        return this.arguments
                .stream()
                .map(el -> WritingUtil.writeBytes(el, argSize))
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }
}
