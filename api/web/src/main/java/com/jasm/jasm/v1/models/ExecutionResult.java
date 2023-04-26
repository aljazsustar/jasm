package com.jasm.jasm.v1.models;

public class ExecutionResult {
    private String stdOut;
    private byte[] compiledClassFile;

    public ExecutionResult(String stdOut, byte[] compiledClassFile) {
        this.stdOut = stdOut;
        this.compiledClassFile = compiledClassFile;
    }

    public String getStdOut() {
        return stdOut;
    }

    public void setStdOut(String stdOut) {
        this.stdOut = stdOut;
    }

    public byte[] getCompiledClassFile() {
        return compiledClassFile;
    }

    public void setCompiledClassFile(byte[] compiledClassFile) {
        this.compiledClassFile = compiledClassFile;
    }
}
