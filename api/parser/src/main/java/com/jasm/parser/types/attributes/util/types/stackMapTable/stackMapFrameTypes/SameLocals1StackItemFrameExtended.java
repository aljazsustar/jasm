package com.jasm.parser.types.attributes.util.types.stackMapTable.stackMapFrameTypes;

import com.jasm.parser.interfaces.StackMapFrameBase;
import com.jasm.parser.interfaces.VerificationTypeInfoBase;
import com.jasm.parser.types.attributes.util.types.stackMapTable.VerificationTypeInfo;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class SameLocals1StackItemFrameExtended extends StackMapFrameBase {


    private VerificationTypeInfo<? extends VerificationTypeInfoBase> stack;
    private Integer offsetDelta;

    public SameLocals1StackItemFrameExtended(Integer frameType, VerificationTypeInfo<? extends VerificationTypeInfoBase> stack, Integer offsetDelta) {
        this.frameType = frameType;
        this.stack = stack;
        this.offsetDelta = offsetDelta;
    }

    public VerificationTypeInfo<? extends VerificationTypeInfoBase> getStack() {
        return stack;
    }

    public void setStack(VerificationTypeInfo<? extends VerificationTypeInfoBase> stack) {
        this.stack = stack;
    }

    public Integer getOffsetDelta() {
        return offsetDelta;
    }

    public void setOffsetDelta(Integer offsetDelta) {
        this.offsetDelta = offsetDelta;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> res = new ArrayList<>();
        res.addAll(WritingUtil.writeBytes(this.frameType, 1));
        res.addAll(WritingUtil.writeBytes(this.offsetDelta, 2));
        res.addAll(this.stack.toHex());
        return res;
    }
}
