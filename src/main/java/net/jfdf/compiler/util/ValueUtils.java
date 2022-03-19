package net.jfdf.compiler.util;

import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;

public class ValueUtils {
    public static Variable asVariable(Object variable) {
        // Here we do if 1 == 1, so if someone uses IDE for compiled code, it does not show warnings
        if(1 == 1) throw new IllegalStateException("Trying to call ValueUtils.asVariable(...) with a code running on JVM.");

        return new Variable("", Variable.Scope.LOCAL);
    }

    public static Variable asReference(Object variable) {
        // Here we do if 1 == 1, so if someone uses IDE for compiled code, it does not show warnings
        if(1 == 1) throw new IllegalStateException("Trying to call ValueUtils.asReference(...) with a code running on JVM.");

        return new Variable("", Variable.Scope.LOCAL);
    }

    public static Variable asVariable(boolean variable) {
        return asVariable((Object) variable);
    }

    public static Variable asVariable(byte variable) {
        return asVariable((Object) variable);
    }

    public static Variable asVariable(short variable) {
        return asVariable((Object) variable);
    }

    public static Variable asVariable(int variable) {
        return asVariable((Object) variable);
    }

    public static Variable asVariable(long variable) {
        return asVariable((Object) variable);
    }

    public static Variable asVariable(float variable) {
        return asVariable((Object) variable);
    }

    public static Variable asVariable(double variable) {
        return asVariable((Object) variable);
    }

    public static Variable asVariable(char variable) {
        return asVariable((Object) variable);
    }

    public static IText asText(String text) {
        // Here we do if 1 == 1, so if someone uses IDE for compiled code, it does not show warnings
        if(1 == 1) throw new IllegalStateException("Trying to call ValueUtils.asText(...) with a code running on JVM.");

        return new Text();
    }

    public static INumber asNumber(int number) {
        // Here we do if 1 == 1, so if someone uses IDE for compiled code, it does not show warnings
        if(1 == 1) throw new IllegalStateException("Trying to call ValueUtils.asNumber(...) with a code running on JVM.");

        return new Number();
    }

    public static INumber asNumber(long number) {
        // Here we do if 1 == 1, so if someone uses IDE for compiled code, it does not show warnings
        if(1 == 1) throw new IllegalStateException("Trying to call ValueUtils.asNumber(...) with a code running on JVM.");

        return new Number();
    }

    public static INumber asNumber(float number) {
        // Here we do if 1 == 1, so if someone uses IDE for compiled code, it does not show warnings
        if(1 == 1) throw new IllegalStateException("Trying to call ValueUtils.asNumber(...) with a code running on JVM.");

        return new Number();
    }

    public static INumber asNumber(double number) {
        // Here we do if 1 == 1, so if someone uses IDE for compiled code, it does not show warnings
        if(1 == 1) throw new IllegalStateException("Trying to call ValueUtils.asNumber(...) with a code running on JVM.");

        return new Number();
    }
}