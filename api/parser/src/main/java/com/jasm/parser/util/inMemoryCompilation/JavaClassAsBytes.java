package com.jasm.parser.util.inMemoryCompilation;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.net.URI;

public class JavaClassAsBytes extends SimpleJavaFileObject {

    protected ByteArrayOutputStream bos =
            new ByteArrayOutputStream();

    public JavaClassAsBytes(String name, Kind kind) {
        super(URI.create("string:///" + name.replace('.', '/')
                + kind.extension), kind);
    }

    public byte[] getBytes() {
        return bos.toByteArray();
    }

    @Override
    public ByteArrayOutputStream openOutputStream() {
        return bos;
    }
}
