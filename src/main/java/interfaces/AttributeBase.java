package interfaces;

import types.constantPool.constants.strings.Utf8Constant;
import types.interfaces.Interfaces;

import java.util.List;

public abstract class AttributeBase implements ClassFileElement {
    protected Utf8Constant attributeName;
    protected Integer attributeLength;
}
