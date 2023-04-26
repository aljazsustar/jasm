package com.jasm.parser.types;

import com.jasm.parser.types.attributes.Attributes;
import com.jasm.parser.types.constantPool.ConstantPool;
import com.jasm.parser.types.constantPool.constants.ClassConstant;
import com.jasm.parser.types.constantPool.constants.methods.MethodRefConstant;
import com.jasm.parser.types.fields.Fields;
import com.jasm.parser.types.interfaces.Interfaces;
import com.jasm.parser.types.methods.MethodInfo;
import com.jasm.parser.types.methods.Methods;
import com.jasm.parser.util.WritingUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ClassFile {
    private Long magic;
    private Integer minor_version;
    private Integer major_version;
    private Integer constant_pool_count;
    private ConstantPool constant_pool;
    private Integer access_flags;
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

    public Long getMagic() {
        return magic;
    }

    public void setMagic(Long magic) {
        this.magic = magic;
    }

    public Integer getMinor_version() {
        return minor_version;
    }

    public void setMinor_version(Integer minor_version) {
        this.minor_version = minor_version;
    }

    public Integer getMajor_version() {
        return major_version;
    }

    public void setMajor_version(Integer major_version) {
        this.major_version = major_version;
    }

    public Integer getConstant_pool_count() {
        return constant_pool_count;
    }

    public void setConstant_pool_count(Integer constant_pool_count) {
        this.constant_pool_count = constant_pool_count;
    }

    public ConstantPool getConstant_pool() {
        return constant_pool;
    }

    public void setConstant_pool(ConstantPool constant_pool) {
        this.constant_pool = constant_pool;
    }

    public Integer getAccess_flags() {
        return access_flags;
    }

    public void setAccess_flags(Integer access_flags) {
        this.access_flags = access_flags;
    }

    public ClassConstant getThis_class() {
        return this_class;
    }

    public void setThis_class(ClassConstant this_class) {
        this.this_class = this_class;
    }

    public ClassConstant getSuper_class() {
        return super_class;
    }

    public void setSuper_class(ClassConstant super_class) {
        this.super_class = super_class;
    }

    public Integer getInterfaces_count() {
        return interfaces_count;
    }

    public void setInterfaces_count(Integer interfaces_count) {
        this.interfaces_count = interfaces_count;
    }

    public Interfaces getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Interfaces interfaces) {
        this.interfaces = interfaces;
    }

    public Integer getFields_count() {
        return fields_count;
    }

    public void setFields_count(Integer fields_count) {
        this.fields_count = fields_count;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Integer getMethods_count() {
        return methods_count;
    }

    public void setMethods_count(Integer methods_count) {
        this.methods_count = methods_count;
    }

    public Methods getMethods() {
        return methods;
    }

    public void setMethods(Methods methods) {
        this.methods = methods;
    }

    public Integer getAttributes_count() {
        return attributes_count;
    }

    public void setAttributes_count(Integer attributes_count) {
        this.attributes_count = attributes_count;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public com.jasm.parser.util.types.Methods getAllMethods() {
        List<MethodInfo> localMethods = this.methods.getMethods();
        List<MethodRefConstant> methodRefs = this.constant_pool.getMethods();
        return new com.jasm.parser.util.types.Methods(localMethods, methodRefs);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public byte[] writeBytes() {
        // we don't immediately know, how long the input file is, so we initially construct an arraylist and then convert
        // it to primitive array
        ArrayList<Byte> tmp = new ArrayList<>();
        tmp.addAll(WritingUtil.writeBytes(this.magic, 4));
        tmp.addAll(WritingUtil.writeBytes(this.minor_version, 2));
        tmp.addAll(WritingUtil.writeBytes(this.major_version, 2));
        tmp.addAll(WritingUtil.writeBytes(this.constant_pool_count, 2));
        tmp.addAll(this.constant_pool.toHex());
        tmp.addAll(WritingUtil.writeBytes(this.access_flags, 2));
        tmp.addAll(WritingUtil.writeBytes(this.this_class.getConstantPoolIndex() + 1, 2));
        tmp.addAll(WritingUtil.writeBytes(this.super_class.getConstantPoolIndex() + 1, 2));
        tmp.addAll(WritingUtil.writeBytes(this.interfaces_count, 2));
        tmp.addAll(this.interfaces.toHex());
        tmp.addAll(WritingUtil.writeBytes(this.fields_count, 2));
        tmp.addAll(this.fields.toHex());
        tmp.addAll(WritingUtil.writeBytes(this.methods_count, 2));
        tmp.addAll(this.methods.toHex());
        tmp.addAll(WritingUtil.writeBytes(this.attributes_count, 2));
        tmp.addAll(this.attributes.toHex());
        return WritingUtil.objectByteListToPrimitiveArray(tmp);
    }
}
