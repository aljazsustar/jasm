package com.jasm.parser.parsing;

import com.jasm.parser.exceptions.AttributeDoesNotExistException;
import com.jasm.parser.enums.AccessFlags;
import com.jasm.parser.types.attributes.Attributes;
import com.jasm.parser.types.constantPool.ConstantPool;
import com.jasm.parser.types.fields.FieldInfo;
import com.jasm.parser.types.fields.Fields;
import com.jasm.parser.util.ParsingUtil;

import java.io.BufferedInputStream;

public class FieldsParser {
    private final Integer fieldsCount;
    private final BufferedInputStream inputStream;
    private final ConstantPool constantPool;

    public FieldsParser(BufferedInputStream inputStream, Integer fieldsCount, ConstantPool constantPool) {
        this.fieldsCount = fieldsCount;
        this.inputStream = inputStream;
        this.constantPool = constantPool;
    }

    public Fields parse() throws AttributeDoesNotExistException {
        Fields fields = new Fields(this.fieldsCount);

        for (int i = 0; i < this.fieldsCount; i++) {
            fields.addField(this.parseField());
        }

        return fields;
    }

    private FieldInfo parseField() throws AttributeDoesNotExistException {
        AccessFlags accessFlags = new AccessFlags(ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2)));
        Integer nameIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer descriptorIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer attributesCount = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Attributes fieldAttributes = new AttributeParser(attributesCount, this.inputStream, this.constantPool).parse();
        return new FieldInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, fieldAttributes);
    }
}
