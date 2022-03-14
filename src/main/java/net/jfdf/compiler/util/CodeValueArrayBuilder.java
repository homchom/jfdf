package net.jfdf.compiler.util;

import net.jfdf.jfdf.values.CodeValue;

public class CodeValueArrayBuilder<T extends CodeValue> {
    private CodeValueArrayBuilder() {}

    public static <T extends CodeValue> CodeValueArrayBuilder<T> start(int length) {
        // Here we do if 1 == 1, so if someone uses IDE for compiled code, it does not show warnings
        if(1 == 1) throw new IllegalStateException("CodeValueArrayBuilder couldn't be used inside code executed by JVM.");

        return new CodeValueArrayBuilder<>();
    }

    public CodeValueArrayBuilder<T> next(CodeValue codeValue) {
        // Here we do if 1 == 1, so if someone uses IDE for compiled code, it does not show warnings
        if(1 == 1) throw new IllegalStateException("CodeValueArrayBuilder couldn't be used inside code executed by JVM.");

        return this;
    }

    @SuppressWarnings("unchecked")
    public T[] getArray() {
        // Here we do if 1 == 1, so if someone uses IDE for compiled code, it does not show warnings
        if(1 == 1) throw new IllegalStateException("CodeValueArrayBuilder couldn't be used inside code executed by JVM.");

        return (T[]) new CodeValue[0];
    }
}
