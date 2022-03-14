package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.mangement.VariableControl;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;
import net.jfdf.transformer.util.NumberMath;

public class MathFunctionStackValue implements IStackValue {
    private final Number value;

    public MathFunctionStackValue(INumber value, MathFunction operation) {
        this.value = switch (operation) {
            case NEGATIVE -> NumberMath.negative(value);
            case ROUND -> NumberMath.round(value);
            default -> throw new IllegalStateException("Unexpected function: " + operation + ".");
        };
    }

    public MathFunctionStackValue(INumber value, MathFunction operation, String methodName, int blockOperationIndex) {
        this.value = switch (operation) {
            case CAST_TO_BYTE -> {
                Variable castVariable = new Variable("_jco>" + methodName + ">" + blockOperationIndex, Variable.Scope.LOCAL);

                VariableControl.Bitwise(
                        castVariable,
                        NumberMath.add(
                                value,
                                new Number().Set(-Byte.MIN_VALUE)
                        ),
                        new Number().Set(Byte.MAX_VALUE - Byte.MIN_VALUE),
                        Tags.Operator.AND
                );

                yield Number.Subtract(
                        Number.AsVariable(castVariable),
                        new Number().Set(-Byte.MIN_VALUE)
                );
            }

            case CAST_TO_CHAR -> {
                Variable castVariable = new Variable("_jco>" + methodName + ">" + blockOperationIndex, Variable.Scope.LOCAL);

                VariableControl.Bitwise(
                        castVariable,
                        value,
                        new Number().Set(Character.MAX_VALUE),
                        Tags.Operator.AND
                );

                yield Number.AsVariable(castVariable);
            }

            case CAST_TO_SHORT -> {
                Variable castVariable = new Variable("_jco>" + methodName + ">" + blockOperationIndex, Variable.Scope.LOCAL);

                VariableControl.Bitwise(
                        castVariable,
                        NumberMath.add(
                                value,
                                new Number().Set(-Short.MIN_VALUE)
                        ),
                        new Number().Set(Short.MAX_VALUE - Short.MIN_VALUE),
                        Tags.Operator.AND
                );

                yield Number.Subtract(
                        Number.AsVariable(castVariable),
                        new Number().Set(-Short.MIN_VALUE)
                );
            }

            default -> throw new IllegalStateException("Unexpected function: " + operation + ".");
        };
    }

    public MathFunctionStackValue(INumber value1, INumber value2, MathFunction operation) {
        this.value = switch (operation) {
            case RANDOM -> NumberMath.random(value1, value2);
            default -> throw new IllegalStateException("Unexpected function: " + operation + ".");
        };
    }

    public MathFunctionStackValue(Variable variable, INumber number, MathFunction operation) {
        this.value = switch (operation) {
            case LIST_VALUE -> NumberMath.listValue(variable, number);
            default -> throw new IllegalStateException("Unexpected function: " + operation + ".");
        };
    }

    @Override
    public CodeValue getTransformedValue() {
        return value;
    }

    @Override
    public String getDescriptor() {
        return "J";
    }

    public enum MathFunction {
        NEGATIVE,
        ROUND,
        RANDOM,
        LIST_VALUE,

        // Casting functions
        CAST_TO_BYTE,
        CAST_TO_CHAR,
        CAST_TO_SHORT
    }
}