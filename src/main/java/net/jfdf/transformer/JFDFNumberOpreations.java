package net.jfdf.transformer;

import net.jfdf.jfdf.mangement.VariableControl;
import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.INumber;
import net.jfdf.jfdf.values.Tags;
import net.jfdf.jfdf.values.Variable;

import java.util.Random;

@Deprecated(forRemoval = true)
public class JFDFNumberOpreations {
    private static Random random;

    static {
        random = new Random();
    }

    public static Variable add(CodeValue a, CodeValue b) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);

        VariableControl.SetToSum(var, (INumber) a, (INumber) b);
        return var;
    }

    public static Variable subtract(CodeValue a, CodeValue b) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);

        VariableControl.SetToDifference(var, (INumber) a, (INumber) b);
        return var;
    }

    public static Variable multiply(CodeValue a, CodeValue b) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);

        VariableControl.SetToProduct(var, (INumber) a, (INumber) b);
        return var;
    }

    public static Variable divide(CodeValue a, CodeValue b, Tags.DivisionMode divisionMode) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);

        VariableControl.SetToQuotient(var, new INumber[] {(INumber)a, (INumber)b}, divisionMode);
        return var;
    }

    public static Variable remainder(CodeValue a, CodeValue b) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);

        VariableControl.SetToRemainder(var, (INumber) a, (INumber) b);
        return var;
    }

    public static Variable divideInteger(CodeValue a, CodeValue b) {
        return divide(a, b, Tags.DivisionMode.FLOOR_RESULT);
    }

    public static Variable divideDecimal(CodeValue a, CodeValue b) {
        return divide(a, b, Tags.DivisionMode.DEFAULT);
    }

    public static void inc(Variable var, INumber increment) {
        VariableControl.Increment(var, increment);
    }

    public static Variable binaryOr(CodeValue a, CodeValue b) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);
        return var;
    }

    public static Variable binaryXor(CodeValue a, CodeValue b) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);
        return var;
    }

    public static Variable binaryAnd(CodeValue a, CodeValue b) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);
        return var;
    }

    public static Variable binaryShiftLeft(CodeValue a, CodeValue b) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);
        return var;
    }

    public static Variable binaryShiftRight(CodeValue a, CodeValue b) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);
        return var;
    }

    public static Variable binaryUnsignedShiftRight(CodeValue a, CodeValue b) {
        Variable var = new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL);
        return var;
    }
}
