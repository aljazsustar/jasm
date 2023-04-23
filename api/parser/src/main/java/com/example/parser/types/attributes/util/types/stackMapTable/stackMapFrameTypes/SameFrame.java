package com.example.parser.types.attributes.util.types.stackMapTable.stackMapFrameTypes;

import com.example.parser.interfaces.StackMapFrameBase;
import com.example.parser.util.WritingUtil;

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
