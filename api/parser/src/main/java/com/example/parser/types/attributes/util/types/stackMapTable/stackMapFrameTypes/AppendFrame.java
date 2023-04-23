package com.example.parser.types.attributes.util.types.stackMapTable.stackMapFrameTypes;

import com.example.parser.interfaces.StackMapFrameBase;
import com.example.parser.interfaces.VerificationTypeInfoBase;
import com.example.parser.types.attributes.util.types.stackMapTable.VerificationTypeInfo;
import com.example.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class AppendFrame extends StackMapFrameBase {

    private Integer offsetDelta;
    private List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> locals;

    public AppendFrame(Integer frameType, Integer offsetDelta, List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> locals) {
        this.frameType = frameType;
        this.offsetDelta = offsetDelta;
        this.locals = locals;
    }

    public Integer getOffsetDelta() {
        return offsetDelta;
    }

    public void setOffsetDelta(Integer offsetDelta) {
        this.offsetDelta = offsetDelta;
    }

    public List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> getLocals() {
        return locals;
    }

    public void setLocals(List<VerificationTypeInfo<? extends VerificationTypeInfoBase>> locals) {
        this.locals = locals;
    }

    @Override
    public List<Byte> toHex() {
        List<Byte> res = new ArrayList<>();
        res.addAll(WritingUtil.writeBytes(this.frameType, 1));
        res.addAll(WritingUtil.writeBytes(this.offsetDelta, 2));
        for (VerificationTypeInfo<? extends VerificationTypeInfoBase> local : this.locals) {
            res.addAll(local.toHex());
        }
        return res;
    }
}
