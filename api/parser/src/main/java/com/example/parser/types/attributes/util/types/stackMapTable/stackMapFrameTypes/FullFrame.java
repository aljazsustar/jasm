package com.example.parser.types.attributes.util.types.stackMapTable.stackMapFrameTypes;

import com.example.parser.interfaces.StackMapFrameBase;
import com.example.parser.interfaces.VerificationTypeInfoBase;
import com.example.parser.types.attributes.util.types.stackMapTable.VerificationTypeInfo;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class FullFrame extends StackMapFrameBase {
    private Integer offsetDelta;
    private Integer numberOfLocals;
    private List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> locals;
    private Integer numberOfStackItems;
    private List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> stack;

    public FullFrame(Integer frameType, Integer offsetDelta, Integer numberOfLocals,
                     List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> locals,
                     Integer numberOfStackItems, List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> stack) {
        this.frameType = frameType;
        this.offsetDelta = offsetDelta;
        this.numberOfLocals = numberOfLocals;
        this.locals = locals;
        this.numberOfStackItems = numberOfStackItems;
        this.stack = stack;
    }

    public Integer getOffsetDelta() {
        return offsetDelta;
    }

    public void setOffsetDelta(Integer offsetDelta) {
        this.offsetDelta = offsetDelta;
    }

    public Integer getNumberOfLocals() {
        return numberOfLocals;
    }

    public void setNumberOfLocals(Integer numberOfLocals) {
        this.numberOfLocals = numberOfLocals;
    }

    public List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> getLocals() {
        return locals;
    }

    public void setLocals(List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> locals) {
        this.locals = locals;
    }

    public Integer getNumberOfStackItems() {
        return numberOfStackItems;
    }

    public void setNumberOfStackItems(Integer numberOfStackItems) {
        this.numberOfStackItems = numberOfStackItems;
    }

    public List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> getStack() {
        return stack;
    }

    public void setStack(List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> stack) {
        this.stack = stack;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> res = new ArrayList<>();
        res.addAll(WritingUtil.writeBytes(this.frameType, 1));
        res.addAll(WritingUtil.writeBytes(this.offsetDelta, 2));
        res.addAll(WritingUtil.writeBytes(this.numberOfLocals, 2));
        for (VerificationTypeInfo<? extends VerificationTypeInfoBase> local : this.locals) {
            res.addAll(local.toHex());
        }
        res.addAll(WritingUtil.writeBytes(this.numberOfStackItems, 2));
        for (VerificationTypeInfo<? extends VerificationTypeInfoBase> verificationTypeInfo : this.stack) {
            res.addAll(verificationTypeInfo.toHex());
        }
        return res;
    }
}
