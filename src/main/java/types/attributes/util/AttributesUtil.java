package types.attributes.util;

import enums.Mnemonics;
import types.attributes.util.types.Arguments;
import types.attributes.util.types.Code;
import types.attributes.util.types.Exceptions;
import types.attributes.util.types.Mnemonic;
import types.constantPool.ConstantPool;
import types.constantPool.constants.ClassConstant;
import util.ParsingUtil;
import util.types.Pair;

import java.io.BufferedInputStream;

public class AttributesUtil {
    public static Code parseCode(BufferedInputStream inputStream, Long codeLength) {
        Code code = new Code();
        Mnemonics mnemonics = new Mnemonics();
        System.out.println(codeLength);
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
        System.out.println(exceptionTableLength);

        for (Integer i = 0; i < exceptionTableLength; i++) {
            Integer startPc = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            Integer endPc = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            Integer handlerPc = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            Integer catchTypeIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(inputStream, 2));
            System.out.println(catchTypeIndex);
            ClassConstant catchType = (ClassConstant) constantPool.getConstantPoolElement(catchTypeIndex - 1);
            types.attributes.util.types.Exception exception = new types.attributes.util.types.Exception(startPc, endPc, handlerPc, catchTypeIndex, catchType);
            exceptions.addException(exception);
        }

        return exceptions;
    }
}
