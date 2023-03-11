package com.example.jasm_parser.interfaces;

import com.example.jasm_parser.types.constantPool.constants.strings.Utf8Constant;


public abstract class AttributeBase implements ClassFileElement {
    protected Utf8Constant attributeName;
    protected Long attributeLength;
}
