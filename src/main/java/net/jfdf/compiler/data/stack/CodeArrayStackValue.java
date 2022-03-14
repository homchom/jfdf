package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.values.CodeValue;

public class CodeArrayStackValue implements IStackValue {
    private final CodeValue[] codeValues;
    private int currentIndex;

    public CodeArrayStackValue(int length) {
        this.codeValues = new CodeValue[length];
    }

    public void next(CodeValue value) {
        if(currentIndex >= codeValues.length) throw new IllegalStateException("next(...) has been called more times than array length.");

        codeValues[currentIndex++] = value;
    }

    @Override
    public CodeValue getTransformedValue() {
        throw new IllegalStateException("Use CodeArrayStackValue.getValues() instead.");
    }

    public CodeValue[] getValues() {
        return codeValues;
    }

    @Override
    public String getDescriptor() {
        return null;
    }
}
