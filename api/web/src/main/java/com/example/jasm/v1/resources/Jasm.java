package com.example.jasm.v1.resources;

import com.example.insert.JasmBlocksParser;
import com.example.parser.exceptions.AttributeDoesNotExistException;
import com.example.parser.parsing.ClassFileParser;
import com.example.parser.types.ClassFile;
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

@Path("compile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
@RequestScoped
@CrossOrigin
public class Jasm {

    String source = "import com.example.insert.annotations.Block;\n" +
            "   import com.example.insert.annotations.Jasm;\n" +
            "  \n" +
            "   public class Test {\n" +
            "      public Test() {\n" +
            "      }\n" +
            "     \n" +
            "      public static void main(String[] var0) {\n" +
            "      }\n" +
            "     \n" +
            "      @Jasm({@Block(\n" +
            "      start = 16,\n" +
            "      end = 20\n" +
            "   )})\n" +
            "      public static void f() {" +
            "      /*\n" +
            "       bipush 4\n" +
            "       bipush 2\n" +
            "       iadd\n" +
            "       ireturn\n" +
            "       iadd\n " +
            "}" +
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
            cf = new ClassFileParser("Test.class").parse();
            JasmBlocksParser.extractJasmBlocks(source, cf.getMethods().getJasmAnnotationsPerMethod());
        } catch (AttributeDoesNotExistException e) {
            return Response.serverError().build();
        }
        return Response.ok(cf.toString()).header("Access-Control-Allow-Origin", "*").build();
    }
}
