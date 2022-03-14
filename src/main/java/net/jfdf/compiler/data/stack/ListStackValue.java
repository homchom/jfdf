package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.mangement.Repeat;
import net.jfdf.jfdf.mangement.VariableControl;
import net.jfdf.jfdf.values.INumber;
import net.jfdf.jfdf.values.Number;

public class ListStackValue extends ReferencedStackValue {
    private final String type;

    public ListStackValue(String type, String methodName, int blockOperatorIndex) {
        super(methodName, blockOperatorIndex);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getDescriptor() {
        return "Ljava/util/ArrayList;";
    }
}
