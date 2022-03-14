package net.jfdf.jfdf.blocks;

public class CallProcessBlock implements CodeBlock {

    private String processName;

    public CallProcessBlock(String processName) {
        this.processName = processName;
    }

    public String asJSON() {
        return "{\"id\":\"block\",\"block\":\"start_process\",\"args\":{\"items\":[]},\"data\":\"" + processName +"\"}";
    }
}