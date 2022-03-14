package net.jfdf.transformer;

import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;

@Deprecated(forRemoval = true)
public class JFDFValueInstances {
    public static Text setText(Text text, Text text2) {
        return text2;
    }

    public static Number setNumber(Number number, Number number2) {
        return number2;
    }

    public static Variable newVariable(Text name, Variable.Scope scope) {
        return new Variable(name.Get(), scope);
    }

    public static List newList(Text variableName, CodeValue... codeValues) {
        return new List(variableName.Get(), codeValues);
    }

    public static List newList(Variable variable) {
        return new List(variable);
    }

    public static Item newItem(CodeValue itemId) {
        return new Item(((Text) itemId).Get());
    }

    public static Item newItem(CodeValue itemId, CodeValue itemCount) {
        return new Item(((Text) itemId).Get(), Integer.parseInt(((Number)itemCount).getValue()));
    }

    public static Item newItem(CodeValue itemId, CodeValue itemCount, CodeValue nbt) {
        return new Item(((Text) itemId).Get(), Integer.parseInt(((Number)itemCount).getValue()), ((Text) nbt).Get());
    }
}
