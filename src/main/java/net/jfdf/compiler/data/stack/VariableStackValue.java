package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Variable;

public class VariableStackValue implements IStackValue {
    private final String valueDescriptor;
    private final Variable value;

    public VariableStackValue(String valueDescriptor, String name) {
        this.valueDescriptor = valueDescriptor;
        this.value = new Variable(name, Variable.Scope.LOCAL);
    }

    public VariableStackValue(String valueDescriptor, String name, Variable.Scope scope) {
        this.valueDescriptor = valueDescriptor;
        this.value = new Variable(name, scope);
    }

    @Override
    public CodeValue getTransformedValue() {
        return value;
    }

    @Override
    public String getDescriptor() {
        return valueDescriptor;
    }
}
