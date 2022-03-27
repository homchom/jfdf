package net.jfdf.addon.splitter;

import net.jfdf.jfdf.blocks.CodeBlock;

import java.util.ArrayList;
import java.util.List;

public class IfScope extends Scope {
    public IfScope(CodeBlock ifBlock, CodeBlock ifStart, List<CodeBlock> content, CodeBlock ifEnd) {
        super(new ArrayList<>());

        this.content.add(ifBlock);
        this.content.add(ifStart);

        this.content.addAll(content);
        this.content.add(ifEnd);

        totalLength = 4;

        for(CodeBlock block : content) {
            if(block instanceof Scope) {
                totalLength += ((Scope) block).totalLength;
            } else {
                totalLength += 2;
            }
        }
    }

    @Override
    public List<CodeBlock> getContent() {
        return content.subList(2, content.size() - 1);
    }

    public List<CodeBlock> getStart() {
        return content.subList(0, 2);
    }

    public CodeBlock getEnd() {
        return content.get(content.size() - 1);
    }
}
