package net.jfdf.jfdf;

import net.jfdf.jfdf.blocks.CodeBlock;

import java.util.ArrayList;
import java.util.List;

public final class AddonsManager {
    private static final List<IBlocksAddon> addons = new ArrayList<>();

    public static void registerAddon(IBlocksAddon addon) {
        addons.add(addon);
    }

    public static void unregisterAddon(IBlocksAddon addon) {
        addons.remove(addon);
    }

    public static void publishPreGenerateLine(List<CodeBlock> blocks) {
        for (IBlocksAddon addon : addons) {
            addon.onPreGenerateLine(blocks);
        }
    }
}
