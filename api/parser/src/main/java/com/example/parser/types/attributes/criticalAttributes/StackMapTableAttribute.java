package com.example.parser.types.attributes.criticalAttributes;

import com.example.parser.interfaces.AttributeBase;
import com.example.parser.interfaces.ClassFileElement;

import java.util.List;

public class StackMapTableAttribute extends AttributeBase implements ClassFileElement {


    @Override
    public List<Byte> toHex() {
        return null;
    }
}
