package net.jfdf.jfdf;

import net.jfdf.jfdf.blocks.CodeBlock;
import net.jfdf.jfdf.blocks.CodeHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AddonsManager {
    private static final List<IBlocksAddon> addons = new ArrayList<>();

    public static void registerAddon(IBlocksAddon addon) {
        addons.add(addon);
    }

    public static void unregisterAddon(IBlocksAddon addon) {
        addons.remove(addon);
    }

    public static Map<CodeHeader, List<CodeBlock>> publishPreGenerateLine(CodeHeader codeHeader, List<CodeBlock> blocks) {
        Map<CodeHeader, List<CodeBlock>> result = new HashMap<>();

        for (IBlocksAddon addon : addons) {
            Map<CodeHeader, List<CodeBlock>> addonResult = addon.onPreGenerateLine(codeHeader, blocks);

            if(addonResult != null) {
                result.putAll(addonResult);
            }
        }

        return result;
    }
}
