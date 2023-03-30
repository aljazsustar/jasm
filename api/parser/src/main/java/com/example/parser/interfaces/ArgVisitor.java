package com.example.parser.interfaces;

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

public interface ArgVisitor<T> {
    public void visit(ClassConstant classConstant, T arg);
    public void visit(DynamicConstant dynamicConstant, T arg);
    public void visit(FieldRefConstant fieldRefConstant, T arg);
    public void visit(InvokeDynamicConstant invokeDynamicConstant, T arg);
    public void visit(ModuleConstant moduleConstant, T arg);
    public void visit(NameAndTypeConstant nameAndTypeConstant, T arg);
    public void visit(PackageConstant packageConstant, T arg);
    public void visit(InterfaceMethodRefConstant interfaceMethodRefConstant, T arg);
    public void visit(MethodHandleConstant methodHandleConstant, T arg);
    public void visit(MethodRefConstant methodRefConstant, T arg);
    public void visit(MethodTypeConstant classConstant, T arg);
    public void visit(DoubleConstant doubleConstant, T arg);
    public void visit(FloatConstant floatConstant, T arg);
    public void visit(IntegerConstant integerConstant, T arg);
    public void visit(LongConstant longConstant, T arg);
    public void visit(StringConstant stringConstant, T arg);
    void visit(Utf8Constant utf8Constant, T arg);
}
