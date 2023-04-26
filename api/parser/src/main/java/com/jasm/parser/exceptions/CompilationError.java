package com.jasm.parser.exceptions;

public class CompilationError extends Error {

    public CompilationError() {
    }

    public CompilationError(String message) {
        super(message);
    }
}
