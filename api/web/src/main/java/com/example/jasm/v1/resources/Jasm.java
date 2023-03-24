package com.example.jasm.v1.resources;

import com.example.insert.ByteCodeInserter;
import com.example.insert.JasmBlocksParser;
import com.example.insert.types.JasmBlock;
import com.example.parser.exceptions.AttributeDoesNotExistException;
import com.example.parser.parsing.ClassFileParser;
import com.example.parser.types.ClassFile;
import com.example.parser.util.InMemoryFileManager;
import com.example.parser.util.InvocationOutputStream;
import com.example.parser.util.JavaSourceFromString;
import com.kumuluz.ee.cors.annotations.CrossOrigin;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("compile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
@RequestScoped
@CrossOrigin
public class Jasm {

    String source = "import com.example.insert.annotations.Block;\n" +
            "import com.example.insert.annotations.Jasm;\n" +
            "\n" +
            "public class MinExample {\n" +
            "\n" +
            "  @Jasm({\n" +
            "    @Block(start=11, end=15)\n" +
            "  })\n" +
            "  public static void main(String[] args) {\n" +
            "    /*\n" +
            "     getstatic 7\n" +
            "     bipush 8\n" +
            "     iconst_5\n" +
            "     invokestatic 13\n" +
            "     invokevirtual 19\n" +
            "    */\n" +
            "    System.out.println(sum(8, 5));\n" +
            "  }\n" +
            "\n" +
            "  public static int sum(int a, int b) {\n" +
            "       return a+b;\n" +
            "  }\n" +
            "}\n";

    @Context
    protected UriInfo uriInfo;

    @POST
    @Path("getInfo")
    @PermitAll
    @CrossOrigin
    public Response getInfo(String content) {

        ClassFile cf = null;
        try {
            whenStringIsCompiled_ThenCodeShouldExecute("MinExample", this.source);
            cf = new ClassFileParser("MinExample.class").parse();
            List<JasmBlock> jasmBlocks = JasmBlocksParser.extractJasmBlocks(source, cf.getMethods().getJasmAnnotationsPerMethod());
            ByteCodeInserter.insertBytecode(jasmBlocks, cf);
            byte[] bytes = cf.writeBytes();
            //ParsingUtil.printBytes(bytes);
            File out = new File("MinExample.class");
            FileOutputStream os = new FileOutputStream(out);
            os.write(bytes);
        } catch (AttributeDoesNotExistException e) {
            return Response.serverError().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Response.ok(cf.toString()).header("Access-Control-Allow-Origin", "*").build();
    }

    public void whenStringIsCompiled_ThenCodeShouldExecute(String qualifiedClassName, String sourceCode) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, AttributeDoesNotExistException, IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        InMemoryFileManager manager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));
        List<String> optionList = new ArrayList<>(List.of("-classpath", "insert/src/main/java"));

        List<JavaFileObject> sourceFiles = Collections.singletonList(new JavaSourceFromString(qualifiedClassName, sourceCode));
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, optionList, null, sourceFiles);

        boolean result = task.call();

        PipedInputStream in = new PipedInputStream();
        new Thread(() -> {
            try (final PipedOutputStream out = new PipedOutputStream(in)) {
                manager.getBytesMap().get("MinExample").openOutputStream().writeTo(out);
            } catch (IOException e) {
                // logging and exception handling should go here
            }
        }).start();

        ClassFile cf = new ClassFileParser(new BufferedInputStream(in)).parse();
        List<JasmBlock> jasmBlocks = JasmBlocksParser.extractJasmBlocks(source, cf.getMethods().getJasmAnnotationsPerMethod());
        ByteCodeInserter.insertBytecode(jasmBlocks, cf);
        manager.getBytesMap().get("MinExample").openOutputStream().reset();
        manager.getBytesMap().get("MinExample").openOutputStream().write(cf.writeBytes());
        if (!result) {
            diagnostics.getDiagnostics()
                    .forEach(d -> Logger.getLogger("diagnostics").log(Level.SEVERE, String.valueOf(d)));
        } else {
            ClassLoader classLoader = manager.getClassLoader(null);
            InvocationOutputStream invocationOutputStream = new InvocationOutputStream(System.out);
            PrintStream out = System.out;
            System.setOut(new PrintStream(invocationOutputStream));
            Class<?> clazz = classLoader.loadClass(qualifiedClassName);
            Method m = clazz.getMethod("main", String[].class);
            m.invoke(null, (Object) null);
            String s = invocationOutputStream.getAndClear();
            System.setOut(out);
            System.setOut(invocationOutputStream.getOriginal());
        }
    }
}
