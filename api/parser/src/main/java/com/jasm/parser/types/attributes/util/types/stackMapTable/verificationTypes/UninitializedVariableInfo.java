package com.jasm.parser.types.attributes.util.types.stackMapTable.verificationTypes;

import com.jasm.parser.interfaces.VerificationTypeInfoBase;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class UninitializedVariableInfo extends VerificationTypeInfoBase {

    private Integer offset;

    public UninitializedVariableInfo(Integer tag, Integer offset) {
        this.tag = tag;
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> res = new ArrayList<>();
        res.addAll(WritingUtil.writeBytes(this.tag, 1));
        res.addAll(WritingUtil.writeBytes(this.offset, 2));
        return res;
    }
}
