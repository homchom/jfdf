package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.INumber;
import net.jfdf.jfdf.values.Number;
import net.jfdf.transformer.util.NumberMath;

public class MathStackValue implements IStackValue {
    private final Number value;

    public MathStackValue(INumber value1, INumber value2, MathOperation operation) {
        this.value = switch (operation) {
            case ADD -> NumberMath.add(value1, value2);
            case SUBTRACT -> NumberMath.subtract(value1, value2);
            case MULTIPLY -> NumberMath.multiply(value1, value2);
            case DIVIDE_INTEGER -> NumberMath.divideInteger(value1, value2);
            case DIVIDE -> NumberMath.divide(value1, value2);
            case REMAINDER -> NumberMath.remainder(value1, value2);
            default -> throw new IllegalStateException("Unknown operation: " + operation);
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

    public enum MathOperation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE_INTEGER,
        DIVIDE,
        REMAINDER
    }
}
