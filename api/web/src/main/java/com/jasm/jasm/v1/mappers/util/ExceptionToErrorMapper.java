package com.jasm.jasm.v1.mappers.util;

import com.jasm.jasm.v1.models.Error;
import com.jasm.parser.exceptions.AttributeDoesNotExistException;
import com.jasm.parser.exceptions.CompilationError;
import com.jasm.parser.exceptions.InvalidMnenonicException;

public class ExceptionToErrorMapper {

    public static Error mapExceptionToError(CompilationError e) {
        return new Error("Napaka pri prevajanju izvorne kode", e.getMessage());
    }

    public static Error mapExceptionToError(InvalidMnenonicException e) {
        return new Error("Mnemonik ne obstaja", e.getMessage());
    }

    public static Error mapExceptionToError(AttributeDoesNotExistException e) {
        return new Error("Atribut ne obstaja", e.getMessage());
    }

    public static Error mapExceptionToError(Exception e) {
        return new Error("Neznana napaka", e.getMessage());
    }
}
