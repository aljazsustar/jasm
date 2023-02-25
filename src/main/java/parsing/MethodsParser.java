package parsing;

import enums.AccessFlags;
import exceptions.AttributeDoesNotExistException;
import types.attributes.Attributes;
import types.constantPool.ConstantPool;
import types.methods.MethodInfo;
import types.methods.Methods;
import util.ParsingUtil;

import java.io.BufferedInputStream;

public class MethodsParser {
    private final Integer methodsCount;
    private final BufferedInputStream inputStream;
    private final ConstantPool constantPool;

    public MethodsParser(BufferedInputStream inputStream, Integer methodsCount, ConstantPool constantPool) {
        this.methodsCount = methodsCount;
        this.inputStream = inputStream;
        this.constantPool = constantPool;
    }

    public Methods parse() throws AttributeDoesNotExistException {
        Methods methods = new Methods(this.methodsCount);

        for (int i = 0; i < this.methodsCount; i++) {
            methods.addMethod(this.parseMethod());
        }

        return methods;
    }

    private MethodInfo parseMethod() throws AttributeDoesNotExistException {
        AccessFlags accessFlags = new AccessFlags(ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2)));
        Integer nameIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        System.out.println(this.constantPool.getConstantPoolElement(nameIndex - 1));
        Integer descriptorIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer attributesCount = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Attributes fieldAttributes = new AttributeParser(attributesCount, this.inputStream, this.constantPool).parse();
        return new MethodInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, fieldAttributes);
    }
}
