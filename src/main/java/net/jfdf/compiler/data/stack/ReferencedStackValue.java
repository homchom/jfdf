package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.mangement.Functions;
import net.jfdf.jfdf.mangement.VariableControl;
import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Text;
import net.jfdf.jfdf.values.Variable;

public abstract class ReferencedStackValue implements IStackValue {
    protected final Text allocationMethod = new Text().Set("_jfdf>std>sallocl");

    protected final Text allocationVariableName;
    protected final Variable pointer;
    protected final Variable reference;

    protected ReferencedStackValue(String methodName, int blockOperatorIndex) {
        this.allocationVariableName = new Text().Set("_jco>" + methodName + ">" + blockOperatorIndex);
        this.pointer = new Variable("_jco>" + methodName + ">" + blockOperatorIndex, Variable.Scope.LOCAL);
        this.reference = new Variable("_jfdfR%var(_jco>" + methodName + ">" + blockOperatorIndex + ")", Variable.Scope.NORMAL);

        VariableControl.Set(new Variable("_jfdfPFD", Variable.Scope.LOCAL), new Variable("_jfdfFD", Variable.Scope.LOCAL));
        VariableControl.Increment(new Variable("_jfdfFD", Variable.Scope.LOCAL));

        VariableControl.Set(new Variable("_jfdffa>%var(_jfdfFD)>0", Variable.Scope.LOCAL), allocationVariableName);
        Functions.Call(allocationMethod);
    }

    public void setAllocationVariable(String name, Variable.Scope scope) {
        if(scope.equals(Variable.Scope.SAVED))
            throw new IllegalStateException("Cannot allocate saved variable.");

        allocationVariableName.Set(name.replace("%var(_jfdfFD)", "%var(_jfdfPFD)"));
        reference.setName("_jfdfR%var(" + name + ")");

        pointer.setName(name);
        pointer.setScope(scope);

        allocationMethod.Set(scope.equals(Variable.Scope.NORMAL) ? "_jfdf>std>salloc" : "_jfdf>std>sallocl");
    }

    public Text getAllocationMethod() {
        return allocationMethod;
    }

    public Text getAllocationVariableName() {
        return allocationVariableName;
    }

    @Override
    public CodeValue getTransformedValue() {
        return pointer;
    }

    public Variable getReference() {
        return reference;
    }
}
