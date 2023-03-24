package com.example.jasm.v1.resources;

import com.example.insert.ByteCodeInserter;
import com.example.insert.JasmBlocksParser;
import com.example.insert.types.JasmBlock;
import com.example.parser.exceptions.AttributeDoesNotExistException;
import com.example.parser.parsing.ClassFileParser;
import com.example.parser.types.ClassFile;
import com.example.parser.util.ParsingUtil;
import com.kumuluz.ee.cors.annotations.CrossOrigin;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
            "    @Block(start=11, end=17)\n" +
            "  })\n" +
            "  public static void main(String[] args) {\n" +
            "    /*\n" +
            "     getstatic 7\n" +
            "     bipush 8\n" +
            "     iconst_5\n" +
            "     invokestatic 13\n" +
            "     invokevirtual 19\n" +
            "    */\n" +
            "  }\n" +
            "\n" +
            "  public static int sum(int a, int b) {\n" +
            "       return a+b;\n" +
            "  }\n" +
            "}";

    @Context
    protected UriInfo uriInfo;

    @POST
    @Path("getInfo")
    @PermitAll
    @CrossOrigin
    public Response getInfo(String content) {

        ClassFile cf = null;
        try {
            cf = new ClassFileParser("MinExample.class").parse();
            List<JasmBlock> jasmBlocks = JasmBlocksParser.extractJasmBlocks(source, cf.getMethods().getJasmAnnotationsPerMethod());
            ByteCodeInserter.insertBytecode(jasmBlocks, cf);
            byte[] bytes = cf.writeBytes();
            ParsingUtil.printBytes(bytes);
            File out = new File("MinExample.class");
            FileOutputStream os = new FileOutputStream(out);
            os.write(bytes);
        } catch (AttributeDoesNotExistException e) {
            return Response.serverError().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Response.ok(cf.toString()).header("Access-Control-Allow-Origin", "*").build();
    }
}
