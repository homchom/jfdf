package net.jfdf.jfdf;

import net.jfdf.jfdf.blocks.CodeBlock;

import java.util.List;

public interface IBlocksAddon {
    public void onPreGenerateLine(List<CodeBlock> blocks);
}
