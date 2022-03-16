package net.jfdf.compiler.library;

import net.jfdf.compiler.annotation.CompileWithExecute;
import net.jfdf.compiler.annotation.NoCompile;
import net.jfdf.compiler.annotation.NoConstructors;
import net.jfdf.jfdf.mangement.*;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;
import net.jfdf.transformer.util.NumberMath;

@NoConstructors
public class References {
    private References() {}

    @FunctionWithArgs(
            name = "_jfdf>std>malloc",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Memory Allocate\"}],\"text\":\"\"}'}"
    )
    @CompileWithExecute
    public static void malloc() {
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));
        List deletedReferences = new List(new Variable("_jfdfRD", Variable.Scope.NORMAL));

        Variable referenceCount = new Variable("_jfdfRC", Variable.Scope.NORMAL);
        Variable memoryUsage = new Variable("_jfdfMemoryUsage", Variable.Scope.NORMAL);

        Variable pointer = new Variable("_jfdfRP", Variable.Scope.LOCAL);
        Variable deletedReferencesLength = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable deletedReferencePointer = new Variable("tmp0", Variable.Scope.LOCAL);

        Variable functionDepth = new Variable("_jfdfFD", Variable.Scope.LOCAL);

        If.Variable.NotEquals(deletedReferences.length(deletedReferencesLength), new CodeValue[]{ new Number().Set(0.0f) }, false);
        VariableControl.Set(
                pointer,
                deletedReferences.get(
                        deletedReferencePointer,
                        new Number().Set(1.0f)
                )
        );

        VariableControl.Increment(memoryUsage, new Number().SetToString("0.01"));
        VariableControl.Decrement(functionDepth);
        Control.Return();
        If.End();

        referenceCountList.add(new Number().Set(0));
        VariableControl.Increment(referenceCount);
        VariableControl.Increment(memoryUsage, new Number().SetToString("0.01"));
        VariableControl.Set(pointer, referenceCount);
    }

    @FunctionWithArgs(
            name = "_jfdf>std>salloc",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Smart Allocate (Game Variables)\"}],\"text\":\"\"}'}"
    )
    @CompileWithExecute
    public static void salloc(Variable varName) {
        Variable shouldAlloc = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable pointerVariable = new Variable("%var(" + varName.getName() + ")", Variable.Scope.NORMAL);
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));

        Variable returnVariable = new Variable("_jfdfRP", Variable.Scope.LOCAL);
        Variable functionDepth = new Variable("_jfdfFD", Variable.Scope.LOCAL);

        INumber false_ = new Number().Set(0.0f);
        INumber true_ = new Number().Set(1.0f);

        VariableControl.Set(shouldAlloc, false_);

        If.Variable.Exists(pointerVariable, true);
            VariableControl.Set(shouldAlloc, true_);
        If.End();

        If.Variable.Equals(pointerVariable, new CodeValue[]{ new Number().Set(0.0f) }, false);
            VariableControl.Set(shouldAlloc, true_);
        If.Else();
            If.Variable.IsType(pointerVariable, Tags.VariableType.NUMBER, false);
                If.Variable.NotEquals(pointerVariable, new CodeValue[]{ new Number().Set(1.0f) }, false);
                    VariableControl.Set(shouldAlloc, true_);
                If.End();
            If.Else();
                VariableControl.Set(shouldAlloc, true_);
            If.End();
        If.End();

        If.Variable.Equals(shouldAlloc, new CodeValue[]{ true_ }, false);
        VariableControl.Increment(new Variable("_jfdfFD", Variable.Scope.LOCAL));
        Functions.Call("_jfdf>std>malloc");
        VariableControl.Set(pointerVariable, returnVariable);

        referenceCountList.set(returnVariable, new Number().Set(1));
        VariableControl.Decrement(functionDepth);
        Control.Return();
        If.End();

        VariableControl.Set(returnVariable, pointerVariable);
        referenceCountList.set(returnVariable, new Number().Set(1));
    }

    @FunctionWithArgs(
            name = "_jfdf>std>sallocl",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Smart Allocate (Local Variables)\"}],\"text\":\"\"}'}"
    )
    @CompileWithExecute
    public static void sallocl(Variable varName) {
        Variable shouldAlloc = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable pointerVariable = new Variable("%var(" + varName.getName() + ")", Variable.Scope.LOCAL);
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));

        Variable returnVariable = new Variable("_jfdfRP", Variable.Scope.LOCAL);
        Variable functionDepth = new Variable("_jfdfFD", Variable.Scope.LOCAL);

        INumber false_ = new Number().Set(0.0f);
        INumber true_ = new Number().Set(1.0f);

        VariableControl.Set(shouldAlloc, false_);

        If.Variable.Exists(pointerVariable, true);
            VariableControl.Set(shouldAlloc, true_);
        If.End();

        If.Variable.Equals(pointerVariable, new CodeValue[]{ new Number().Set(0.0f) }, false);
            VariableControl.Set(shouldAlloc, true_);
        If.Else();
            If.Variable.IsType(pointerVariable, Tags.VariableType.NUMBER, false);
                If.Variable.NotEquals(pointerVariable, new CodeValue[]{ new Number().Set(1.0f) }, false);
                    VariableControl.Set(shouldAlloc, true_);
                If.End();
            If.Else();
                VariableControl.Set(shouldAlloc, true_);
            If.End();
        If.End();

        If.Variable.Equals(shouldAlloc, new CodeValue[]{ true_ }, false);
            VariableControl.Increment(new Variable("_jfdfFD", Variable.Scope.LOCAL));
            Functions.Call("_jfdf>std>malloc");
            VariableControl.Set(pointerVariable, returnVariable);

            referenceCountList.set(returnVariable, new Number().Set(1));
            VariableControl.Decrement(functionDepth);
            Control.Return();
        If.End();

        VariableControl.Set(returnVariable, pointerVariable);
        referenceCountList.set(returnVariable, new Number().Set(1));
    }

    @FunctionWithArgs(
            name = "_jfdf>std>free",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Free Memory\"}],\"text\":\"\"}'}"
    )
    @CompileWithExecute
    public static void free(Variable pointers) {
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));
        VariableControl.SortList(pointers, null, Tags.SortOrder.DESCENDING);

        List deletedReferences = new List(new Variable("_jfdfRD", Variable.Scope.NORMAL));
        Variable referenceCount = new Variable("_jfdfRC", Variable.Scope.NORMAL);
        Variable memoryUsage = new Variable("_jfdfMemoryUsage", Variable.Scope.NORMAL);

        Variable pointer = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable reference = new Variable("_jfdfR%var(tmp0)", Variable.Scope.NORMAL);

        Repeat.ForEach(pointer, pointers);
            VariableControl.Set(reference, new Number().Set(0.0f));

            If.Variable.Equals(pointer, new CodeValue[]{referenceCount}, false);
                referenceCountList.remove(pointer);

                VariableControl.Decrement(memoryUsage, new Number().SetToString("0.01"));
                VariableControl.Decrement(referenceCount);
                Control.Skip();
            If.End();

            VariableControl.Decrement(memoryUsage, new Number().SetToString("0.01"));
            deletedReferences.add(pointer);
        Repeat.End();
    }

    @FunctionWithArgs(
            name = "_jfdf>std>gc",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Collect Garbage\"}],\"text\":\"\"}'}"
    )
    @CompileWithExecute
    public static void collectGarbage() {
        Variable functionDepth = new Variable("_jfdfFD", Variable.Scope.LOCAL);
        Variable nextFunctionDepth = new Variable("tmp2", Variable.Scope.LOCAL);

        VariableControl.SetToSum(nextFunctionDepth, functionDepth, new Number().Set(1.0f));

        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));
        List freePointers = new List("_jfdffa>%var(tmp2)>0");

        Variable referencePointer = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable referenceCounter = new Variable("tmp1", Variable.Scope.LOCAL);

        VariableControl.Set(referencePointer, new Number().Set(1.0f));
        Repeat.ForEach(referenceCounter, referenceCountList);
            If.Variable.Equals(referenceCounter, new CodeValue[]{ new Number().Set(0.0f) }, false);
                freePointers.add(referencePointer);
            If.End();

            VariableControl.Increment(referencePointer);
        Repeat.End();

        VariableControl.Increment(functionDepth);
        Functions.Call("_jfdf>std>free");
    }

    @FunctionWithArgs(
            name = "_jfdf>std>procEnd",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Process End\"}],\"text\":\"\"}'}"
    )
    @CompileWithExecute
    public static void processEnd() {
        VariableControl.Increment(new Variable("_jfdfFD", Variable.Scope.LOCAL));
        Functions.Call("_jfdf>std>gc");
        Control.End();
    }

    private final static Variable referenceCountList = new Variable("_jfdfRCL", Variable.Scope.NORMAL);

    @NoCompile
    public static void incrementRefCount(INumber pointer) {
        VariableControl.SetListValue(
                referenceCountList,
                pointer,
                Number.Add(NumberMath.listValue(referenceCountList, pointer), new Number().Set(1))
        );
    }

    @NoCompile
    public static void decrementRefCount(INumber pointer) {
        VariableControl.SetListValue(
                referenceCountList,
                pointer,
                Number.Subtract(NumberMath.listValue(referenceCountList, pointer), new Number().Set(1))
        );
    }
}
