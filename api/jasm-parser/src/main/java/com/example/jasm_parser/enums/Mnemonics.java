package com.example.jasm_parser.enums;

import com.example.jasm_parser.util.types.Pair;

import java.util.HashMap;
import java.util.Map;

public class Mnemonics {

    /**
     * {@link #mnemonicsByString} serves as a list of mnemonics, mapped by their name. The value is a {@linkplain Pair}
     * of {@linkplain Integer} representing the mnemonic's opcode and another {@linkplain Integer}, representing the
     * number of mnemonic's arguments
     */
    private final Map<String, Pair<Integer, Integer>> mnemonicsByString;
    /**
     * For the purpose of convenience, mnemonics are also mapped by their opcode. In this case, the keys are of type
     * {@linkplain Integer}, which represent the opcodes. The values are of type {@linkplain Pair},
     * where the first element represents the mnemonic's name and the second value represents the number of parameters.
     */
    private final Map<Integer, Pair<String, Integer>> mnemonicsByOpcode;

    public Mnemonics() {
        this.mnemonicsByString = new HashMap<>();
        this.mnemonicsByOpcode = new HashMap<>();
        this.fillMap();
    }

    private void fillMap() {
        this.mnemonicsByString.put("nop", new Pair<>(0, 0));
        this.mnemonicsByString.put("aconst_null", new Pair<>(1, 0));
        this.mnemonicsByString.put("iconst_m1", new Pair<>(2, 0));
        this.mnemonicsByString.put("iconst_0", new Pair<>(3, 0));
        this.mnemonicsByString.put("iconst_1", new Pair<>(4, 0));
        this.mnemonicsByString.put("iconst_2", new Pair<>(5, 0));
        this.mnemonicsByString.put("iconst_3", new Pair<>(6, 0));
        this.mnemonicsByString.put("iconst_4", new Pair<>(7, 0));
        this.mnemonicsByString.put("iconst_5", new Pair<>(8, 0));
        this.mnemonicsByString.put("lconst_0", new Pair<>(9, 0));
        this.mnemonicsByString.put("lconst_1", new Pair<>(10, 0));
        this.mnemonicsByString.put("fconst_0", new Pair<>(11, 0));
        this.mnemonicsByString.put("fconst_1", new Pair<>(12, 0));
        this.mnemonicsByString.put("fconst_2", new Pair<>(13, 0));
        this.mnemonicsByString.put("dconst_0", new Pair<>(14, 0));
        this.mnemonicsByString.put("dconst_1", new Pair<>(15, 0));
        this.mnemonicsByString.put("bipush", new Pair<>(16, 1));
        this.mnemonicsByString.put("sipush", new Pair<>(17, 2));
        this.mnemonicsByString.put("ldc", new Pair<>(18, 1));
        this.mnemonicsByString.put("ldc_w", new Pair<>(19, 2));
        this.mnemonicsByString.put("ldc2_w", new Pair<>(20, 2));
        this.mnemonicsByString.put("iload", new Pair<>(21, 1));
        this.mnemonicsByString.put("lload", new Pair<>(22, 1));
        this.mnemonicsByString.put("fload", new Pair<>(23, 1));
        this.mnemonicsByString.put("dload", new Pair<>(24, 1));
        this.mnemonicsByString.put("aload", new Pair<>(25, 1));
        this.mnemonicsByString.put("iload_0", new Pair<>(26, 0));
        this.mnemonicsByString.put("iload_1", new Pair<>(27, 0));
        this.mnemonicsByString.put("iload_2", new Pair<>(28, 0));
        this.mnemonicsByString.put("iload_3", new Pair<>(29, 0));
        this.mnemonicsByString.put("lload_0", new Pair<>(30, 0));
        this.mnemonicsByString.put("lload_1", new Pair<>(31, 0));
        this.mnemonicsByString.put("lload_2", new Pair<>(32, 0));
        this.mnemonicsByString.put("lload_3", new Pair<>(33, 0));
        this.mnemonicsByString.put("fload_0", new Pair<>(34, 0));
        this.mnemonicsByString.put("fload_1", new Pair<>(35, 0));
        this.mnemonicsByString.put("fload_2", new Pair<>(36, 0));
        this.mnemonicsByString.put("fload_3", new Pair<>(37, 0));
        this.mnemonicsByString.put("dload_0", new Pair<>(38, 0));
        this.mnemonicsByString.put("dload_1", new Pair<>(39, 0));
        this.mnemonicsByString.put("dload_2", new Pair<>(40, 0));
        this.mnemonicsByString.put("dload_3", new Pair<>(41, 0));
        this.mnemonicsByString.put("aload_0", new Pair<>(42, 0));
        this.mnemonicsByString.put("aload_1", new Pair<>(43, 0));
        this.mnemonicsByString.put("aload_2", new Pair<>(44, 0));
        this.mnemonicsByString.put("aload_3", new Pair<>(45, 0));
        this.mnemonicsByString.put("iaload", new Pair<>(46, 0));
        this.mnemonicsByString.put("laload", new Pair<>(47, 0));
        this.mnemonicsByString.put("faload", new Pair<>(48, 0));
        this.mnemonicsByString.put("daload", new Pair<>(49, 0));
        this.mnemonicsByString.put("aaload", new Pair<>(50, 0));
        this.mnemonicsByString.put("baload", new Pair<>(51, 0));
        this.mnemonicsByString.put("caload", new Pair<>(52, 0));
        this.mnemonicsByString.put("saload", new Pair<>(53, 0));
        this.mnemonicsByString.put("istore", new Pair<>(54, 1));
        this.mnemonicsByString.put("lstore", new Pair<>(55, 1));
        this.mnemonicsByString.put("fstore", new Pair<>(56, 1));
        this.mnemonicsByString.put("dstore", new Pair<>(57, 1));
        this.mnemonicsByString.put("astore", new Pair<>(58, 1));
        this.mnemonicsByString.put("istore_0", new Pair<>(59, 0));
        this.mnemonicsByString.put("istore_1", new Pair<>(60, 0));
        this.mnemonicsByString.put("istore_2", new Pair<>(61, 0));
        this.mnemonicsByString.put("istore_3", new Pair<>(62, 0));
        this.mnemonicsByString.put("lstore_0", new Pair<>(63, 0));
        this.mnemonicsByString.put("lstore_1", new Pair<>(64, 0));
        this.mnemonicsByString.put("lstore_2", new Pair<>(65, 0));
        this.mnemonicsByString.put("lstore_3", new Pair<>(66, 0));
        this.mnemonicsByString.put("fstore_0", new Pair<>(67, 0));
        this.mnemonicsByString.put("fstore_1", new Pair<>(68, 0));
        this.mnemonicsByString.put("fstore_2", new Pair<>(69, 0));
        this.mnemonicsByString.put("fstore_3", new Pair<>(70, 0));
        this.mnemonicsByString.put("dstore_0", new Pair<>(71, 0));
        this.mnemonicsByString.put("dstore_1", new Pair<>(72, 0));
        this.mnemonicsByString.put("dstore_2", new Pair<>(73, 0));
        this.mnemonicsByString.put("dstore_3", new Pair<>(74, 0));
        this.mnemonicsByString.put("astore_0", new Pair<>(75, 0));
        this.mnemonicsByString.put("astore_1", new Pair<>(76, 0));
        this.mnemonicsByString.put("astore_2", new Pair<>(77, 0));
        this.mnemonicsByString.put("astore_3", new Pair<>(78, 0));
        this.mnemonicsByString.put("iastore", new Pair<>(79, 0));
        this.mnemonicsByString.put("lastore", new Pair<>(80, 0));
        this.mnemonicsByString.put("fastore", new Pair<>(81, 0));
        this.mnemonicsByString.put("dastore", new Pair<>(82, 0));
        this.mnemonicsByString.put("aastore", new Pair<>(83, 0));
        this.mnemonicsByString.put("bastore", new Pair<>(84, 0));
        this.mnemonicsByString.put("castore", new Pair<>(85, 0));
        this.mnemonicsByString.put("sastore", new Pair<>(86, 0));
        this.mnemonicsByString.put("pop", new Pair<>(87, 0));
        this.mnemonicsByString.put("pop2", new Pair<>(88, 0));
        this.mnemonicsByString.put("dup", new Pair<>(89, 0));
        this.mnemonicsByString.put("dup_x1", new Pair<>(90, 0));
        this.mnemonicsByString.put("dup_x2", new Pair<>(91, 0));
        this.mnemonicsByString.put("dup2", new Pair<>(92, 0));
        this.mnemonicsByString.put("dup2_x1", new Pair<>(93, 0));
        this.mnemonicsByString.put("dup2_x2", new Pair<>(94, 0));
        this.mnemonicsByString.put("swap", new Pair<>(95, 0));
        this.mnemonicsByString.put("iadd", new Pair<>(96, 0));
        this.mnemonicsByString.put("ladd", new Pair<>(97, 0));
        this.mnemonicsByString.put("fadd", new Pair<>(98, 0));
        this.mnemonicsByString.put("dadd", new Pair<>(99, 0));
        this.mnemonicsByString.put("isub", new Pair<>(100, 0));
        this.mnemonicsByString.put("lsub", new Pair<>(101, 0));
        this.mnemonicsByString.put("fsub", new Pair<>(102, 0));
        this.mnemonicsByString.put("dsub", new Pair<>(103, 0));
        this.mnemonicsByString.put("imul", new Pair<>(104, 0));
        this.mnemonicsByString.put("lmul", new Pair<>(105, 0));
        this.mnemonicsByString.put("fmul", new Pair<>(106, 0));
        this.mnemonicsByString.put("dmul", new Pair<>(107, 0));
        this.mnemonicsByString.put("idiv", new Pair<>(108, 0));
        this.mnemonicsByString.put("ldiv", new Pair<>(109, 0));
        this.mnemonicsByString.put("fdiv", new Pair<>(110, 0));
        this.mnemonicsByString.put("ddiv", new Pair<>(111, 0));
        this.mnemonicsByString.put("irem", new Pair<>(112, 0));
        this.mnemonicsByString.put("lrem", new Pair<>(113, 0));
        this.mnemonicsByString.put("frem", new Pair<>(114, 0));
        this.mnemonicsByString.put("drem", new Pair<>(115, 0));
        this.mnemonicsByString.put("ineg", new Pair<>(116, 0));
        this.mnemonicsByString.put("lneg", new Pair<>(117, 0));
        this.mnemonicsByString.put("fneg", new Pair<>(118, 0));
        this.mnemonicsByString.put("dneg", new Pair<>(119, 0));
        this.mnemonicsByString.put("ishl", new Pair<>(120, 0));
        this.mnemonicsByString.put("lshl", new Pair<>(121, 0));
        this.mnemonicsByString.put("ishr", new Pair<>(122, 0));
        this.mnemonicsByString.put("lshr", new Pair<>(123, 0));
        this.mnemonicsByString.put("iushr", new Pair<>(124, 0));
        this.mnemonicsByString.put("lushr", new Pair<>(125, 0));
        this.mnemonicsByString.put("iand", new Pair<>(126, 0));
        this.mnemonicsByString.put("land", new Pair<>(127, 0));
        this.mnemonicsByString.put("ior", new Pair<>(128, 0));
        this.mnemonicsByString.put("lor", new Pair<>(129, 0));
        this.mnemonicsByString.put("ixor", new Pair<>(130, 0));
        this.mnemonicsByString.put("lxor", new Pair<>(131, 0));
        this.mnemonicsByString.put("iinc", new Pair<>(132, 2));
        this.mnemonicsByString.put("i2l", new Pair<>(133, 0));
        this.mnemonicsByString.put("i2f", new Pair<>(134, 0));
        this.mnemonicsByString.put("i2d", new Pair<>(135, 0));
        this.mnemonicsByString.put("l2i", new Pair<>(136, 0));
        this.mnemonicsByString.put("l2f", new Pair<>(137, 0));
        this.mnemonicsByString.put("l2d", new Pair<>(138, 0));
        this.mnemonicsByString.put("f2i", new Pair<>(139, 0));
        this.mnemonicsByString.put("f2l", new Pair<>(140, 0));
        this.mnemonicsByString.put("f2d", new Pair<>(141, 0));
        this.mnemonicsByString.put("d2i", new Pair<>(142, 0));
        this.mnemonicsByString.put("d2l", new Pair<>(143, 0));
        this.mnemonicsByString.put("d2f", new Pair<>(144, 0));
        this.mnemonicsByString.put("i2b", new Pair<>(145, 0));
        this.mnemonicsByString.put("i2c", new Pair<>(146, 0));
        this.mnemonicsByString.put("i2s", new Pair<>(147, 0));
        this.mnemonicsByString.put("lcmp", new Pair<>(148, 2));
        this.mnemonicsByString.put("fcmpl", new Pair<>(149, 2));
        this.mnemonicsByString.put("fcmpg", new Pair<>(150, 2));
        this.mnemonicsByString.put("dcmpl", new Pair<>(151, 2));
        this.mnemonicsByString.put("dcmpg", new Pair<>(152, 2));
        this.mnemonicsByString.put("ifeq", new Pair<>(153, 2));
        this.mnemonicsByString.put("ifne", new Pair<>(154, 2));
        this.mnemonicsByString.put("iflt", new Pair<>(155, 2));
        this.mnemonicsByString.put("ifge", new Pair<>(156, 2));
        this.mnemonicsByString.put("ifgt", new Pair<>(157, 2));
        this.mnemonicsByString.put("ifle", new Pair<>(158, 2));
        this.mnemonicsByString.put("if_icmpeq", new Pair<>(159, 2));
        this.mnemonicsByString.put("if_icmpne", new Pair<>(160, 2));
        this.mnemonicsByString.put("if_icmplt", new Pair<>(161, 2));
        this.mnemonicsByString.put("if_icmpge", new Pair<>(162, 2));
        this.mnemonicsByString.put("if_icmpgt", new Pair<>(163, 2));
        this.mnemonicsByString.put("if_icmple", new Pair<>(164, 2));
        this.mnemonicsByString.put("if_acmpeq", new Pair<>(165, 2));
        this.mnemonicsByString.put("if_acmpne", new Pair<>(166, 2));
        this.mnemonicsByString.put("goto", new Pair<>(167, 2));
        this.mnemonicsByString.put("jsr", new Pair<>(168, 2));
        this.mnemonicsByString.put("ret", new Pair<>(169, 1));
        // special case
        this.mnemonicsByString.put("tableswitch", new Pair<>(170, 12));
        // special case
        this.mnemonicsByString.put("lookupswitch", new Pair<>(171, 8));
        this.mnemonicsByString.put("ireturn", new Pair<>(172, 0));
        this.mnemonicsByString.put("lreturn", new Pair<>(173, 0));
        this.mnemonicsByString.put("freturn", new Pair<>(174, 0));
        this.mnemonicsByString.put("dreturn", new Pair<>(175, 0));
        this.mnemonicsByString.put("areturn", new Pair<>(176, 0));
        this.mnemonicsByString.put("return", new Pair<>(177, 0));
        this.mnemonicsByString.put("getstatic", new Pair<>(178, 2));
        this.mnemonicsByString.put("putstatic", new Pair<>(179, 2));
        this.mnemonicsByString.put("getfield", new Pair<>(180, 2));
        this.mnemonicsByString.put("putfield", new Pair<>(181, 2));
        this.mnemonicsByString.put("invokevirtual", new Pair<>(182, 2));
        this.mnemonicsByString.put("invokespecial", new Pair<>(183, 2));
        this.mnemonicsByString.put("invokestatic", new Pair<>(184, 2));
        this.mnemonicsByString.put("invokeinterface", new Pair<>(185, 4));
        this.mnemonicsByString.put("invokedynamic", new Pair<>(186, 4));
        this.mnemonicsByString.put("new", new Pair<>(187, 2));
        this.mnemonicsByString.put("newarray", new Pair<>(188, 1));
        this.mnemonicsByString.put("anewarray", new Pair<>(189, 2));
        this.mnemonicsByString.put("arraylength", new Pair<>(190, 0));
        this.mnemonicsByString.put("athrow", new Pair<>(191, 0));
        this.mnemonicsByString.put("checkcast", new Pair<>(192, 2));
        this.mnemonicsByString.put("instanceof", new Pair<>(193, 2));
        this.mnemonicsByString.put("monitorenter", new Pair<>(194, 0));
        this.mnemonicsByString.put("monitorexit", new Pair<>(195, 0));
        // special case
        this.mnemonicsByString.put("wide", new Pair<>(196, 3));
        this.mnemonicsByString.put("multianewarray", new Pair<>(197, 3));
        this.mnemonicsByString.put("ifnull", new Pair<>(198, 2));
        this.mnemonicsByString.put("ifnonnull", new Pair<>(199, 2));
        this.mnemonicsByString.put("goto_w", new Pair<>(200, 4));
        this.mnemonicsByString.put("jsr_w", new Pair<>(201, 4));

        this.mnemonicsByString.forEach((mnemonic, opcode) -> this.mnemonicsByOpcode.put(opcode.getFirst(), new Pair<>(mnemonic, opcode.getSecond())));
    }

    public Pair<String, Integer> getMnemonicByOpcode(Integer mnemonic) {
        return this.mnemonicsByOpcode.get(mnemonic);
    }

    public Pair<Integer, Integer> getOpcodeByMnemonic(String mnemonic) {
        return this.mnemonicsByString.get(mnemonic);
    }

}
