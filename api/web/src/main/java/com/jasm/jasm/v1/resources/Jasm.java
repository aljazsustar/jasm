package com.jasm.jasm.v1.resources;

import com.jasm.insert.ByteCodeInserter;
import com.jasm.insert.JasmBlocksParser;
import com.jasm.insert.types.JasmBlock;
import com.jasm.jasm.v1.models.ExecutionResult;
import com.jasm.jasm.v1.models.Result;
import com.jasm.jasm.v1.util.ExitSecurityManager;
import com.jasm.parser.exceptions.AttributeDoesNotExistException;
import com.jasm.parser.exceptions.CompilationError;
import com.jasm.parser.exceptions.ExecutionError;
import com.jasm.parser.exceptions.InvalidMnenonicException;
import com.jasm.parser.parsing.ClassFileParser;
import com.jasm.parser.types.ClassFile;
import com.jasm.parser.util.formatting.types.ClassFileJsonFormat;
import com.jasm.parser.util.inMemoryCompilation.InMemoryFileManager;
import com.jasm.parser.util.inMemoryCompilation.InvocationOutputStream;
import com.jasm.parser.util.inMemoryCompilation.JavaSourceFromString;
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
import java.security.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("compile")
@CrossOrigin
public class Jasm {

    @Context
    protected UriInfo uriInfo;
    String className = "Main";

    @POST
    @Path("getInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @PermitAll
    @RequestScoped
    public Response getInfo(String content) {

        Result res;
        try {
            res = compileAndExecute(className, content);
        } catch (AttributeDoesNotExistException | IOException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException | InstantiationException | IllegalAccessException | InvalidMnenonicException e) {
            throw new RuntimeException(e);
        }
        return Response.ok(res.toJsonString()).header("Access-Control-Allow-Origin", "*").build();
    }

    public Result compileAndExecute(String className, String source) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, AttributeDoesNotExistException, IOException, InvalidMnenonicException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        InMemoryFileManager manager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));
        List<String> optionList = new ArrayList<>(List.of("-classpath", "insert/src/main/java"));

        List<JavaFileObject> sourceFiles = Collections.singletonList(new JavaSourceFromString(className, source));
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, optionList, null, sourceFiles);

        boolean result = task.call();
        if (!result) {
            String error = diagnostics.getDiagnostics()
                    .stream()
                    .map(el -> "Error at line " + el.getLineNumber() + ": " + el.getColumnNumber() + "\n" + el.getMessage(Locale.US))
                    .reduce("", (acc ,d) -> acc + d + "\n");
            throw new CompilationError(error);
        }
        PipedInputStream in = new PipedInputStream();
        try (final PipedOutputStream out = new PipedOutputStream(in)) {
            manager.getBytesMap().get(className).openOutputStream().writeTo(out);
        } catch (IOException e) {
        }

        ClassFile cf = new ClassFileParser(new BufferedInputStream(in)).parse();
        List<JasmBlock> jasmBlocks = JasmBlocksParser.extractJasmBlocks(source, cf.getMethods().getJasmAnnotationsPerMethod(), cf.getConstant_pool());
        ByteCodeInserter.insertBytecode(jasmBlocks, cf);
        manager.getBytesMap().get(className).openOutputStream().reset();
        manager.getBytesMap().get(className).openOutputStream().write(cf.writeBytes());
        Result res = new Result();
        if (!result) {
            diagnostics.getDiagnostics()
                    .forEach(d -> Logger.getLogger("diagnostics").log(Level.SEVERE, String.valueOf(d)));
        } else {
            ClassLoader classLoader = manager.getClassLoader(null);
            InvocationOutputStream invocationOutputStream = new InvocationOutputStream(System.out);
            PrintStream out = System.out;
            System.setOut(new PrintStream(invocationOutputStream));
            Class<?> clazz = classLoader.loadClass(className);
            try {
             Method m = clazz.getMethod("main", String[].class);
            m.invoke(null, (Object) null);
            } catch (VerifyError e) {
                throw new ExecutionError(e.getMessage());
            }
            String s = invocationOutputStream.getAndClear();
            res.setExecutionResult(new ExecutionResult(s, cf.writeBytes()));
            System.setOut(out);
            out.flush();
            out.close();
            System.setOut(invocationOutputStream.getOriginal());
        }
        in.close();
        res.setClassFile(new ClassFileJsonFormat().classFileToJson(cf));
        return res;
    }
}
