package com.example.insert;

import com.example.insert.types.JasmBlock;
import com.example.parser.types.ClassFile;
import com.example.parser.types.attributes.criticalAttributes.CodeAttribute;
import com.example.parser.types.attributes.usefulAttributes.LineNumberTableAttribute;
import com.example.parser.types.attributes.util.types.code.Arguments;
import com.example.parser.types.attributes.util.types.code.Mnemonic;
import com.example.parser.types.attributes.util.types.lineNumberTable.LineNumberTableElement;
import com.example.parser.util.types.Pair;

import java.util.List;

public class ByteCodeInserter {

    public static void insertBytecode(List<JasmBlock> jasmBlocks, ClassFile classFile) {
        for (JasmBlock jasmBlock : jasmBlocks) {
            Long currentCodeLength = jasmBlock.getMethod().getCodeAttribute().getCodeLength();
            jasmBlock.getMethod().getCodeAttribute().setCodeLength(currentCodeLength + jasmBlock.getByteCode().size());
            jasmBlock.getMethod().getCodeAttribute().getCode().getCode().addAll(getInsertionIndex(jasmBlock), jasmBlock.getByteCode());
            System.out.println(jasmBlock.getMethod().getCodeAttribute());
        }
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
}
