package com.example.insert;

import com.example.insert.types.JasmBlock;
import com.example.parser.enums.Mnemonics;
import com.example.parser.types.attributes.util.types.annotations.Annotation;
import com.example.parser.types.attributes.util.types.annotations.ElementValue;
import com.example.parser.types.attributes.util.types.annotations.ElementValuePair;
import com.example.parser.types.attributes.util.types.annotations.elementValue.AnnotationValue;
import com.example.parser.types.attributes.util.types.annotations.elementValue.ArrayValue;
import com.example.parser.types.attributes.util.types.annotations.elementValue.ConstValue;
import com.example.parser.types.attributes.util.types.code.Arguments;
import com.example.parser.types.attributes.util.types.code.Mnemonic;
import com.example.parser.types.methods.MethodInfo;
import com.example.parser.util.types.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JasmBlocksParser {

    public static List<JasmBlock> extractJasmBlocks(String source, List<Pair<MethodInfo, Annotation>> jasmBlocks) {
        String[] sourceLines = source.split("\n");
        List<Pair<MethodInfo, Pair<Integer, Integer>>> jasmStartEnd = getJasmStartEndLines(jasmBlocks);
        List<JasmBlock> res = new ArrayList<>(jasmBlocks.size());

        for (Pair<MethodInfo, Pair<Integer, Integer>> pair : jasmStartEnd) {
            String[] rawBytecode = Arrays.copyOfRange(sourceLines, pair.getSecond().getFirst() - 1, pair.getSecond().getSecond());
            res.add(extractJasmBlock(rawBytecode, pair.getSecond().getFirst(), pair.getSecond().getSecond(), pair.getFirst()));
        }
        return res;
    }

    private static JasmBlock extractJasmBlock(String[] jasmSource, Integer jasmStart, Integer jasmEnd, MethodInfo method) {
        Mnemonics mnemonics = new Mnemonics();
        List<Pair<Mnemonic, Arguments>> code = new ArrayList<>();

        for (String s : jasmSource) {
            String[] split = s.strip().split(" ");
            Mnemonic mnemonic = new Mnemonic(mnemonics.getOpcodeByMnemonic(split[0]).getFirst(), split[0]);
            Arguments arguments = new Arguments();

            for (int i = 1; i < split.length; i++) {
                arguments.addArgument(Integer.parseInt(split[i].strip()));
            }

            Pair<Mnemonic, Arguments> command = new Pair<>(mnemonic, arguments);
            code.add(command);

        }

        return new JasmBlock(jasmStart, jasmEnd, method, code);
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
