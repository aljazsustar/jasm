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

public interface Visitor {

    public void visit(ClassConstant classConstant);
    public void visit(DynamicConstant dynamicConstant);
    public void visit(FieldRefConstant fieldRefConstant);
    public void visit(InvokeDynamicConstant invokeDynamicConstant);
    public void visit(ModuleConstant moduleConstant);
    public void visit(NameAndTypeConstant nameAndTypeConstant);
    public void visit(PackageConstant packageConstant);
    public void visit(InterfaceMethodRefConstant interfaceMethodRefConstant);
    public void visit(MethodHandleConstant methodHandleConstant);
    public void visit(MethodRefConstant methodRefConstant);
    public void visit(MethodTypeConstant classConstant);
    public void visit(DoubleConstant doubleConstant);
    public void visit(FloatConstant floatConstant);
    public void visit(IntegerConstant integerConstant);
    public void visit(LongConstant longConstant);
    public void visit(StringConstant stringConstant);
    void visit(Utf8Constant utf8Constant);
}
