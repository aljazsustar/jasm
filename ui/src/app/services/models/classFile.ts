export interface ConstantPoolElementJsonFormat {
  constantPoolIndex: number;
  type: string;
  value: string;
  name: string;
}

export interface ClassFileJsonFormat {
  constantPool: ConstantPoolElementJsonFormat[];
}

export interface ExecutionResult{
  stdOut: string;
  compiledClassFile: number[];
}

export interface Result {
  classFile?: ClassFileJsonFormat;
  executionResult: ExecutionResult;
}
