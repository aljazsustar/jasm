package com.example.insert.types;

import com.example.parser.types.attributes.util.types.code.Arguments;
import com.example.parser.types.attributes.util.types.code.Mnemonic;
import com.example.parser.util.types.Pair;

import java.util.List;

public class JasmBlock {
    private Integer jasmBlockStartLine;
    private Integer jasmBlockEndLine;
    private List<Pair<Mnemonic, Arguments>> byteCode;

    public JasmBlock(Integer jasmBlockStartLine, Integer jasmBlockEndLine, List<Pair<Mnemonic, Arguments>> byteCode) {
        this.jasmBlockStartLine = jasmBlockStartLine;
        this.jasmBlockEndLine = jasmBlockEndLine;
        this.byteCode = byteCode;
    }


    public Integer getJasmBlockStartLine() {
        return jasmBlockStartLine;
    }

    public void setJasmBlockStartLine(Integer jasmBlockStartLine) {
        this.jasmBlockStartLine = jasmBlockStartLine;
    }

    public Integer getJasmBlockEndLine() {
        return jasmBlockEndLine;
    }

    public void setJasmBlockEndLine(Integer jasmBlockEndLine) {
        this.jasmBlockEndLine = jasmBlockEndLine;
    }

    public List<Pair<Mnemonic, Arguments>> getByteCode() {
        return byteCode;
    }

    public void setByteCode(List<Pair<Mnemonic, Arguments>> byteCode) {
        this.byteCode = byteCode;
    }

    private String byteCodeToString() {
        StringBuilder sb = new StringBuilder();

        for (Pair<Mnemonic, Arguments> mnemonicArgumentsPair : this.byteCode) {
            sb.append(mnemonicArgumentsPair.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "JasmBlock{" +
                "jasmBlockStartLine=" + jasmBlockStartLine +
                ", jasmBlockEndLine=" + jasmBlockEndLine +
                ", byteCode=\n" + byteCodeToString() +
                '}';
    }
}