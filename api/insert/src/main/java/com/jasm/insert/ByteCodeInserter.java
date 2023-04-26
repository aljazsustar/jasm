package com.jasm.insert;

import com.jasm.insert.types.JasmBlock;
import com.jasm.parser.types.ClassFile;
import com.jasm.parser.types.attributes.criticalAttributes.CodeAttribute;
import com.jasm.parser.types.attributes.usefulAttributes.LineNumberTableAttribute;
import com.jasm.parser.types.attributes.util.types.code.Arguments;
import com.jasm.parser.types.attributes.util.types.code.Mnemonic;
import com.jasm.parser.types.attributes.util.types.lineNumberTable.LineNumberTableElement;
import com.jasm.parser.util.types.Pair;

import java.util.List;

public class ByteCodeInserter {

    public static void insertBytecode(List<JasmBlock> jasmBlocks, ClassFile classFile) {
        for (JasmBlock jasmBlock : jasmBlocks) {
            jasmBlock.getMethod().getCodeAttribute().getCode().getCode().addAll(getInsertionIndex(jasmBlock), jasmBlock.getByteCode());
            boolean deleted = deleteIf2Returns(jasmBlock);
            Long currentCodeLength = jasmBlock.getMethod().getCodeAttribute().getCodeLength();
            Long currentAttributeLength = jasmBlock.getMethod().getCodeAttribute().getAttributeLength();
            Integer currentMaxStack = jasmBlock.getMethod().getCodeAttribute().getMaxStack();
            Integer currentMaxLocals = jasmBlock.getMethod().getCodeAttribute().getMaxLocals();

            if (deleted) {
                jasmBlock.getMethod().getCodeAttribute().setCodeLength(currentCodeLength + jasmBlock.getByteCodeSize() - 2);
                jasmBlock.getMethod().getCodeAttribute().setMaxStack(currentMaxStack + getMaxStackSizeDiff(jasmBlock) - 1);
                jasmBlock.getMethod().getCodeAttribute().setMaxLocals(currentMaxLocals + getLocalsDiff(jasmBlock));
                jasmBlock.getMethod().getCodeAttribute().setAttributeLength(currentAttributeLength + jasmBlock.getByteCodeSize() - 2);
            } else {
                jasmBlock.getMethod().getCodeAttribute().setCodeLength(currentCodeLength + jasmBlock.getByteCodeSize());
                jasmBlock.getMethod().getCodeAttribute().setMaxStack(currentMaxStack + getMaxStackSizeDiff(jasmBlock));
                jasmBlock.getMethod().getCodeAttribute().setMaxLocals(currentMaxLocals + getLocalsDiff(jasmBlock));
                jasmBlock.getMethod().getCodeAttribute().setAttributeLength(currentAttributeLength + jasmBlock.getByteCodeSize());
            }
        }
    }

    private static boolean deleteIf2Returns(JasmBlock jasmBlock) {
        List<Pair<Mnemonic, Arguments>> code = jasmBlock.getMethod().getCodeAttribute().getCode().getCode();
        Pair<Mnemonic, Arguments> last = code.get(code.size() - 1);
        Pair<Mnemonic, Arguments> secondToLast = code.get(code.size() - 2);

        if (last.getFirst().getMnemonic().contains("return") && secondToLast.getFirst().getMnemonic().contains("iconst")) {
            code.remove(last);
            code.remove(secondToLast);
            return true;
        }

        return false;
    }

    private static Integer getInsertionIndex(JasmBlock jasmBlock) {
        CodeAttribute codeAttribute = (CodeAttribute) jasmBlock.getMethod().getAttributes().getAttributes()
                .stream()
                .filter(el -> el.getAttributeName().getValue().equals("Code"))
                .findFirst()
                .get();

        LineNumberTableAttribute lineNumberTableAttribute = (LineNumberTableAttribute) codeAttribute.getAttributes().getAttributes()
                .stream()
                .filter(el -> el.getAttributeName().getValue().equals("LineNumberTable"))
                .findFirst()
                .get();

        // first we find the pcStart in the linNumberTable attribute
        Integer lineNumberTableIndex = 0;
        for (LineNumberTableElement lineNumberTableElement : lineNumberTableAttribute.getLineNumberTable().getLineNumberTable()) {
            if (lineNumberTableElement.getLineNumber() > jasmBlock.getJasmBlockEndLine()) {
                lineNumberTableIndex = lineNumberTableElement.getStartPc();
                break;
            }
        }

        // because PC also includes sizes of arguments, the mapping between codeAttribute and lineNumberTableAttribute
        // isn't exactly 1:1. We get the index of the insertion by counting the counter (number of bytes encountered (mnemonic + args))
        // by the 1 + size of args, while simultaneously incrementing codeTableIndex, which the actual index at which we
        // insert the code into the Code attribute
        Integer codeTableIndex = 0;
        Integer counter = 0;

        for (Pair<Mnemonic, Arguments> command : codeAttribute.getCode().getCode()) {

            if (counter.equals(lineNumberTableIndex)) {
                return codeTableIndex;
            }
            counter += 1 + command.getSecond().getArguments().size();
            codeTableIndex++;
        }
        return -1;
    }

    private static Integer getMaxStackSizeDiff(JasmBlock jasmBlock) {
        Integer diff = 0;
        for (Pair<Mnemonic, Arguments> command : jasmBlock.getByteCode()) {
            if (command.getFirst().getMnemonic().contains("load")
                    || command.getFirst().getMnemonic().contains("push")
                    || command.getFirst().getMnemonic().contains("const"))
                ++diff;
        }

        return diff;
    }

    private static Integer getLocalsDiff(JasmBlock jasmBlock) {
        Integer diff = 0;
        for (Pair<Mnemonic, Arguments> command : jasmBlock.getByteCode()) {
            if (command.getFirst().getMnemonic().contains("store")) ++diff;
        }

        return diff;
    }
}
