package com.example.parser.types.attributes.util.types.stackMapTable.stackMapFrameTypes;

import com.example.parser.interfaces.StackMapFrameBase;
import com.example.parser.interfaces.VerificationTypeInfoBase;
import com.example.parser.types.attributes.util.types.stackMapTable.VerificationTypeInfo;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class SameLocals1StackItemFrame extends StackMapFrameBase {

    private VerificationTypeInfo<? extends VerificationTypeInfoBase> stack;

    public SameLocals1StackItemFrame(VerificationTypeInfo<? extends VerificationTypeInfoBase> stack, Integer frameType) {
        this.frameType = frameType;
        this.stack = stack;
    }

    public VerificationTypeInfo<? extends VerificationTypeInfoBase> getStack() {
        return stack;
    }

    public void setStack(VerificationTypeInfo<? extends VerificationTypeInfoBase> stack) {
        this.stack = stack;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> res = new ArrayList<>();
        res.addAll(WritingUtil.writeBytes(this.frameType, 1));
        res.addAll(this.stack.toHex());
        return res;
    }
}
