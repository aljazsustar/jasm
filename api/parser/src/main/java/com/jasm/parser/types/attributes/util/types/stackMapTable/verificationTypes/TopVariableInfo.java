package com.jasm.parser.types.attributes.util.types.stackMapTable.verificationTypes;

import com.jasm.parser.interfaces.VerificationTypeInfoBase;

import java.util.List;

public class TopVariableInfo extends VerificationTypeInfoBase {

    public TopVariableInfo(Integer tag) {
        this.tag = tag;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
