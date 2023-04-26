package com.jasm.parser.types.attributes.util.types.stackMapTable;

import com.jasm.parser.interfaces.ClassFileElement;
import com.jasm.parser.interfaces.StackMapFrameBase;

import java.util.List;

public class StackMapFrame<T extends StackMapFrameBase> implements ClassFileElement {

    private T stackMapFrameType;

    public StackMapFrame(T stackMapFrameType) {
        this.stackMapFrameType = stackMapFrameType;
    }

    public T getStackMapFrameType() {
        return stackMapFrameType;
    }

    public void setStackMapFrameType(T stackMapFrameType) {
        this.stackMapFrameType = stackMapFrameType;
    }

    @Override
    public List<Byte> toHex() {
        return this.stackMapFrameType.toHex();
    }
}
