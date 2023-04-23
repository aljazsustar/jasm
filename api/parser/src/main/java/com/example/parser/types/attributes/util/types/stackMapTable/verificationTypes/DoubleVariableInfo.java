package com.example.parser.types.attributes.util.types.stackMapTable.verificationTypes;

import com.example.parser.interfaces.VerificationTypeInfoBase;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class DoubleVariableInfo extends VerificationTypeInfoBase {

    public DoubleVariableInfo(Integer tag) {
        this.tag = tag;
    }

    @Override
    public List<Byte> toHex() {
        return new ArrayList<>(WritingUtil.writeBytes(this.tag, 1));
    }
}
