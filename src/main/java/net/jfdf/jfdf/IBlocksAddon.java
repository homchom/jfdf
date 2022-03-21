package net.jfdf.jfdf;

import net.jfdf.jfdf.blocks.CodeBlock;
import net.jfdf.jfdf.blocks.CodeHeader;

import java.util.List;
import java.util.Map;

public interface IBlocksAddon {
    public Map<CodeHeader, List<CodeBlock>> onPreGenerateLine(List<CodeBlock> blocks);
}
