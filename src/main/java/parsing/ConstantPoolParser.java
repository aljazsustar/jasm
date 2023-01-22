package parsing;

import types.constantPool.ConstantPool;
import types.constantPool.constants.ClassConstant;
import types.constantPool.constants.methods.MethodRefConstant;
import util.ParsingUtil;
import enums.ConstantPoolTags;

import java.io.BufferedInputStream;

public class ConstantPoolParser {

    private final BufferedInputStream inputStream;
    private final ConstantPool constantPool;
    private Integer constantPoolIndex;

    public ConstantPoolParser(BufferedInputStream inputStream, Integer constantPoolCount) {
        this.inputStream = inputStream;
        this.constantPool = new ConstantPool(constantPoolCount);
        this.constantPoolIndex = 0;
    }

    public ConstantPool parseConstantPool() {
        Integer tag = this.parseTag();

        System.out.println(tag);
        switch (tag) {
            case ConstantPoolTags.CONSTANT_Class:
                constantPool.addToConstantPool(this.parseClassConstant());
                break;
            case ConstantPoolTags.CONSTANT_Methodref:
                constantPool.addToConstantPool(this.parseMethodRefConstant());
                break;
        }

        return this.constantPool;
    }

    private Integer parseTag() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 1));
    }

    private ClassConstant parseClassConstant() {
        ClassConstant classConstant = new ClassConstant(this.constantPoolIndex);
        Integer nameIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        classConstant.setNameIndex(nameIndex);
        return classConstant;
    }

    private MethodRefConstant parseMethodRefConstant() {
        MethodRefConstant methodRefConstant = new MethodRefConstant(this.constantPoolIndex);
        Integer classIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer nameAndTypeIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        methodRefConstant.setClassConstantIndex(classIndex);
        methodRefConstant.setNameAndTypeIndex(nameAndTypeIndex);
        return methodRefConstant;
    }
}
