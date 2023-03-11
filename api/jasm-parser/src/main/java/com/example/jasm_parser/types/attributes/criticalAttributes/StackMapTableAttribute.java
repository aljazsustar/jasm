package com.example.jasm_parser.types.attributes.criticalAttributes;

import com.example.jasm_parser.interfaces.AttributeBase;
import com.example.jasm_parser.interfaces.ClassFileElement;

import java.util.List;

public class StackMapTableAttribute extends AttributeBase implements ClassFileElement {


    @Override
    public List<Byte> toHex() {
        return null;
    }
}
