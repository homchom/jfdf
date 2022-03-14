package net.jfdf.compiler.data.stack;

public class ObjectStackValue extends ReferencedStackValue {
    private final String type;

    public ObjectStackValue(String type, String methodName, int blockOperatorIndex) {
        super(methodName, blockOperatorIndex);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getDescriptor() {
        return "L" + type + ";";
    }
}
