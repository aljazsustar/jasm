package com.example.insert;

import com.example.insert.types.JasmBlock;
import com.example.parser.types.ClassFile;
import com.example.parser.types.attributes.criticalAttributes.CodeAttribute;
import com.example.parser.types.attributes.usefulAttributes.LineNumberTableAttribute;
import com.example.parser.types.attributes.util.types.lineNumberTable.LineNumberTableElement;

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

        for (LineNumberTableElement lineNumberTableElement : lineNumberTableAttribute.getLineNumberTable().getLineNumberTable()) {
            if (lineNumberTableElement.getLineNumber().intValue() == jasmBlock.getJasmBlockStartLine()) {
                return lineNumberTableElement.getStartPc() + 1;
            }
            if (lineNumberTableElement.getLineNumber() > jasmBlock.getJasmBlockEndLine()) {
                return lineNumberTableElement.getStartPc();
            }
        }
        return -1;
    }
}
