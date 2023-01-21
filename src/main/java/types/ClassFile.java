package types;

import enums.AccessFlags;
import types.attributes.Attributes;
import types.constantPool.ConstantPool;
import types.constantPool.constants.ClassConstant;
import types.fields.Fields;
import types.interfaces.Interfaces;
import types.methods.Methods;

public class ClassFile {
    private Integer magic;
    private Integer minor_version;
    private Integer major_version;
    private Integer constant_pool_count;
    private ConstantPool constant_pool;
    private AccessFlags access_flags;
    private ClassConstant this_class;
    private ClassConstant super_class;
    private Integer interfaces_count;
    private Interfaces interfaces;
    private Integer fields_count;
    private Fields fields;
    private Integer methods_count;
    private Methods methods;
    private Integer attributes_count;
    private Attributes attributes;
}
