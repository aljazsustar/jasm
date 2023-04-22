package com.example.parser.util.types;

import com.example.parser.types.constantPool.constants.methods.MethodRefConstant;
import com.example.parser.types.methods.MethodInfo;

import java.util.List;

public class Methods {
    private List<MethodInfo> localMethods;
    private List<MethodRefConstant> methodRefs;

    public Methods(List<MethodInfo> localMethods, List<MethodRefConstant> methodRefs) {
        this.localMethods = localMethods;
        this.methodRefs = methodRefs;
    }

    public List<MethodInfo> getLocalMethods() {
        return localMethods;
    }

    public void setLocalMethods(List<MethodInfo> localMethods) {
        this.localMethods = localMethods;
    }

    public List<MethodRefConstant> getMethodRefs() {
        return methodRefs;
    }

    public void setMethodRefs(List<MethodRefConstant> methodRefs) {
        this.methodRefs = methodRefs;
    }
}
