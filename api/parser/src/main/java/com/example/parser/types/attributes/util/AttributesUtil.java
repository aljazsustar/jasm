package com.example.parser.types.attributes.util;

import com.example.parser.enums.Mnemonics;
import com.example.parser.interfaces.ConstantValue;
import com.example.parser.types.attributes.util.types.annotations.*;
import com.example.parser.types.attributes.util.types.annotations.elementValue.*;
import com.example.parser.types.attributes.util.types.code.Exception;
import com.example.parser.types.attributes.util.types.code.*;
import com.example.parser.types.attributes.util.types.lineNumberTable.LineNumberTable;
import com.example.parser.types.attributes.util.types.lineNumberTable.LineNumberTableElement;
import com.example.parser.types.constantPool.ConstantPool;
import com.example.parser.types.constantPool.constants.ClassConstant;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;
import com.example.parser.util.ParsingUtil;
import com.example.parser.util.types.Pair;

import java.io.BufferedInputStream;

public class AttributesUtil {
    public static Code parseCode(BufferedInputStream inputStream, Long codeLength) {
        Code code = new Code();
        Mnemonics mnemonics = new Mnemonics();
        for (int i = 0; i < codeLength; i++) {
            // TODO: handle commands with variable number args (wide, tableswitch, lookupswitch)
            Integer opcode = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 1));
            Pair<String, Integer> command = mnemonics.getMnemonicByOpcode(opcode);
            Mnemonic mnemonic = new Mnemonic(mnemonics.getOpcodeByMnemonic(command.getFirst()).getFirst(), command.getFirst());
            Arguments commandArgs = new Arguments();
            // TODO: this does not handle multiple arguments
            commandArgs.addArgument(ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, command.getSecond())));
            i += command.getSecond();
            code.addCommand(new Pair<>(mnemonic, commandArgs));
        }

        return code;
    }

    public static Exceptions parseExceptions(BufferedInputStream inputStream, ConstantPool constantPool, Integer exceptionTableLength) {
        Exceptions exceptions = new Exceptions(exceptionTableLength);

        for (Integer i = 0; i < exceptionTableLength; i++) {
            Integer startPc = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            Integer endPc = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            Integer handlerPc = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            Integer catchTypeIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            ClassConstant catchType = (ClassConstant) constantPool.getConstantPoolElement(catchTypeIndex - 1);
            Exception exception = new Exception(startPc, endPc, handlerPc, catchTypeIndex, catchType);
            exceptions.addException(exception);
        }

        return exceptions;
    }

    public static LineNumberTable parseLineNumberTable(BufferedInputStream inputStream, Integer lineNumberTableLength) {

        LineNumberTable lineNumberTable = new LineNumberTable(lineNumberTableLength);

        for (Integer i = 0; i < lineNumberTableLength; i++) {
            Integer startPc = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            Integer lineNumber = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            LineNumberTableElement element = new LineNumberTableElement(startPc, lineNumber);
            lineNumberTable.addLineNumberTableElement(element);
        }

        return lineNumberTable;
    }

    public static Annotations parseAnnotations(BufferedInputStream inputStream, ConstantPool constantPool, Integer annotationsLength) {

        Annotations annotations = new Annotations(annotationsLength);

        for (int i = 0; i < annotationsLength; i++) {
            annotations.addAnnotation(parseAnnotation(inputStream, constantPool));
        }
        return annotations;
    }

    private static Annotation parseAnnotation(BufferedInputStream inputStream, ConstantPool constantPool) {
        Integer typeIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
        Utf8Constant type = (Utf8Constant) constantPool.getConstantPoolElement(typeIndex - 1);
        Integer numElementValuePairs = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
        ElementValuePairs elementValuePairs = parseElementValuePairs(inputStream, constantPool, numElementValuePairs);
        return new Annotation(typeIndex, type, numElementValuePairs, elementValuePairs);
    }

    private static ElementValuePairs parseElementValuePairs(BufferedInputStream inputStream, ConstantPool constantPool, Integer elementValuePairsLength) {

        ElementValuePairs elementValuePairs = new ElementValuePairs(elementValuePairsLength);

        for (int i = 0; i < elementValuePairsLength; i++) {
            Integer elementNameIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            Utf8Constant elementName = (Utf8Constant) constantPool.getConstantPoolElement(elementNameIndex - 1);
            ElementValue<? extends ElementValueType> elementValue = parseElementValue(inputStream, constantPool);
            ElementValuePair<? extends ElementValueType> elementValuePair = new ElementValuePair<>(elementNameIndex, elementName, elementValue);
            elementValuePairs.addElementValuePair(elementValuePair);
        }

        return elementValuePairs;
    }

    private static ElementValue<? extends ElementValueType> parseElementValue(BufferedInputStream inputStream, ConstantPool constantPool) {

        Integer ch = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 1));
        char tag = String.valueOf(Character.toChars(ch)).charAt(0);
        switch (tag) {
            case 'e':
                Integer typeNameIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
                Integer constNameIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
                Utf8Constant typeName = (Utf8Constant) constantPool.getConstantPoolElement(typeNameIndex - 1);
                Utf8Constant constName = (Utf8Constant) constantPool.getConstantPoolElement(constNameIndex - 1);
                EnumConstValue enumConstValue = new EnumConstValue(typeNameIndex, constNameIndex, typeName, constName);
                return new ElementValue<>(tag, enumConstValue);
            case 'c':
                Integer classInfoIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
                ClassConstant classConstant = (ClassConstant) constantPool.getConstantPoolElement(classInfoIndex - 1);
                ClassInfo classInfo = new ClassInfo(classInfoIndex, classConstant);
                return new ElementValue<>(tag, classInfo);
            case '@':
                Annotation annotation = parseAnnotation(inputStream, constantPool);
                AnnotationValue annotationValue = new AnnotationValue(annotation);
                return new ElementValue<>(tag, annotationValue);
            case '[':
                Integer numValues = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
                ArrayValue<?> arrayValue = new ArrayValue<>(numValues);
                for (int i = 0; i < numValues; i++) {
                    arrayValue.addElementValue(parseElementValue(inputStream, constantPool));
                }
                return new ElementValue<>(tag, arrayValue);
            default:
                Integer constValueIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
                ConstantValue constantValue = (ConstantValue) constantPool.getConstantPoolElement(constValueIndex - 1);
                ConstValue constValue = new ConstValue(constValueIndex, constantValue);
                return new ElementValue<ConstValue>(tag, constValue);
        }

    }
}
