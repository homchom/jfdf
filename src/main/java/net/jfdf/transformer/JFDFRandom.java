package net.jfdf.transformer;

import net.jfdf.jfdf.mangement.VariableControl;
import net.jfdf.jfdf.values.Number;
import net.jfdf.jfdf.values.Tags;
import net.jfdf.jfdf.values.Variable;

import java.util.Random;

@Deprecated(forRemoval = true)
public class JFDFRandom {
    private static Random random;

    static {
        random = new Random();
    }

    public Variable generateDecimalNumber() {
        return generateDecimalNumber(new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL));
    }

    public Variable generateIntegerNumber() {
        return generateIntegerNumber(new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL));
    }

    public Variable generateIntegerNumber(Number maxInt) {
        return generateIntegerNumber(maxInt, new Variable("tmp_" + random.nextInt(), Variable.Scope.LOCAL));
    }

    public Variable generateDecimalNumber(Variable var) {
        VariableControl.RandomNumber(var,
                new Number().Set(0.0f),
                new Number().Set(1.0f),
                Tags.RoundingMode.DECIMAL_NUMBER);

        return var;
    }

    public Variable generateIntegerNumber(Variable var) {
        VariableControl.RandomNumber(var,
                new Number().Set(Long.MIN_VALUE / 1000L),
                new Number().Set(Long.MAX_VALUE / 1000L),
                Tags.RoundingMode.WHOLE_NUMBER);

        return var;
    }

    public Variable generateIntegerNumber(Number maxInt, Variable var) {
        VariableControl.RandomNumber(var,
                new Number().Set(0.0f),
                maxInt,
                Tags.RoundingMode.WHOLE_NUMBER);

        return var;
    }
}
