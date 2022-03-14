package net.jfdf.transformer.classes;

import net.jfdf.compiler.annotation.CompileWithExecute;
import net.jfdf.jfdf.mangement.*;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;
import net.jfdf.transformer.ExportClass;
import net.jfdf.transformer.NoTransform;
import net.jfdf.transformer.annotations.NoConstructor;

@ExportClass
@NoConstructor
public class ReferenceManager {
    private ReferenceManager() {}

    @FunctionWithArgs(
            name = "_jfdf>std>malloc",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Memory Allocate\"}],\"text\":\"\"}'}"
    )
    @NoTransform
    @CompileWithExecute
    public static void malloc() {
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));
        List deletedReferences = new List(new Variable("_jfdfRD", Variable.Scope.NORMAL));
        Variable referenceCount = new Variable("_jfdfRC", Variable.Scope.NORMAL);

        Variable pointer = new Variable("_jfdfRP", Variable.Scope.LOCAL);
        Variable deletedReferencesLength = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable deletedReferencePointer = new Variable("tmp0", Variable.Scope.LOCAL);

        If.Variable.NotEquals(deletedReferences.length(deletedReferencesLength), new CodeValue[]{ new Number().Set(0.0f) }, false);
            VariableControl.Set(
                    pointer,
                    deletedReferences.get(
                            deletedReferencePointer,
                            new Number().Set(1.0f)
                    )
            );

            Control.Return();
        If.End();

        referenceCountList.add(new Number().Set(0));
        VariableControl.Increment(referenceCount);
        VariableControl.Set(pointer, referenceCount);
    }

    @FunctionWithArgs(
            name = "_jfdf>std>salloc",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Smart Allocate (Game Variables)\"}],\"text\":\"\"}'}"
    )
    @NoTransform
    @CompileWithExecute
    public static void salloc(Variable varName) {
        Variable shouldAlloc = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable pointerVariable = new Variable("%var(" + varName.getName() + ")", Variable.Scope.NORMAL);
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));

        Variable returnVariable = new Variable("_jfdfRP", Variable.Scope.LOCAL);

        INumber false_ = new Number().Set(0.0f);
        INumber true_ = new Number().Set(1.0f);

        VariableControl.Set(shouldAlloc, false_);

        If.Variable.Exists(pointerVariable, true);
            VariableControl.Set(shouldAlloc, true_);
        If.End();

        If.Variable.Equals(pointerVariable, new CodeValue[]{ new Number().Set(0.0f) }, false);
            VariableControl.Set(shouldAlloc, true_);
        If.End();

        If.Variable.IsType(pointerVariable, Tags.VariableType.NUMBER, true);
            VariableControl.Set(shouldAlloc, true_);
        If.End();

        If.Variable.Equals(shouldAlloc, new CodeValue[]{ true_ }, false);
            Functions.Call("_jfdf>std>malloc");
            VariableControl.Set(pointerVariable, returnVariable);

            referenceCountList.set(returnVariable, new Number().Set(1));
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
    @NoTransform
    @CompileWithExecute
    public static void sallocl(Variable varName) {
        Variable shouldAlloc = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable pointerVariable = new Variable("%var(" + varName.getName() + ")", Variable.Scope.LOCAL);
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));

        List localPointers = new List(new Variable("_jfdfRL", Variable.Scope.LOCAL));
        Variable returnVariable = new Variable("_jfdfRP", Variable.Scope.LOCAL);

        INumber false_ = new Number().Set(0.0f);
        INumber true_ = new Number().Set(1.0f);

        VariableControl.Set(shouldAlloc, false_);

        If.Variable.Exists(pointerVariable, true);
            VariableControl.Set(shouldAlloc, true_);
        If.End();

        If.Variable.Equals(pointerVariable, new CodeValue[]{ new Number().Set(0.0f) }, false);
            VariableControl.Set(shouldAlloc, true_);
        If.End();

        If.Variable.IsType(pointerVariable, Tags.VariableType.NUMBER, true);
            VariableControl.Set(shouldAlloc, true_);
        If.End();

        If.Variable.Equals(shouldAlloc, new CodeValue[]{ true_ }, false);
            Functions.Call("_jfdf>std>malloc");
            VariableControl.Set(pointerVariable, returnVariable);

            referenceCountList.set(returnVariable, new Number().Set(100000));
            localPointers.add(returnVariable);
            Control.Return();
        If.End();

        VariableControl.Set(returnVariable, pointerVariable);
        referenceCountList.set(returnVariable, new Number().Set(100000));
    }

    @FunctionWithArgs(
            name = "_jfdf>std>free",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Free Memory\"}],\"text\":\"\"}'}"
    )
    @NoTransform
    @CompileWithExecute
    public static void free(Variable pointers) {
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));
        VariableControl.SortList(pointers, null, Tags.SortOrder.DESCENDING);

        List deletedReferences = new List(new Variable("_jfdfRD", Variable.Scope.NORMAL));
        Variable referenceCount = new Variable("_jfdfRC", Variable.Scope.NORMAL);

        Variable pointer = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable reference = new Variable("_jfdfR%var(tmp0)", Variable.Scope.NORMAL);

        Repeat.ForEach(pointer, pointers);
            VariableControl.Set(reference, new Number().Set(0.0f));

            If.Variable.Equals(pointer, new CodeValue[]{referenceCount}, false);
                referenceCountList.remove(pointer);
                
                VariableControl.Decrement(referenceCount);
                Control.Skip();
            If.End();

            deletedReferences.add(pointer);
        Repeat.End();
    }

    @FunctionWithArgs(
            name = "_jfdf>std>gc",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Collect Garbage\"}],\"text\":\"\"}'}"
    )
    @NoTransform
    @CompileWithExecute
    public static void collectGarbage() {
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));
        List freePointers = new List("var0");

        Variable referencePointer = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable referenceCounter = new Variable("tmp1", Variable.Scope.LOCAL);

        VariableControl.Set(referencePointer, new Number().Set(1.0f));
        Repeat.ForEach(referenceCounter, referenceCountList);
            If.Variable.Equals(referenceCounter, new CodeValue[]{ new Number().Set(0.0f) }, false);
                freePointers.add(referencePointer);
            If.End();

            VariableControl.Increment(referencePointer);
        Repeat.End();

        Functions.CallWithArgs("_jfdf>std>free", freePointers);
    }

    @FunctionWithArgs(
            name = "_jfdf>std>procStart",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Process Start\"}],\"text\":\"\"}'}"
    )
    @NoTransform
    @CompileWithExecute
    public static void processStart() {
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));
        List localPointers = new List(new Variable("_jfdfRL", Variable.Scope.LOCAL));

        Variable localPointer = new Variable("tmp0", Variable.Scope.LOCAL);
        Variable referenceUses = new Variable("tmp1", Variable.Scope.LOCAL);

        Repeat.ForEach(localPointer, localPointers);
        referenceCountList.set(
                localPointers,
                Number.Subtract(
                        Number.AsListValue(
                                referenceUses, Number.AsVariable(localPointer)
                        ), new Number().Set(100000.0f)
                )
        );
        Repeat.End();
    }

    @FunctionWithArgs(
            name = "_jfdf>std>procEnd",
            iconId = "honey_block",
            iconNbt = "Enchantments:[{id:\"minecraft:lure\",lvl:1s}],HideFlags:1,display:{Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Part of JFDF Standard Library\"}],\"text\":\"\"}'],Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Process End\"}],\"text\":\"\"}'}"
    )
    @NoTransform
    @CompileWithExecute
    public static void processEnd() {
        List referenceCountList = new List(new Variable("_jfdfRCL", Variable.Scope.NORMAL));
        List localPointers = new List(new Variable("_jfdfRL", Variable.Scope.LOCAL));

        Variable localPointer = new Variable("tmp0", Variable.Scope.LOCAL);

        Repeat.ForEach(localPointer, localPointers);
            referenceCountList.set(
                    localPointer,
                    Number.Subtract(
                            Number.AsListValue(
                                    referenceCountList, Number.AsVariable(localPointer)
                            ), new Number().Set(100000.0f)
                    )
            );
        Repeat.End();

        Functions.Call("_jfdf>std>gc");
        Control.End();
    }
}
