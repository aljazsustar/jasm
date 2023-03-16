package com.example.parser.interfaces;

import com.example.parser.types.constantPool.constants.strings.Utf8Constant;


public abstract class AttributeBase implements ClassFileElement {
    protected Utf8Constant attributeName;
    protected Long attributeLength;
}
