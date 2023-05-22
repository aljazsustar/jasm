package com.jasm.parser.exceptions;

public class ExecutionError extends Error {
    public ExecutionError() {
    }

    public ExecutionError(String message) {
        super(message);
    }
}
