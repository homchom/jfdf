package net.jfdf.addon.splitter;

import net.jfdf.jfdf.blocks.CodeBlock;

import java.util.List;
import java.util.stream.Collectors;

public class Scope implements CodeBlock {
    protected List<CodeBlock> content;
    protected int totalLength;

    public Scope(List<CodeBlock> content) {
        this.content = content;

        this.totalLength = 2;
        for (CodeBlock block : content) {
            if(block instanceof Scope) {
                this.totalLength += ((Scope) block).totalLength;
            } else {
                this.totalLength += 2;
            }
        }
    }

    @Override
    public String asJSON() {
        return content.stream().map(CodeBlock::asJSON).collect(Collectors.joining(","));
    }

    public List<CodeBlock> getContent() {
        return content;
    }
}
