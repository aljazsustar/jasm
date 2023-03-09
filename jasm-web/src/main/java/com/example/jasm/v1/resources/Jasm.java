package com.example.jasm.v1.resources;

import exceptions.AttributeDoesNotExistException;
import parsing.ClassFileParser;
import types.ClassFile;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class Jasm {

    @GET
    @PermitAll
    public Response test() {
        ClassFile cf = null;
        try {
            cf = new ClassFileParser("Test1.class").parse();
        } catch (AttributeDoesNotExistException e) {
            return Response.serverError().build();
        }
        return Response.ok(cf.toString()).build();
    }
}
