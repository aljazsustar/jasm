package com.example.parser.types.attributes.util.types.code;

import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.util.types.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Code implements ClassFileElement {

    private final List<Pair<Mnemonic, Arguments>> code;

    public Code() {
        this.code = new LinkedList<>();
    }

    public List<Pair<Mnemonic, Arguments>> getCode() {
        return code;
    }

    public void addCommand(Pair<Mnemonic, Arguments> command) {
        this.code.add(command);
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> bytes = new ArrayList<>();
        for (Pair<Mnemonic, Arguments> code : this.code) {
            bytes.addAll(code.getFirst().toHex());
            bytes.addAll(code.getSecond().toHex());
        }
        return bytes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (Pair<Mnemonic, Arguments> command : this.code) {
            sb.append(" ").append(command).append(",\n");
        }

        sb.append("]");
        return sb.toString();
    }
}
