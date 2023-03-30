package com.example.parser.visitors;

import com.example.parser.interfaces.Visitor;
import com.example.parser.types.constantPool.ConstantPool;
import com.example.parser.types.constantPool.constants.*;
import com.example.parser.types.constantPool.constants.methods.InterfaceMethodRefConstant;
import com.example.parser.types.constantPool.constants.methods.MethodHandleConstant;
import com.example.parser.types.constantPool.constants.methods.MethodRefConstant;
import com.example.parser.types.constantPool.constants.methods.MethodTypeConstant;
import com.example.parser.types.constantPool.constants.numeric.DoubleConstant;
import com.example.parser.types.constantPool.constants.numeric.FloatConstant;
import com.example.parser.types.constantPool.constants.numeric.IntegerConstant;
import com.example.parser.types.constantPool.constants.numeric.LongConstant;
import com.example.parser.types.constantPool.constants.strings.StringConstant;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;

public class ConstantPoolVisitor implements Visitor {

    private final ConstantPool constantPool;

    public ConstantPoolVisitor(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void visit(ClassConstant classConstant) {
        classConstant.setClassName((Utf8Constant) constantPool.getConstantPoolElement(classConstant.getNameIndex() - 1));
    }

    @Override
    public void visit(DynamicConstant dynamicConstant) {
        dynamicConstant.setNameAndType((NameAndTypeConstant) constantPool.getConstantPoolElement(dynamicConstant.getNameAndTypeIndex() - 1));
    }

    @Override
    public void visit(FieldRefConstant fieldRefConstant) {
        fieldRefConstant.setClassConstant((ClassConstant) constantPool.getConstantPoolElement(fieldRefConstant.getClassIndex() - 1));
        fieldRefConstant.setNameAndTypeConstant((NameAndTypeConstant) constantPool.getConstantPoolElement(fieldRefConstant.getNameAndTypeIndex() - 1));
    }

    @Override
    public void visit(InvokeDynamicConstant invokeDynamicConstant) {
        invokeDynamicConstant.setNameAndType((NameAndTypeConstant) constantPool.getConstantPoolElement(invokeDynamicConstant.getNameAndTypeIndex() - 1));
    }

    @Override
    public void visit(ModuleConstant moduleConstant) {
        moduleConstant.setName((Utf8Constant) constantPool.getConstantPoolElement(moduleConstant.getNameIndex() - 1));
    }

    @Override
    public void visit(NameAndTypeConstant nameAndTypeConstant) {
        nameAndTypeConstant.setName((Utf8Constant) constantPool.getConstantPoolElement(nameAndTypeConstant.getNameIndex() - 1));
        nameAndTypeConstant.setType((Utf8Constant) constantPool.getConstantPoolElement(nameAndTypeConstant.getDescriptorIndex() - 1));
    }

    @Override
    public void visit(PackageConstant packageConstant) {
        packageConstant.setName((Utf8Constant) constantPool.getConstantPoolElement(packageConstant.getNameIndex() - 1));
    }

    @Override
    public void visit(InterfaceMethodRefConstant interfaceMethodRefConstant) {
        interfaceMethodRefConstant.setClassConstant((ClassConstant) constantPool.getConstantPoolElement(interfaceMethodRefConstant.getClassIndex() - 1));
        interfaceMethodRefConstant.setNameAndTypeConstant((NameAndTypeConstant) constantPool.getConstantPoolElement(interfaceMethodRefConstant.getNameAndTypeIndex() - 1));
    }

    @Override
    public void visit(MethodHandleConstant methodHandleConstant) {
    }

    @Override
    public void visit(MethodRefConstant methodRefConstant) {
        methodRefConstant.setClassConstant((ClassConstant) constantPool.getConstantPoolElement(methodRefConstant.getClassConstantIndex() - 1));
        methodRefConstant.setNameAndTypeConstant((NameAndTypeConstant) constantPool.getConstantPoolElement(methodRefConstant.getNameAndTypeIndex() - 1));
    }

    @Override
    public void visit(MethodTypeConstant methodTypeConstant) {
        methodTypeConstant.setDescriptor((Utf8Constant) constantPool.getConstantPoolElement(methodTypeConstant.getDescriptorIndex() - 1));
    }

    @Override
    public void visit(DoubleConstant doubleConstant) {
    }

    @Override
    public void visit(FloatConstant floatConstant) {
    }

    @Override
    public void visit(IntegerConstant integerConstant) {
    }

    @Override
    public void visit(LongConstant longConstant) {
    }

    @Override
    public void visit(StringConstant stringConstant) {
        stringConstant.setValue((Utf8Constant) constantPool.getConstantPoolElement(stringConstant.getStringIndex() - 1));
    }

    @Override
    public void visit(Utf8Constant utf8Constant) {
    }
}
