package com.example.parser.parsing;

import com.example.parser.exceptions.AttributeDoesNotExistException;
import com.example.parser.types.ClassFile;
import com.example.parser.types.attributes.Attributes;
import com.example.parser.types.constantPool.ConstantPool;
import com.example.parser.types.constantPool.constants.ClassConstant;
import com.example.parser.types.fields.Fields;
import com.example.parser.types.interfaces.Interfaces;
import com.example.parser.types.methods.Methods;
import com.example.parser.util.ParsingUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassFileParser {
    private final BufferedInputStream inputStream;

    public ClassFileParser(String path) {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("Razredne datoteke '%s' ni bilo mogoče najti.", path));
        }
        this.inputStream = new BufferedInputStream(inputStream);
    }

    public ClassFile parse() throws AttributeDoesNotExistException {
        ClassFile classFile = new ClassFile();
        classFile.setMagic(this.parseMagic());
        classFile.setMinor_version(this.parseMinorVersion());
        classFile.setMajor_version(this.parseMajorVersion());
        classFile.setConstant_pool_count(this.parseConstantPoolCount());
        classFile.setConstant_pool(this.parseConstantPool(classFile.getConstant_pool_count()));
        classFile.setAccess_flags(this.parseAccessFlags());
        classFile.setThis_class((ClassConstant) classFile.getConstant_pool().getConstantPoolElement(this.parseThisClassConstantPoolIndex() - 1));
        classFile.setSuper_class((ClassConstant) classFile.getConstant_pool().getConstantPoolElement(this.parseSuperClassConstantPoolIndex() - 1));
        classFile.setInterfaces_count(this.parseInterfacesCount());
        classFile.setInterfaces(this.parseInterfaces(classFile.getInterfaces_count(), classFile.getConstant_pool()));
        classFile.setFields_count(this.parseFieldsCount());
        classFile.setFields(this.parseFields(classFile.getFields_count(), classFile.getConstant_pool()));
        classFile.setMethods_count(this.parseMethodsCount());
        classFile.setMethods(this.parseMethods(classFile.getMethods_count(), classFile.getConstant_pool()));
        classFile.setAttributes_count(this.parseAttributesCount());
        classFile.setAttributes(this.parseAttributes(classFile.getAttributes_count(), classFile.getConstant_pool()));
        this.closeInputStream();
        return classFile;
    }

    private void closeInputStream() {
        try {
            this.inputStream.close();
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Napaka pri zapiranju vhoda");
        }
    }


    private Long parseMagic() {
        return ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
    }

    private Integer parseMinorVersion() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Integer parseMajorVersion() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Integer parseConstantPoolCount() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private ConstantPool parseConstantPool(Integer constantPoolCount) {
        return new ConstantPoolParser(this.inputStream, constantPoolCount).parseConstantPool();
    }

    private Integer parseAccessFlags() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Integer parseThisClassConstantPoolIndex() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Integer parseSuperClassConstantPoolIndex() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Integer parseInterfacesCount() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Interfaces parseInterfaces(Integer interfacesCount, ConstantPool constantPool) {
        return new InterfacesParser(this.inputStream, interfacesCount, constantPool).parseInterfaces();
    }

    private Integer parseFieldsCount() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Fields parseFields(Integer fieldsCount, ConstantPool constantPool) throws AttributeDoesNotExistException {
        return new FieldsParser(this.inputStream, fieldsCount, constantPool).parse();
    }

    private Integer parseMethodsCount() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Methods parseMethods(Integer methodsCount, ConstantPool constantPool) throws AttributeDoesNotExistException {
        return new MethodsParser(this.inputStream, methodsCount, constantPool).parse();
    }

    private Integer parseAttributesCount() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Attributes parseAttributes(Integer attributesCount, ConstantPool constantPool) throws AttributeDoesNotExistException {
        return new AttributeParser(attributesCount, this.inputStream, constantPool).parse();
    }
}
