package net.jfdf.compiler.data.instruction;

public enum InstructionType {
    /** Instruction with no arguments, produced by {@link org.objectweb.asm.MethodVisitor#visitInsn(int)} */
    NO_ARGUMENTS,

    /** Label-related instructions */
    JUMP,
    LABEL,
    FRAME,
    LINE_NUMBER,

    /** Field and variable instructions */
    FIELD,
    VARIABLE,

    /** Integer-related instructions */
    INTEGER,
    INTEGER_INCREMENT,

    /** Invoke instructions */
    INVOKE,
    INVOKE_DYNAMIC,

    /** Array and Object-related instructions */
    LDC,
    TYPE,
    MULTI_ANEW_ARRAY,

    /** Try-catch and switch instructions */
    TRY_CATCH,
    TABLE_SWITCH,
    LOOKUP_SWITCH,

    /** Local variable and maximum instructions */
    MAXS,
    LOCAL_VARIABLE,
}
