package com.example.parser.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class InvocationOutputStream extends OutputStream {
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private final PrintStream original;

    public InvocationOutputStream(PrintStream original) {
        this.original = original;
    }

    @Override
    public void write(int b) {
        original.write(b);
        buffer.write(b);
    }

    public String getAndClear() {
        String s = buffer.toString(StandardCharsets.UTF_8);
        buffer.reset();
        return s;
    }

    public PrintStream getOriginal() {
        return original;
    }
}

