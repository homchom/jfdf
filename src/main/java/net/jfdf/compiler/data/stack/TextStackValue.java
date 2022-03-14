package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Text;

public class TextStackValue implements IStackValue {
    private final Text value;

    public TextStackValue(String value) {
        this.value = new Text().Set(value);
    }

    public Text getValue() {
        return value;
    }

    @Override
    public String getDescriptor() {
        return "Ljava/lang/String;";
    }

    @Override
    public CodeValue getTransformedValue() {
        return value;
    }
}
