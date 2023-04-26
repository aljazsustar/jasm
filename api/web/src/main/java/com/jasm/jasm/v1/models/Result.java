package com.jasm.jasm.v1.models;

import com.google.gson.Gson;
import com.jasm.parser.util.formatting.types.ClassFileJsonFormat;

public class Result {
    private ClassFileJsonFormat classFile;
    private ExecutionResult executionResult;
    private Error error;

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

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
