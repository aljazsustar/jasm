package com.jasm.parser.types.attributes.util.types.stackMapTable.verificationTypes;

import com.jasm.parser.interfaces.VerificationTypeInfoBase;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class NullVariableInfo extends VerificationTypeInfoBase {

    public NullVariableInfo(Integer tag) {
        this.tag = tag;
    }

    @Override
    public List<Byte> toHex() {
        return new ArrayList<>(WritingUtil.writeBytes(this.tag, 1));
    }
}
