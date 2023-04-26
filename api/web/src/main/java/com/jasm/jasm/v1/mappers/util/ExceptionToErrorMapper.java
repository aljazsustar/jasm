package com.jasm.jasm.v1.mappers.util;

import com.jasm.jasm.v1.models.Error;

public class ExceptionToErrorMapper {

    public static Error mapExceptionToError(Exception e) {
        return new Error("Neznana napaka", e.getMessage());
    }
}
