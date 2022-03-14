package net.jfdf.transformer.util;

import net.jfdf.jfdf.mangement.VariableControl;
import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.INumber;
import net.jfdf.jfdf.values.List;
import net.jfdf.jfdf.values.Variable;

public class TransformedList extends Variable {
    public TransformedList(Variable list) {
        super(list);
        showError();
    }

    public TransformedList(String name) {
        super(name, Scope.LOCAL);
        showError();
    }

    public TransformedList add(CodeValue... values) {
        showError();
        return this;
    }

    public TransformedList add(Object... values) {
        showError();
        return this;
    }

    public TransformedList addAll(Variable... lists) {
        showError();
        return this;
    }

    public TransformedList remove(CodeValue... values) {
        showError();
        return this;
    }

    public TransformedList remove(Object... value) {
        showError();
        return this;
    }

    public TransformedList removeAt(int... indices) {
        showError();
        return this;
    }

    public TransformedList removeAt(INumber... indices) {
        showError();
        return this;
    }

    public Object get(int index) {
        showError();
        return null;
    }

    public Object get(INumber index) {
        showError();
        return null;
    }

    public TransformedList set(INumber index, CodeValue value) {
        showError();
        return this;
    }

    public TransformedList set(int index, Object value) {
        showError();
        return this;
    }

    public int size() {
        showError();
        return -1;
    }

    private void showError() {
        throw new RuntimeException("You can only use this in a transformable code.");
    }
}
