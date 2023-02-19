package parsing;

import exceptions.AttributeDoesNotExistException;
import interfaces.AttributeBase;
import interfaces.ConstantValue;
import types.attributes.Attributes;
import types.attributes.criticalAttributes.ConstantValueAttribute;
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

}
