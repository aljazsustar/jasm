package com.jasm.jasm.v1.mappers;

import com.jasm.jasm.v1.mappers.util.ExceptionToErrorMapper;
import com.jasm.jasm.v1.models.Error;
import com.jasm.jasm.v1.models.Result;
import com.jasm.parser.exceptions.ExecutionError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExecutionErrorMapper implements ExceptionMapper<ExecutionError> {

    @Override
    public Response toResponse(ExecutionError exception) {
        System.out.println(exception.getMessage());
        Error error = ExceptionToErrorMapper.mapExceptionToError(exception);
        Result res = new Result();
        res.setError(error);
        return Response.ok(res).header("Access-Control-Allow-Origin", "*").build();
    }
}
