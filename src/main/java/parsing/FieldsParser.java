package parsing;

import types.constantPool.ConstantPool;
import types.fields.Fields;

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

    public Fields parse() {
        return null;
    }
}
