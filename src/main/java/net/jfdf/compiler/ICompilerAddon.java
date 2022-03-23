package net.jfdf.compiler;

import net.jfdf.compiler.data.stack.IStackValue;
import net.jfdf.jfdf.blocks.CodeBlock;
import net.jfdf.jfdf.blocks.CodeHeader;

import java.util.List;
import java.util.Map;

public interface ICompilerAddon {
    public boolean onInitClass(String type, List<IStackValue> stack);
    public boolean onInvokeConstructor(String type, String descriptor, List<IStackValue> stack);

    public boolean onInvokeMember(String owner, String name, String descriptor, List<IStackValue> stack);
    public boolean onInvokeStatic(String owner, String name, String descriptor, List<IStackValue> stack);
}
