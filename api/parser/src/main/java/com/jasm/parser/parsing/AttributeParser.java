package com.jasm.parser.parsing;

import com.jasm.parser.exceptions.AttributeDoesNotExistException;
import com.jasm.parser.interfaces.AttributeBase;
import com.jasm.parser.interfaces.ConstantValue;
import com.jasm.parser.interfaces.StackMapFrameBase;
import com.jasm.parser.types.attributes.Attributes;
import com.jasm.parser.types.attributes.criticalAttributes.CodeAttribute;
import com.jasm.parser.types.attributes.criticalAttributes.ConstantValueAttribute;
import com.jasm.parser.types.attributes.criticalAttributes.StackMapTableAttribute;
import com.jasm.parser.types.attributes.optionalAttributes.RuntimeInvisibleAnnotations;
import com.jasm.parser.types.attributes.usefulAttributes.LineNumberTableAttribute;
import com.jasm.parser.types.attributes.usefulAttributes.SourceFileAttribute;
import com.jasm.parser.types.attributes.util.AttributesUtil;
import com.jasm.parser.types.attributes.util.types.annotations.Annotations;
import com.jasm.parser.types.attributes.util.types.code.Code;
import com.jasm.parser.types.attributes.util.types.code.Exceptions;
import com.jasm.parser.types.attributes.util.types.lineNumberTable.LineNumberTable;
import com.jasm.parser.types.attributes.util.types.stackMapTable.StackMapFrame;
import com.jasm.parser.types.constantPool.ConstantPool;
import com.jasm.parser.types.constantPool.constants.strings.Utf8Constant;
import com.jasm.parser.util.ParsingUtil;

import java.io.BufferedInputStream;
import java.util.List;

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
        Integer attributeNameIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Utf8Constant attributeName = (Utf8Constant) this.constantPool.getConstantPoolElement(attributeNameIndex - 1);
        switch (attributeName.getValue()) {
            case "ConstantValue":
                return this.parseConstantValueAttribute(attributeNameIndex, attributeName);
            case "Code":
                return this.parseCodeAttribute(attributeNameIndex, attributeName);
            case "LineNumberTable":
                return this.parseLineNumberTableAttribute(attributeNameIndex, attributeName);
            case "SourceFile":
                return this.parseSourceFileAttribute(attributeNameIndex, attributeName);
            case "StackMapTable":
                return this.parseStackMapTableAttribute(attributeNameIndex, attributeName);
            case "Exceptions":
                return this.parseExceptionsAttribute(attributeName);
            case "BootstrapMethods":
                return this.parseBootstrapMethods(attributeName);
            case "InnerClasses":
                return this.parseInnerClassesAttribute(attributeName);
            case "RuntimeInvisibleAnnotations":
                return this.parseRuntimeVisibleAnnotationsAttribute(attributeNameIndex, attributeName);

        }
        throw new AttributeDoesNotExistException();
    }

    private ConstantValueAttribute parseConstantValueAttribute(Integer attributeNameIndex, Utf8Constant attributeName) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Integer constantValueIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        ConstantValueAttribute constantValueAttribute = new ConstantValueAttribute(attributeNameIndex, attributeName, attributeLength, constantValueIndex);
        constantValueAttribute.setConstantValue((ConstantValue) this.constantPool.getConstantPoolElement(constantValueIndex - 1));
        return constantValueAttribute;
    }

    private CodeAttribute parseCodeAttribute(Integer attributeNameIndex, Utf8Constant attributeName) throws AttributeDoesNotExistException {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Integer maxStack = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer maxLocals = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Long codeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Code code = AttributesUtil.parseCode(this.inputStream, codeLength);
        Integer exceptionTableLength = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Exceptions exceptions = AttributesUtil.parseExceptions(this.inputStream, this.constantPool, exceptionTableLength);
        Integer attributesCount = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Attributes attributes = new AttributeParser(attributesCount, this.inputStream, this.constantPool).parse();
        return new CodeAttribute(attributeNameIndex, attributeLength, attributeName, maxStack, maxLocals, codeLength, code, exceptionTableLength,
                exceptions, attributesCount, attributes);
    }

    private LineNumberTableAttribute parseLineNumberTableAttribute(Integer attributeNameIndex, Utf8Constant attributeName) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Integer lineNumberTableLength = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        LineNumberTable lineNumberTable = AttributesUtil.parseLineNumberTable(this.inputStream, lineNumberTableLength);
        return new LineNumberTableAttribute(attributeNameIndex, attributeName, attributeLength, lineNumberTableLength, lineNumberTable);
    }

    private SourceFileAttribute parseSourceFileAttribute(Integer attributeNameIndex, Utf8Constant attributeName) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Integer nameConstantPoolIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Utf8Constant sourceFileName = (Utf8Constant) this.constantPool.getConstantPoolElement(nameConstantPoolIndex - 1);
        return new SourceFileAttribute(attributeNameIndex, attributeName, attributeLength, nameConstantPoolIndex, sourceFileName);
    }

    private StackMapTableAttribute parseStackMapTableAttribute(Integer attributeNameIndex, Utf8Constant attributeNane) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        List<StackMapFrame<? extends StackMapFrameBase>> entries = AttributesUtil.parseStackMapFrames(inputStream);
        return new StackMapTableAttribute(attributeLength, attributeNameIndex, attributeNane, entries);
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

    private AttributeBase parseRuntimeVisibleAnnotationsAttribute(Integer attributeNameIndex, Utf8Constant attributeName) {
        Long attributeLength = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
        Integer numAnnotations = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Annotations attributes = AttributesUtil.parseAnnotations(this.inputStream, this.constantPool, numAnnotations);
        return new RuntimeInvisibleAnnotations(attributeNameIndex, attributeName, attributeLength, numAnnotations, attributes);
    }
}
