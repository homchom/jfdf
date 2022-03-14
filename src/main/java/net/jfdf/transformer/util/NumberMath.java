package net.jfdf.transformer.util;

import net.jfdf.jfdf.values.INumber;
import net.jfdf.jfdf.values.Number;
import net.jfdf.jfdf.values.Variable;

public class NumberMath {
    public static Number listValue(Variable variable, INumber index) {
        return Number.AsListValue(variable, toNumber(index));
    }

    public static Number add(INumber a, INumber b) {
        return Number.Add(toNumber(a), toNumber(b));
    }

    public static Number subtract(INumber a, INumber b) {
        return Number.Subtract(toNumber(a), toNumber(b));
    }

    public static Number multiply(INumber a, INumber b) {
        return Number.Multiplication(toNumber(a), toNumber(b));
    }

    public static Number divide(INumber a, INumber b) {
        return Number.Division(toNumber(a), toNumber(b));
    }

    public static Number divideInteger(INumber a, INumber b) {
        return Number.Round(Number.Division(toNumber(a), toNumber(b)));
    }

    public static Number remainder(INumber a, INumber b) {
        return Number.Remainder(toNumber(a), toNumber(b));
    }

    public static Number random(INumber a, INumber b) {
        return Number.Random(toNumber(a), toNumber(b));
    }

    public static Number negative(INumber a) {
        return Number.Negative(toNumber(a));
    }

    public static Number round(INumber a) {
        return Number.Round(toNumber(a));
    }

    public static Number toNumber(INumber num) {
        if(num instanceof Variable) {
            return Number.AsVariable((Variable) num);
        }

        return (Number) num;
    }
}
