package com.example.jasm.v1.resources;

import com.example.insert.ByteCodeInserter;
import com.example.insert.JasmBlocksParser;
import com.example.insert.types.JasmBlock;
import com.example.jasm.v1.models.ExecutionResult;
import com.example.jasm.v1.models.Result;
import com.example.parser.exceptions.AttributeDoesNotExistException;
import com.example.parser.parsing.ClassFileParser;
import com.example.parser.types.ClassFile;
import com.example.parser.util.InMemoryFileManager;
import com.example.parser.util.InvocationOutputStream;
import com.example.parser.util.JavaSourceFromString;

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
@RequestScoped
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
        return Response.ok(res.toJsonString()).header("Access-Control-Allow-Origin", "*").build();
    }

    public Result compileAndExecute(String className, String source) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, AttributeDoesNotExistException, IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        InMemoryFileManager manager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));
        List<String> optionList = new ArrayList<>(List.of("-classpath", "insert/src/main/java"));

        List<JavaFileObject> sourceFiles = Collections.singletonList(new JavaSourceFromString(className, source));
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, optionList, null, sourceFiles);

        boolean result = task.call();
        if (!result) {
            diagnostics.getDiagnostics()
                    .forEach(d -> Logger.getLogger("diagnostics").log(Level.SEVERE, String.valueOf(d)));
        }
        PipedInputStream in = new PipedInputStream();
        try (final PipedOutputStream out = new PipedOutputStream(in)) {
            manager.getBytesMap().get(className).openOutputStream().writeTo(out);
        } catch (IOException e) {
        }

        ClassFile cf = new ClassFileParser(new BufferedInputStream(in)).parse();
        List<JasmBlock> jasmBlocks = JasmBlocksParser.extractJasmBlocks(source, cf.getMethods().getJasmAnnotationsPerMethod());
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
            Method m = clazz.getMethod("main", String[].class);
            m.invoke(null, (Object) null);
            String s = invocationOutputStream.getAndClear();
            res.setExecutionResult(new ExecutionResult(s));
            System.setOut(out);
            out.flush();
            out.close();
            System.setOut(invocationOutputStream.getOriginal());
        }
        in.close();
        res.setClassFile(cf);
        return res;
    }
}
