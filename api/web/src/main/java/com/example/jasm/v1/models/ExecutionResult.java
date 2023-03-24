package com.example.jasm.v1.models;

public class ExecutionResult {
    private String stdOut;

    public ExecutionResult(String stdOut) {
        this.stdOut = stdOut;
    }

    public String getStdOut() {
        return stdOut;
    }

    public void setStdOut(String stdOut) {
        this.stdOut = stdOut;
    }
}
