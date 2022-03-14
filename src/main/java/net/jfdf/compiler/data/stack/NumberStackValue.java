package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Number;
import org.objectweb.asm.Type;

public class NumberStackValue implements IStackValue {
    private final Number value;
    private final java.lang.Number javaValue;

    public NumberStackValue() {
        this.value = new Number().Set(0);
        this.javaValue = null;
    }

    public NumberStackValue(byte value) {
        this.value = new Number().Set(value);
        this.javaValue = value;
    }

    public NumberStackValue(short value) {
        this.value = new Number().Set(value);
        this.javaValue = value;
    }

    public NumberStackValue(int value) {
        this.value = new Number().Set(value);
        this.javaValue = value;
    }

    public NumberStackValue(long value) {
        this.value = new Number().Set(value);
        this.javaValue = value;
    }

    public NumberStackValue(float value) {
        this.value = new Number().Set(value);
        this.javaValue = value;
    }

    public NumberStackValue(double value) {
        this.value = new Number().Set(value);
        this.javaValue = value;
    }

    public java.lang.Number getJavaValue() {
        return this.javaValue;
    }

    @Override
    public CodeValue getTransformedValue() {
        return value;
    }

    @Override
    public String getDescriptor() {
        return "J";
    }
}
