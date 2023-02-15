package parsing;

import types.constantPool.ConstantPool;
import types.constantPool.constants.ClassConstant;
import types.interfaces.Interfaces;
import util.ParsingUtil;

import java.io.BufferedInputStream;

public class InterfacesParser {
    private final BufferedInputStream inputStream;
    private final Interfaces interfaces;
    private final ConstantPool constantPool;
    private final Integer intefacesCount;
    private Integer interfacesIndex;

    public InterfacesParser(BufferedInputStream inputStream, Integer interfacesCount, ConstantPool constantPool) {
        this.interfacesIndex = 0;
        this.inputStream = inputStream;
        this.intefacesCount = interfacesCount;
        this.constantPool = constantPool;
        this.interfaces = new Interfaces(this.intefacesCount);
    }

    public Interfaces parseInterfaces() {
        for (; this.interfacesIndex < this.intefacesCount; this.interfacesIndex++) {
            this.interfaces.addToInterfaces(this.parseInterfaceElement());
        }
        return interfaces;
    }

    private ClassConstant parseInterfaceElement() {
        Integer constantPoolIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        return (ClassConstant) this.constantPool.getConstantPoolElement(constantPoolIndex - 1);
    }
}
