package net.jfdf.jfdf.blocks;

import net.jfdf.jfdf.values.Text;

public class CallFunctionBlock implements CodeBlock {

    private final String functionName;
    private final Text functionNameAsText;

    public CallFunctionBlock(String functionName) {
        this.functionName = functionName;
        this.functionNameAsText = null;
    }

    public CallFunctionBlock(Text functionName) {
        this.functionName = null;
        this.functionNameAsText = functionName;
    }

    public String asJSON() {
        return "{\"id\":\"block\",\"block\":\"call_func\",\"args\":{\"items\":[]},\"data\":\"" + (functionName != null ? functionName : functionNameAsText.Get()) +"\"}";
    }
}