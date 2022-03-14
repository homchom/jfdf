package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.values.CodeValue;

public class CompareStackValue implements IStackValue {
    private final CompareType compareType;

    private final IStackValue stackValue1;
    private final IStackValue stackValue2;

    public CompareStackValue(CompareType compareType, IStackValue stackValue1, IStackValue stackValue2) {
        this.compareType = compareType;

        this.stackValue1 = stackValue1;
        this.stackValue2 = stackValue2;
    }

    @Override
    public CodeValue getTransformedValue() {
        throw new IllegalStateException("Getting transformed value is not support");
    }

    @Override
    public String getDescriptor() {
        return null;
    }

    public CompareType getCompareType() {
        return compareType;
    }

    public IStackValue getStackValue1() {
        return stackValue1;
    }

    public IStackValue getStackValue2() {
        return stackValue2;
    }

    public enum CompareType {
        NORMAL,
        LIST_CONTAINS
    }
}
