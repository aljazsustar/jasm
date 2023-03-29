export interface AccessFlags{
  accessFlags: Map<string, number>;
  intValue: number;
}


export interface Fields{
  fields: FieldInfo[];
}

export interface ConstantPool{
  elements: ConstantPoolElement[];
}

export interface ExecutionResult{
  stdOut: string;
  compiledClassFile: number[];
}

export interface Interfaces{
  interfaces: ClassConstant[];
}

export interface ClassFile{
  magic: number;
  minor_version: number;
  major_version: number;
  constant_pool_count: number;
  constant_pool: ConstantPool;
  access_flags: number;
  this_class: ClassConstant;
  super_class: ClassConstant;
  interfaces_count: number;
  interfaces: Interfaces;
  fields_count: number;
  fields: Fields;
  methods_count: number;
  methods: Methods;
  attributes_count: number;
  attributes: Attributes;
}

export interface ConstantPoolElement{
  tag: number;
  constantPoolIndex: number;
}

export interface ClassConstant{
}

export interface Result{
  classFile: ClassFile;
  executionResult: ExecutionResult;
}

export interface Utf8Constant{
}


export interface AttributeBase{
  attributeName: Utf8Constant;
  attributeNameIndex: number;
  attributeLength: number;
}

export interface Methods{
  methods: MethodInfo[];
}

export interface MethodInfo{
  accessFlags: AccessFlags;
  nameIndex: number;
  descriptorIndex: number;
  attributesCount: number;
  attributes: Attributes;
}

export interface FieldInfo{
  accessFlags: AccessFlags;
  nameIndex: number;
  descriptorIndex: number;
  attributesCount: number;
  attributes: Attributes;
}


export interface Attributes{
  attributes: AttributeBase[];
}

export interface Utf8Constant{
  value: string;
  tag: number;
  constantPoolIndex: number;
}

export interface AttributeBase{
  attributeName: Utf8Constant;
  attributeNameIndex: number;
  attributeLength: number;
}



