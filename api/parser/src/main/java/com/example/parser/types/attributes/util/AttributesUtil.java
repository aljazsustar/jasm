package com.example.parser.types.attributes.util;

import com.example.parser.enums.Mnemonics;
import com.example.parser.enums.VerificationTypeTags;
import com.example.parser.interfaces.ConstantValue;
import com.example.parser.interfaces.StackMapFrameBase;
import com.example.parser.interfaces.VerificationTypeInfoBase;
import com.example.parser.types.attributes.util.types.annotations.*;
import com.example.parser.types.attributes.util.types.annotations.elementValue.*;
import com.example.parser.types.attributes.util.types.code.Exception;
import com.example.parser.types.attributes.util.types.code.*;
import com.example.parser.types.attributes.util.types.lineNumberTable.LineNumberTable;
import com.example.parser.types.attributes.util.types.lineNumberTable.LineNumberTableElement;
import com.example.parser.types.attributes.util.types.stackMapTable.StackMapFrame;
import com.example.parser.types.attributes.util.types.stackMapTable.VerificationTypeInfo;
import com.example.parser.types.attributes.util.types.stackMapTable.stackMapFrameTypes.*;
import com.example.parser.types.attributes.util.types.stackMapTable.verificationTypes.*;
import com.example.parser.types.constantPool.ConstantPool;
import com.example.parser.types.constantPool.constants.ClassConstant;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;
import com.example.parser.util.ParsingUtil;
import com.example.parser.util.types.Pair;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

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
            if (command.getSecond() != 0)
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
                return new ElementValue<>(tag, constValue);
        }

    }

    public static List<StackMapFrame<? extends StackMapFrameBase>> parseStackMapFrames(BufferedInputStream inputStream) {
        Integer numOfEntries = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
        List<StackMapFrame<? extends StackMapFrameBase>> res = new ArrayList<>(numOfEntries);

        for (Integer i = 0; i < numOfEntries; i++) {
            res.add(parseStackMapFrame(inputStream));
        }

        return res;
    }

    private static StackMapFrame<? extends StackMapFrameBase> parseStackMapFrame(BufferedInputStream inputStream) {
        Integer tag = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 1));

        if (tag >= 0 && tag <= 63) {
            return new StackMapFrame<>(new SameFrame(tag));
        } else if (tag >= 64 && tag <= 127) {
            VerificationTypeInfo<? extends VerificationTypeInfoBase> verificationTypeInfo = parseVerificationTypeInfo(inputStream);
            return new StackMapFrame<>(new SameLocals1StackItemFrame(verificationTypeInfo, tag));
        } else if (tag == 247) {
            Integer offsetDelta = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            VerificationTypeInfo<? extends VerificationTypeInfoBase> verificationTypeInfo = parseVerificationTypeInfo(inputStream);
            return new StackMapFrame<>(new SameLocals1StackItemFrameExtended(tag, verificationTypeInfo, offsetDelta));
        } else if (tag >= 247 && tag <= 250) {
            Integer offsetDelta = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            return new StackMapFrame<>(new ChopFrame(tag, offsetDelta));
        } else if (tag == 251) {
            Integer offsetDelta = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            return new StackMapFrame<>(new SameFrameExtended(tag, offsetDelta));
        } else if (tag >= 252 && tag <= 254) {
            Integer offsetDelta = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> locals = parseVerificationTypes(inputStream, tag - 251);
            return new StackMapFrame<>(new AppendFrame(tag, offsetDelta, locals));
        } else if (tag == 255) {
            Integer offsetDelta = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            Integer numberOfLocals = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> locals = parseVerificationTypes(inputStream, numberOfLocals);
            Integer stackItems = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> stack = parseVerificationTypes(inputStream, stackItems);
            return new StackMapFrame<>(new FullFrame(tag, offsetDelta, numberOfLocals, locals, stackItems, stack));
        } else throw new RuntimeException(String.format("StackMapFrame type with tag %d does not exist.", tag));
    }

    private static List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> parseVerificationTypes(BufferedInputStream inputStream, Integer length) {
        List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> res = new ArrayList<>();

        for (Integer i = 0; i < length; i++) {
            res.add(parseVerificationTypeInfo(inputStream));
        }

        return res;
    }

    private static VerificationTypeInfo<? extends VerificationTypeInfoBase> parseVerificationTypeInfo(BufferedInputStream inputStream) {
        Integer tag = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 1));

        switch (tag) {
            case VerificationTypeTags.ITEM_Top:
                return new VerificationTypeInfo<>(new TopVariableInfo(tag));
            case VerificationTypeTags.ITEM_Integer:
                return new VerificationTypeInfo<>(new IntegerVariableInfo(tag));
            case VerificationTypeTags.ITEM_Float:
                return new VerificationTypeInfo<>(new FloatVariableInfo(tag));
            case VerificationTypeTags.ITEM_Double:
                return new VerificationTypeInfo<>(new DoubleVariableInfo(tag));
            case VerificationTypeTags.ITEM_Long:
                return new VerificationTypeInfo<>(new LongVariableInfo(tag));
            case VerificationTypeTags.ITEM_Null:
                return new VerificationTypeInfo<>(new NullVariableInfo(tag));
            case VerificationTypeTags.ITEM_UninitializedThis:
                return new VerificationTypeInfo<>(new UninitializedThisVariableInfo(tag));
            case VerificationTypeTags.ITEM_Object:
                Integer constantPoolIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
                return new VerificationTypeInfo<>(new ObjectVariableInfo(tag, constantPoolIndex));
            case VerificationTypeTags.ITEM_Uninitialized:
                Integer offset = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
                return new VerificationTypeInfo<>(new UninitializedVariableInfo(tag, offset));
        }

        throw new RuntimeException(String.format("VerificationTypeInfo with tag %d does not exist", tag));
    }
}
