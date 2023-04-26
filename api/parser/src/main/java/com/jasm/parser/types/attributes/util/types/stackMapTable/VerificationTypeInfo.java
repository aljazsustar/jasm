package com.jasm.parser.types.attributes.util.types.stackMapTable;

import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.interfaces.VerificationTypeInfoBase;

import java.util.List;

public class VerificationTypeInfo<T extends VerificationTypeInfoBase> implements ClassFileElement {

    private T verificationType;

    public VerificationTypeInfo(T verificationType) {
        this.verificationType = verificationType;
    }

    public T getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(T verificationType) {
        this.verificationType = verificationType;
    }

    @Override
    public List<Byte> toHex() {
        return this.verificationType.toHex();
    }
}
