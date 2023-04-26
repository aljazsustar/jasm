package com.jasm.insert.types;

import com.jasm.parser.enums.Mnemonics;
import com.jasm.parser.types.attributes.util.types.code.Arguments;
import com.jasm.parser.types.attributes.util.types.code.Mnemonic;
import com.jasm.parser.types.methods.MethodInfo;
import com.jasm.parser.util.types.Pair;

import java.util.List;

public class JasmBlock {
    private Integer jasmBlockStartLine;
    private Integer jasmBlockEndLine;
    private MethodInfo method;
    private List<String> methodStrings;
    private List<Pair<Mnemonic, Arguments>> byteCode;

    public JasmBlock(Integer jasmBlockStartLine, Integer jasmBlockEndLine, MethodInfo method, List<String> methodStrings, List<Pair<Mnemonic, Arguments>> byteCode) {
        this.jasmBlockStartLine = jasmBlockStartLine;
        this.jasmBlockEndLine = jasmBlockEndLine;
        this.method = method;
        this.methodStrings = methodStrings;
        this.byteCode = byteCode;
    }

    public List<String> getMethodStrings() {
        return methodStrings;
    }

    public void setMethodStrings(List<String> methodStrings) {
        this.methodStrings = methodStrings;
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

    public MethodInfo getMethod() {
        return method;
    }

    public void setMethod(MethodInfo method) {
        this.method = method;
    }

    public List<Pair<Mnemonic, Arguments>> getByteCode() {
        return byteCode;
    }

    public Integer getByteCodeSize() {
        Mnemonics m = new Mnemonics();
        return this.byteCode.stream()
                .map(el -> 1 + m.getMnemonicByOpcode(el.getFirst().getOpcode()).getSecond())
                .reduce(0, Integer::sum);
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