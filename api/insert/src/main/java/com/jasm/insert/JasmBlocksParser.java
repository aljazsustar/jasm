package com.jasm.insert;

import com.jasm.insert.types.JasmBlock;
import com.jasm.parser.enums.Mnemonics;
import com.jasm.parser.exceptions.InvalidMnenonicException;
import com.jasm.parser.types.attributes.util.types.annotations.Annotation;
import com.jasm.parser.types.attributes.util.types.annotations.ElementValue;
import com.jasm.parser.types.attributes.util.types.annotations.ElementValuePair;
import com.jasm.parser.types.attributes.util.types.annotations.elementValue.AnnotationValue;
import com.jasm.parser.types.attributes.util.types.annotations.elementValue.ArrayValue;
import com.jasm.parser.types.attributes.util.types.annotations.elementValue.ConstValue;
import com.jasm.parser.types.attributes.util.types.code.Arguments;
import com.jasm.parser.types.attributes.util.types.code.Mnemonic;
import com.jasm.parser.types.constantPool.ConstantPool;
import com.jasm.parser.types.methods.MethodInfo;
import com.jasm.parser.util.types.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JasmBlocksParser {

    public static List<JasmBlock> extractJasmBlocks(String source, List<Pair<MethodInfo, Annotation>> jasmBlocks, ConstantPool constantPool) throws InvalidMnenonicException {
        String[] sourceLines = source.split("\n");
        List<Pair<MethodInfo, Pair<Integer, Integer>>> jasmStartEnd = getJasmStartEndLines(jasmBlocks);
        List<JasmBlock> res = new ArrayList<>(jasmBlocks.size());

        for (Pair<MethodInfo, Pair<Integer, Integer>> pair : jasmStartEnd) {
            String[] rawBytecode = Arrays.copyOfRange(sourceLines, pair.getSecond().getFirst() - 1, pair.getSecond().getSecond());
            res.add(extractJasmBlock(rawBytecode, pair.getSecond().getFirst(), pair.getSecond().getSecond(), pair.getFirst(), constantPool));
        }
        return res;
    }

    private static JasmBlock extractJasmBlock(String[] jasmSource, Integer jasmStart, Integer jasmEnd, MethodInfo method, ConstantPool constantPool) throws InvalidMnenonicException {
        Mnemonics mnemonics = new Mnemonics();
        List<Pair<Mnemonic, Arguments>> code = new ArrayList<>();
        List<String> methodStrings = new ArrayList<>();

        for (String s : jasmSource) {
            String[] split = s.strip().replaceAll(" +", " ").split(" ");
            Pair<Integer, Integer> opcodeAndArgs = mnemonics.getOpcodeByMnemonic(split[0]);

            if (opcodeAndArgs == null) throw new InvalidMnenonicException(String.format("Mnemonic %s ni del javanske zložne kode", split[0]));

            Mnemonic mnemonic = new Mnemonic(opcodeAndArgs.getFirst(), split[0]);
            Arguments arguments = new Arguments();

            for (int i = 1; i < split.length; i++) {

                if (split[i].strip().startsWith("#")) {
                    split[i] = split[i].substring(1);

                    if (split[i].split("\\.").length == 2) {
                        String[] nameAndType = split[i].split("\\.");
                        Integer cpIndex = constantPool.getMethodByFullyQualifiedName(nameAndType[0], nameAndType[1]).getConstantPoolIndex();
                        arguments.addArgument(cpIndex + 1);
                    } else {
                        Integer cpIndex = constantPool.getMethodByMethodName(split[i]).getConstantPoolIndex();
                        arguments.addArgument(cpIndex + 1);
                    }
                    continue;
                }

                arguments.addArgument(Integer.parseInt(split[i].strip()));
            }

            Pair<Mnemonic, Arguments> command = new Pair<>(mnemonic, arguments);
            code.add(command);

        }

        return new JasmBlock(jasmStart, jasmEnd, method, methodStrings, code);
    }

    private static List<Pair<MethodInfo, Pair<Integer, Integer>>> getJasmStartEndLines(List<Pair<MethodInfo, Annotation>> jasmBlocks) {
        List<Pair<MethodInfo, Pair<Integer, Integer>>> res = new ArrayList<>();

        for (Pair<MethodInfo, Annotation> annotation : jasmBlocks) {
            for (ElementValuePair<ArrayValue<AnnotationValue>> jasmElementValuePair : annotation.getSecond().getElementValuePairs().getElementValuePairs()) {
                for (ElementValue<?> blockElementValuePair : jasmElementValuePair.getElementValue().getValue().getValues()) {
                    AnnotationValue blockAnnotation = (AnnotationValue) blockElementValuePair.getValue();
                    ElementValue<ConstValue> startElement = blockAnnotation.getAnnotationValue().getElementValuePairs().getElementValuePairs().get(0).getElementValue();
                    ElementValue<ConstValue> endElement = blockAnnotation.getAnnotationValue().getElementValuePairs().getElementValuePairs().get(1).getElementValue();
                    Integer start = startElement.getValue().getConstValue().getValue();
                    Integer end = endElement.getValue().getConstValue().getValue();
                    res.add(new Pair<>(annotation.getFirst(), new Pair<>(start, end)));
                }
            }
        }
        return res;
    }
}
