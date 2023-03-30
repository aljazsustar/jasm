package com.example.parser.util.formatting.types;

import com.example.parser.types.ClassFile;
import com.example.parser.visitors.FormattingVisitor;
import com.google.gson.Gson;

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
