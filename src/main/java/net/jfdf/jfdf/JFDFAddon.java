package net.jfdf.jfdf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.jfdf.jfdf.blocks.CodeBlock;
import net.jfdf.jfdf.blocks.CodeHeader;

@SuppressWarnings("unused")
public class JFDFAddon {

    private static List<JFDFAddon> allAddons = new ArrayList<JFDFAddon>();
    
    private static void onStart() {
        for (JFDFAddon jfdfAddon : allAddons) {
            try {
                jfdfAddon.getClass().getMethod("onStart").invoke(jfdfAddon);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {}
        }
    }

    private static void onHeaderStart() {
        for (JFDFAddon jfdfAddon : allAddons) {
            try {
                jfdfAddon.getClass().getMethod("onHeaderStart").invoke(jfdfAddon);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {}
        }
    }

    private static void onHeaderEnd(CodeHeader codeHeader, List<CodeBlock> codeBlocks) {
        for (JFDFAddon jfdfAddon : allAddons) {
            try {
                jfdfAddon.getClass().getMethod("onHeaderEnd", CodeHeader.class, List.class).invoke(jfdfAddon, codeHeader, codeBlocks);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {}
        }
    }

    private static void onAddCodeBlock(CodeBlock codeBlock) {
        for (JFDFAddon jfdfAddon : allAddons) {
            try {
                jfdfAddon.getClass().getMethod("onAddCodeBlock", CodeBlock.class).invoke(jfdfAddon, codeBlock);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {}
        }
    }

    private static void onGetHeaderValue(String value) {
        for (JFDFAddon jfdfAddon : allAddons) {
            try {
                jfdfAddon.getClass().getMethod("onAddCodeBlock", String.class).invoke(jfdfAddon, value);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {}
        }
    }

    public static void addJFDFAddons(Collection<? extends JFDFAddon> addons) {
        allAddons.addAll(addons);
    }
}