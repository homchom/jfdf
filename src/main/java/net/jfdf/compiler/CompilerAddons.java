package net.jfdf.compiler;

import net.jfdf.compiler.data.stack.IStackValue;

import java.util.ArrayList;
import java.util.List;

public class CompilerAddons {
    private static final List<ICompilerAddon> addons = new ArrayList<>();

    public static void registerAddon(ICompilerAddon addon) {
        addons.add(addon);
    }

    public static void unregisterAddon(ICompilerAddon addon) {
        addons.remove(addon);
    }

    public static boolean publishInitClassEvent(String type, List<IStackValue> stack) {
        for (ICompilerAddon addon : addons) {
            if(addon.onInitClass(type, stack)) return true;
        }

        return false;
    }

    public static boolean publishInvokeConstructorEvent(String type, String descriptor, List<IStackValue> stack) {
        for (ICompilerAddon addon : addons) {
            if(addon.onInvokeConstructor(type, descriptor, stack)) return true;
        }

        return false;
    }

    public static boolean publishInvokeMemberEvent(String owner, String name, String descriptor, List<IStackValue> stack) {
        for (ICompilerAddon addon : addons) {
            if(addon.onInvokeMember(owner, name, descriptor, stack)) return true;
        }

        return false;
    }

    public static boolean publishInvokeStaticEvent(String owner, String name, String descriptor, List<IStackValue> stack) {
        for (ICompilerAddon addon : addons) {
            if(addon.onInvokeStatic(owner, name, descriptor, stack)) return true;
        }

        return false;
    }
}
