package com.jasm.parser.interfaces;

import com.jasm.parser.types.constantPool.constants.methods.InterfaceMethodRefConstant;
import com.jasm.parser.types.constantPool.constants.methods.MethodHandleConstant;
import com.jasm.parser.types.constantPool.constants.methods.MethodRefConstant;
import com.jasm.parser.types.constantPool.constants.methods.MethodTypeConstant;
import com.jasm.parser.types.constantPool.constants.numeric.DoubleConstant;
import com.jasm.parser.types.constantPool.constants.numeric.FloatConstant;
import com.jasm.parser.types.constantPool.constants.numeric.IntegerConstant;
import com.jasm.parser.types.constantPool.constants.numeric.LongConstant;
import com.jasm.parser.types.constantPool.constants.strings.StringConstant;
import com.jasm.parser.types.constantPool.constants.strings.Utf8Constant;
import com.jasm.parser.types.constantPool.constants.*;

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
