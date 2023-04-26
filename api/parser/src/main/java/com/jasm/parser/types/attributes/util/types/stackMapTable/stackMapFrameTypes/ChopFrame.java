package com.jasm.parser.types.attributes.util.types.stackMapTable.stackMapFrameTypes;

import com.jasm.parser.interfaces.StackMapFrameBase;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class ChopFrame extends StackMapFrameBase {
    private Integer offsetDelta;

    public ChopFrame(Integer frameType, Integer offsetDelta) {
        this.frameType = frameType;
        this.offsetDelta = offsetDelta;
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
        return res;
    }
}
