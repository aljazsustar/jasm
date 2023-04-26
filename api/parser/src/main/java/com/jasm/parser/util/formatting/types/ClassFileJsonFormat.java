package com.jasm.parser.util.formatting.types;

import com.jasm.parser.types.ClassFile;
import com.jasm.parser.visitors.FormattingVisitor;

import java.util.ArrayList;
import java.util.List;

public class ClassFileJsonFormat {
    private final List<ConstantPoolElementJsonFormat> constantPool;

    public ClassFileJsonFormat() {
        this.constantPool = new ArrayList<>();
    }

    public ClassFileJsonFormat classFileToJson(ClassFile classFile) {
        classFile.getConstant_pool().accept(new FormattingVisitor(), this.constantPool);
        return this;
    }
}
