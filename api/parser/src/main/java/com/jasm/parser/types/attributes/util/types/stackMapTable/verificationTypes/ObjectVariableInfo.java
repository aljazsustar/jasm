package com.jasm.parser.types.attributes.util.types.stackMapTable.verificationTypes;

import com.jasm.parser.interfaces.VerificationTypeInfoBase;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class ObjectVariableInfo extends VerificationTypeInfoBase {

    private Integer constantPoolIndex;

    public ObjectVariableInfo(Integer tag, Integer constantPoolIndex) {
        this.tag = tag;
        this.constantPoolIndex = constantPoolIndex;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> res = new ArrayList<>();
        res.addAll(WritingUtil.writeBytes(this.tag, 1));
        res.addAll(WritingUtil.writeBytes(this.constantPoolIndex, 2));
        return res;
    }
}
