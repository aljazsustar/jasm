package com.example.jasm_parser.types.attributes.util;

import com.example.jasm_parser.enums.Mnemonics;
import com.example.jasm_parser.types.attributes.util.types.code.Exception;
import com.example.jasm_parser.types.attributes.util.types.code.*;
import com.example.jasm_parser.types.attributes.util.types.lineNumberTable.LineNumberTable;
import com.example.jasm_parser.types.attributes.util.types.lineNumberTable.LineNumberTableElement;
import com.example.jasm_parser.types.constantPool.ConstantPool;
import com.example.jasm_parser.types.constantPool.constants.ClassConstant;
import com.example.jasm_parser.util.ParsingUtil;
import com.example.jasm_parser.util.types.Pair;

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
}
