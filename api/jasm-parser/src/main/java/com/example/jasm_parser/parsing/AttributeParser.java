package com.example.jasm_parser.parsing;

import com.example.jasm_parser.exceptions.AttributeDoesNotExistException;
import com.example.jasm_parser.interfaces.AttributeBase;
import com.example.jasm_parser.interfaces.ConstantValue;
import com.example.jasm_parser.types.attributes.Attributes;
import com.example.jasm_parser.types.attributes.criticalAttributes.CodeAttribute;
import com.example.jasm_parser.types.attributes.criticalAttributes.ConstantValueAttribute;
import com.example.jasm_parser.types.attributes.criticalAttributes.StackMapTableAttribute;
import com.example.jasm_parser.types.attributes.usefulAttributes.LineNumberTableAttribute;
import com.example.jasm_parser.types.attributes.usefulAttributes.SourceFileAttribute;
import com.example.jasm_parser.types.attributes.util.AttributesUtil;
import com.example.jasm_parser.types.attributes.util.types.code.Code;
import com.example.jasm_parser.types.attributes.util.types.code.Exceptions;
import com.example.jasm_parser.types.attributes.util.types.lineNumberTable.LineNumberTable;
import com.example.jasm_parser.types.constantPool.ConstantPool;
import com.example.jasm_parser.types.constantPool.constants.strings.Utf8Constant;
import com.example.jasm_parser.util.ParsingUtil;

import java.io.BufferedInputStream;

public class AttributeParser {

    private final Integer attributesCount;
    private final BufferedInputStream inputStream;
    private final ConstantPool constantPool;

    public AttributeParser(Integer attributesCount, BufferedInputStream inputStream, ConstantPool constantPool) {
        this.attributesCount = attributesCount;
        this.inputStream = inputStream;
        this.constantPool = constantPool;
    }

    public Attributes parse() throws AttributeDoesNotExistException {
        Attributes attributes = new Attributes(this.attributesCount);

        for (int i = 0; i < this.attributesCount; i++) {
            attributes.add(this.parseAttribute());
        }

        return attributes;
    }

    private AttributeBase parseAttribute() throws AttributeDoesNotExistException {
        Utf8Constant attributeName = (Utf8Constant) this.constantPool.getConstantPoolElement(ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2)) - 1);

        switch (attributeName.getValue()) {
            case "ConstantValue":
                return this.parseConstantValueAttribute(attributeName);
            case "Code":
                return this.parseCodeAttribute(attributeName);
            case "LineNumberTable":
                return this.parseLineNumberTableAttribute(attributeName);
            case "SourceFile":
                return this.parseSourceFileAttribute(attributeName);
            case "StackMapTable":
                return this.parseStackMapTableAttribute(attributeName);
            case "Exceptions":
                return this.parseExceptionsAttribute(attributeName);
            case "BootstrapMethods":
                return this.parseBootstrapMethods(attributeName);
            case "InnerClasses":
                return this.parseInnerClassesAttribute(attributeName);

        }

        throw new AttributeDoesNotExistException();
    }

    private ConstantValueAttribute parseConstantValueAttribute(Utf8Constant attributeName) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Integer constantValueIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        ConstantValueAttribute constantValueAttribute = new ConstantValueAttribute(attributeName, attributeLength, constantValueIndex);
        constantValueAttribute.setConstantValue((ConstantValue) this.constantPool.getConstantPoolElement(constantValueIndex - 1));
        return constantValueAttribute;
    }

    private CodeAttribute parseCodeAttribute(Utf8Constant attributeName) throws AttributeDoesNotExistException {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Integer maxStack = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer maxLocals = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Long codeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Code code = AttributesUtil.parseCode(this.inputStream, codeLength);
        Integer exceptionTableLength = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Exceptions exceptions = AttributesUtil.parseExceptions(this.inputStream, this.constantPool, exceptionTableLength);
        Integer attributesCount = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Attributes attributes = new AttributeParser(attributesCount, this.inputStream, this.constantPool).parse();
        return new CodeAttribute(attributeLength, maxStack, maxLocals, codeLength, code, exceptionTableLength,
                exceptions, attributesCount, attributes);
    }

    private LineNumberTableAttribute parseLineNumberTableAttribute(Utf8Constant attributeName) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Integer lineNumberTableLength = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        LineNumberTable lineNumberTable = AttributesUtil.parseLineNumberTable(this.inputStream, lineNumberTableLength);
        return new LineNumberTableAttribute(attributeName, attributeLength, lineNumberTableLength, lineNumberTable);
    }

    private SourceFileAttribute parseSourceFileAttribute(Utf8Constant attributeName) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Integer nameConstantPoolIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Utf8Constant sourceFileName = (Utf8Constant) this.constantPool.getConstantPoolElement(nameConstantPoolIndex - 1);
        return new SourceFileAttribute(attributeName, attributeLength, nameConstantPoolIndex, sourceFileName);
    }

    private StackMapTableAttribute parseStackMapTableAttribute(Utf8Constant attributeNane) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, attributeLength.intValue()));
        return null;
    }

    private AttributeBase parseExceptionsAttribute(Utf8Constant attributeName) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, attributeLength.intValue()));
        return null;
    }

    private AttributeBase parseBootstrapMethods(Utf8Constant attributeName) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, attributeLength.intValue()));
        return null;
    }

    private AttributeBase parseInnerClassesAttribute(Utf8Constant attributeName) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, attributeLength.intValue()));
        return null;
    }
}
