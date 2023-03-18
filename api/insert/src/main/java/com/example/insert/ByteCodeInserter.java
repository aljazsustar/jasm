package com.example.insert;

import com.example.parser.types.ClassFile;

public class ByteCodeInserter {

    private ClassFile classFile;
    private String source;

    public ByteCodeInserter(ClassFile classFile, String source) {
        this.classFile = classFile;
        this.source = source;
    }

    public ClassFile getClassFile() {
        return classFile;
    }

    public void setClassFile(ClassFile classFile) {
        this.classFile = classFile;
    }
}
