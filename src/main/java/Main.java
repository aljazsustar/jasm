import parsing.ClassFileParser;
import types.ClassFile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Main {
    public static void main(String[] args) {
        compileJavaFile(args[0]);
        ClassFileParser parser = new ClassFileParser(args[0].replace(".java", ".class"));
        ClassFile classFile = parser.parse();
        System.out.println(classFile);
    }

    public static void compileJavaFile(String path) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler.run(null, null, null, path) != 0)
            throw new RuntimeException(String.format("Napaka pri prevajanju datoteke %s", path));
    }
}
