package parsing;

import enums.ConstantPoolTags;
import types.constantPool.ConstantPool;
import types.constantPool.constants.*;
import types.constantPool.constants.methods.InterfaceMethodRefConstant;
import types.constantPool.constants.methods.MethodHandleConstant;
import types.constantPool.constants.methods.MethodRefConstant;
import types.constantPool.constants.methods.MethodTypeConstant;
import types.constantPool.constants.numeric.DoubleConstant;
import types.constantPool.constants.numeric.FloatConstant;
import types.constantPool.constants.numeric.IntegerConstant;
import types.constantPool.constants.numeric.LongConstant;
import types.constantPool.constants.strings.StringConstant;
import types.constantPool.constants.strings.Utf8Constant;
import util.ParsingUtil;

import java.io.BufferedInputStream;

public class ConstantPoolParser {

    private final BufferedInputStream inputStream;
    private final ConstantPool constantPool;
    private final Integer constantPoolSize;
    private Integer constantPoolIndex;

    public ConstantPoolParser(BufferedInputStream inputStream, Integer constantPoolCount) {
        this.inputStream = inputStream;
        this.constantPoolSize = constantPoolCount;
        this.constantPool = new ConstantPool(constantPoolCount);
        this.constantPoolIndex = 0;
    }

    public ConstantPool parseConstantPool() {

        for (; this.constantPoolIndex < this.constantPoolSize; this.constantPoolIndex++) {
            Integer tag = this.parseTag();

            switch (tag) {
                case ConstantPoolTags.CONSTANT_Class:
                    constantPool.addToConstantPool(this.parseClassConstant());
                    break;
                case ConstantPoolTags.CONSTANT_Methodref:
                    constantPool.addToConstantPool(this.parseMethodRefConstant());
                    break;
                case ConstantPoolTags.CONSTANT_Fieldref:
                    constantPool.addToConstantPool(this.parseFieldRefConstant());
                    break;
                case ConstantPoolTags.CONSTANT_NameAndType:
                    constantPool.addToConstantPool(this.parseNameAndTypeConstant());
                    break;
                case ConstantPoolTags.CONSTANT_Utf8:
                    constantPool.addToConstantPool(this.parseUtf8Constant());
                    break;
                case ConstantPoolTags.CONSTANT_String:
                    constantPool.addToConstantPool(this.parseStringConstant());
                    break;
                case ConstantPoolTags.CONSTANT_Integer:
                    constantPool.addToConstantPool(this.parseIntegerConstant());
                    break;
                case ConstantPoolTags.CONSTANT_Long:
                    constantPool.addToConstantPool(this.parseLongConstant());
                    break;
                case ConstantPoolTags.CONSTANT_Float:
                    constantPool.addToConstantPool(this.parseFloatConstant());
                    break;
                case ConstantPoolTags.CONSTANT_Double:
                    constantPool.addToConstantPool(this.parseDoubleConstant());
                    break;
                case ConstantPoolTags.CONSTANT_InterfaceMethodref:
                    constantPool.addToConstantPool(this.parseInterfaceMethodRefConstant());
                    break;
                case ConstantPoolTags.CONSTANT_MethodHandle:
                    constantPool.addToConstantPool(this.parseMethodHandleConstant());
                    break;
                case ConstantPoolTags.CONSTANT_MethodType:
                    constantPool.addToConstantPool(this.parseMethodTypeConstant());
                    break;
                case ConstantPoolTags.CONSTANT_Dynamic:
                    constantPool.addToConstantPool(this.parseDynamicConstant());
                    break;
                case ConstantPoolTags.CONSTANT_InvokeDynamic:
                    constantPool.addToConstantPool(this.parseInvokeDynamicConstant());
                    break;
                case ConstantPoolTags.CONSTANT_Module:
                    constantPool.addToConstantPool(this.parseModuleConstant());
                    break;
                case ConstantPoolTags.CONSTANT_Package:
                    constantPool.addToConstantPool(this.parsePackageConstant());
                    break;
            }
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

    private FieldRefConstant parseFieldRefConstant() {
        FieldRefConstant fieldRefConstant = new FieldRefConstant(this.constantPoolIndex);
        Integer classIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer nameAndTypeIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        fieldRefConstant.setClassIndex(classIndex);
        fieldRefConstant.setNameAndTypeIndex(nameAndTypeIndex);
        return fieldRefConstant;
    }

    private MethodRefConstant parseMethodRefConstant() {
        MethodRefConstant methodRefConstant = new MethodRefConstant(this.constantPoolIndex);
        Integer classIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer nameAndTypeIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        methodRefConstant.setClassConstantIndex(classIndex);
        methodRefConstant.setNameAndTypeIndex(nameAndTypeIndex);
        return methodRefConstant;
    }

    private NameAndTypeConstant parseNameAndTypeConstant() {
        NameAndTypeConstant nameAndTypeConstant = new NameAndTypeConstant(this.constantPoolIndex);
        Integer nameIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer descriptorIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        nameAndTypeConstant.setNameIndex(nameIndex);
        nameAndTypeConstant.setDescroptorIndex(descriptorIndex);
        return nameAndTypeConstant;
    }

    private Utf8Constant parseUtf8Constant() {
        Utf8Constant utf8Constant = new Utf8Constant(this.constantPoolIndex);
        Integer length = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        byte[] utf8Bytes = ParsingUtil.readNBytes(this.inputStream, length);
        utf8Constant.setValue(new String(utf8Bytes));
        return utf8Constant;
    }

    private StringConstant parseStringConstant() {
        StringConstant stringConstant = new StringConstant(this.constantPoolIndex);
        Integer stringIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        stringConstant.setStringIndex(stringIndex);
        return stringConstant;
    }

    private IntegerConstant parseIntegerConstant() {
        IntegerConstant integerConstant = new IntegerConstant(this.constantPoolIndex);
        Integer value = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 4));
        integerConstant.setValue(value);
        return integerConstant;
    }

    private LongConstant parseLongConstant() {
        LongConstant longConstant = new LongConstant(this.constantPoolIndex);
        Long value = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 8));
        longConstant.setValue(value);
        return longConstant;
    }

    private FloatConstant parseFloatConstant() {
        FloatConstant floatConstant = new FloatConstant(this.constantPoolIndex);
        Integer value = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 4));
        floatConstant.setValue(Float.intBitsToFloat(value));
        return floatConstant;
    }

    private DoubleConstant parseDoubleConstant() {
        DoubleConstant doubleConstant = new DoubleConstant(this.constantPoolIndex);
        Long value = ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 8));
        doubleConstant.setValue(Double.longBitsToDouble(value));
        return doubleConstant;
    }

    private InterfaceMethodRefConstant parseInterfaceMethodRefConstant() {
        InterfaceMethodRefConstant interfaceMethodRefConstant = new InterfaceMethodRefConstant(this.constantPoolIndex);
        Integer classIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer nameAndTypeIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        interfaceMethodRefConstant.setClassIndex(classIndex);
        interfaceMethodRefConstant.setNameAndTypeIndex(nameAndTypeIndex);
        return interfaceMethodRefConstant;
    }

    private MethodHandleConstant parseMethodHandleConstant() {
        MethodHandleConstant methodHandleConstant = new MethodHandleConstant(this.constantPoolIndex);
        Integer referenceKind = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer referenceIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        methodHandleConstant.setReferenceKind(referenceKind);
        methodHandleConstant.setReferenceIndex(referenceIndex);
        return methodHandleConstant;
    }

    private MethodTypeConstant parseMethodTypeConstant() {
        MethodTypeConstant methodTypeConstant = new MethodTypeConstant(this.constantPoolIndex);
        Integer descriptorIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        methodTypeConstant.setDescriptorIndex(descriptorIndex);
        return methodTypeConstant;
    }

    private DynamicConstant parseDynamicConstant() {
        DynamicConstant dynamicConstant = new DynamicConstant(this.constantPoolIndex);
        Integer bootstrapMethodAttrIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer nameAndTypeIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        dynamicConstant.setBootstrapMethodAttrIndex(bootstrapMethodAttrIndex);
        dynamicConstant.setNameAndTypeIndex(nameAndTypeIndex);
        return dynamicConstant;
    }

    private InvokeDynamicConstant parseInvokeDynamicConstant() {
        InvokeDynamicConstant invokeDynamicConstant = new InvokeDynamicConstant(this.constantPoolIndex);
        Integer bootstrapMethodAttrIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        Integer nameAndTypeIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        invokeDynamicConstant.setBootstrapMethodAttrIndex(bootstrapMethodAttrIndex);
        invokeDynamicConstant.setNameAndTypeIndex(nameAndTypeIndex);
        return invokeDynamicConstant;
    }

    private ModuleConstant parseModuleConstant() {
        ModuleConstant moduleConstant = new ModuleConstant(this.constantPoolIndex);
        Integer nameIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        moduleConstant.setNameIndex(nameIndex);
        return moduleConstant;
    }

    private PackageConstant parsePackageConstant() {
        PackageConstant packageConstant = new PackageConstant(this.constantPoolIndex);
        Integer nameIndex = ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
        packageConstant.setNameIndex(nameIndex);
        return packageConstant;
    }
}
