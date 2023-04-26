package com.jasm.parser.types.attributes.util.types.stackMapTable.stackMapFrameTypes;

import com.jasm.parser.interfaces.StackMapFrameBase;
import com.jasm.parser.util.WritingUtil;

import java.util.ArrayList;
import java.util.List;

public class SameFrame extends StackMapFrameBase {

    public SameFrame(Integer frameType) {
        this.frameType = frameType;
    }

    @Override
    public List<Byte> toHex() {
        return new ArrayList<>(WritingUtil.writeBytes(this.frameType, 1));
    }
}
