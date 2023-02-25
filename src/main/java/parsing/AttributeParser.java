package parsing;

import exceptions.AttributeDoesNotExistException;
import interfaces.AttributeBase;
import interfaces.ConstantValue;
import types.attributes.Attributes;
import types.attributes.criticalAttributes.CodeAttribute;
import types.attributes.criticalAttributes.ConstantValueAttribute;
import types.attributes.usefulAttributes.LineNumberTableAttribute;
import types.attributes.util.AttributesUtil;
import types.attributes.util.types.code.Code;
import types.attributes.util.types.code.Exceptions;
import types.attributes.util.types.lineNumberTable.LineNumberTable;
import types.constantPool.ConstantPool;
import types.constantPool.constants.strings.Utf8Constant;
import util.ParsingUtil;

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
}
