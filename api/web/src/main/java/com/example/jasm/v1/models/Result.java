package com.example.jasm.v1.models;

import com.example.parser.types.ClassFile;
import com.example.parser.util.formatting.types.ClassFileJsonFormat;
import com.google.gson.Gson;

public class Result {
    private ClassFileJsonFormat classFile;
    private ExecutionResult executionResult;

    public ClassFileJsonFormat getClassFile() {
        return classFile;
    }

    public void setClassFile(ClassFileJsonFormat classFile) {
        this.classFile = classFile;
    }

    public ExecutionResult getExecutionResult() {
        return executionResult;
    }

    public void setExecutionResult(ExecutionResult executionResult) {
        this.executionResult = executionResult;
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
