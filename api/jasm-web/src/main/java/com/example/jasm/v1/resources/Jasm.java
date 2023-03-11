package com.example.jasm.v1.resources;

import com.example.jasm_parser.exceptions.AttributeDoesNotExistException;
import com.example.jasm_parser.parsing.ClassFileParser;
import com.example.jasm_parser.types.ClassFile;
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

    @Context
    protected UriInfo uriInfo;

    @POST
    @Path("getInfo")
    @PermitAll
    @CrossOrigin
    public Response getInfo(String content) {

        ClassFile cf = null;
        try {
            cf = new ClassFileParser("Test1.class").parse();
        } catch (AttributeDoesNotExistException e) {
            return Response.serverError().build();
        }
        return Response.ok(cf.toString()).header("Access-Control-Allow-Origin", "*").build();
    }
}
