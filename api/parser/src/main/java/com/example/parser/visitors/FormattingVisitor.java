package com.example.parser.visitors;

import com.example.parser.interfaces.ArgVisitor;
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
import com.example.parser.util.formatting.types.ConstantPoolElementJsonFormat;

import java.util.List;

public class FormattingVisitor implements ArgVisitor<List<ConstantPoolElementJsonFormat>> {

    @Override
    public void visit(ClassConstant classConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = classConstant.getConstantPoolIndex();
        el.type = "Class";
        el.name = classConstant.getClassName().getValue();
        arg.add(el);
    }

    @Override
    public void visit(DynamicConstant dynamicConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = dynamicConstant.getConstantPoolIndex() + 1;
        el.type = "Dynamic";
        el.name = dynamicConstant.getNameAndType().getName().getValue();
        arg.add(el);
    }

    @Override
    public void visit(FieldRefConstant fieldRefConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = fieldRefConstant.getConstantPoolIndex() + 1;
        el.type = "Field";
        el.name = fieldRefConstant.getNameAndTypeConstant().getName().getValue();
        arg.add(el);
    }

    @Override
    public void visit(InvokeDynamicConstant invokeDynamicConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = invokeDynamicConstant.getConstantPoolIndex() + 1;
        el.type = "InvokeDynamic";
        el.name = invokeDynamicConstant.getNameAndType().getName().getValue();
        arg.add(el);
    }

    @Override
    public void visit(ModuleConstant moduleConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = moduleConstant.getConstantPoolIndex() + 1;
        el.type = "Module";
        el.name = moduleConstant.getName().getValue();
        arg.add(el);
    }

    @Override
    public void visit(NameAndTypeConstant nameAndTypeConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = nameAndTypeConstant.getConstantPoolIndex() + 1;
        el.type = "NameAndType";
        el.name = nameAndTypeConstant.getName().getValue();
        arg.add(el);
    }

    @Override
    public void visit(PackageConstant packageConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = packageConstant.getConstantPoolIndex() + 1;
        el.type = "Package";
        el.name = packageConstant.getName().getValue();
        arg.add(el);
    }

    @Override
    public void visit(InterfaceMethodRefConstant interfaceMethodRefConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = interfaceMethodRefConstant.getConstantPoolIndex() + 1;
        el.type = "InterfaceMethodRef";
        el.name = interfaceMethodRefConstant.getNameAndTypeConstant().getName().getValue();
        arg.add(el);
    }

    @Override
    public void visit(MethodHandleConstant methodHandleConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = methodHandleConstant.getConstantPoolIndex() + 1;
        el.type = "MethodHandle";
        el.value = methodHandleConstant.getReferenceIndex().toString();
        arg.add(el);
    }

    @Override
    public void visit(MethodRefConstant methodRefConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = methodRefConstant.getConstantPoolIndex() + 1;
        el.type = "MethodRef";
        el.name = methodRefConstant.getClassConstant().getClassName().getValue() + "." + methodRefConstant.getNameAndTypeConstant().getName().getValue();
        arg.add(el);
    }

    @Override
    public void visit(MethodTypeConstant methodTypeConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = methodTypeConstant.getConstantPoolIndex() + 1;
        el.type = "MethodType";
        el.name = methodTypeConstant.getDescriptor().getValue();
        arg.add(el);
    }

    @Override
    public void visit(DoubleConstant doubleConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = doubleConstant.getConstantPoolIndex() + 1;
        el.type = "Double";
        el.value = doubleConstant.getValue().toString();
        arg.add(el);
    }

    @Override
    public void visit(FloatConstant floatConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = floatConstant.getConstantPoolIndex() + 1;
        el.type = "Float";
        el.value = floatConstant.getValue().toString() + "F";
        arg.add(el);
    }

    @Override
    public void visit(IntegerConstant integerConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = integerConstant.getConstantPoolIndex() + 1;
        el.type = "Integer";
        el.value = integerConstant.getValue().toString();
        arg.add(el);
    }

    @Override
    public void visit(LongConstant longConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = longConstant.getConstantPoolIndex() + 1;
        el.type = "Long";
        el.value = longConstant.getValue().toString() + "L";
        arg.add(el);
    }

    @Override
    public void visit(StringConstant stringConstant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = stringConstant.getConstantPoolIndex() + 1;
        el.type = "String";
        el.value = stringConstant.getValue().getValue();
        arg.add(el);
    }

    @Override
    public void visit(Utf8Constant utf8Constant, List<ConstantPoolElementJsonFormat> arg) {
        ConstantPoolElementJsonFormat el = new ConstantPoolElementJsonFormat();
        el.constantPoolIndex = utf8Constant.getConstantPoolIndex() + 1;
        el.type = "Utf8";
        el.value = utf8Constant.getValue();
        arg.add(el);
    }
}
