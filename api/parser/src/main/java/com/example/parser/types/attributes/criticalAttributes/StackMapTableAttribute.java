package com.example.parser.types.attributes.criticalAttributes;

import com.example.parser.interfaces.AttributeBase;
import com.example.parser.interfaces.ClassFileElement;
import com.example.parser.interfaces.StackMapFrameBase;
import com.example.parser.types.attributes.util.types.stackMapTable.StackMapFrame;
import com.example.parser.types.constantPool.constants.strings.Utf8Constant;

import java.util.ArrayList;
import java.util.List;

public class StackMapTableAttribute extends AttributeBase implements ClassFileElement {

    private List<StackMapFrame<? extends StackMapFrameBase>> entries;

    public StackMapTableAttribute(Long attributeLength, Integer attributeNameIndex, Utf8Constant attributeName, List<StackMapFrame<? extends StackMapFrameBase>> entries) {
        this.attributeLength = attributeLength;
        this.attributeNameIndex = attributeNameIndex;
        this.attributeName = attributeName;
        this.entries = entries;
    }

    public List<StackMapFrame<? extends StackMapFrameBase>> getEntries() {
        return entries;
    }

    public void setEntries(List<StackMapFrame<? extends StackMapFrameBase>> entries) {
        this.entries = entries;
    }

    @Override
    public List<Byte> toHex() {
        return this.entries
                .stream()
                .map(StackMapFrame::toHex)
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }
}
