package net.jfdf.jfdf.values;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.jfdf.jfdf.mangement.CodeManager;
import net.jfdf.jfdf.mangement.VariableControl;
import net.jfdf.jfdf.values.Tags.SearchOrder;

public class List extends Variable {
    public static ListMode listMode = ListMode.BOTH;

    private int getIndex = 0;
    private int indexOfIndex = 0;
    private int addedElementsSinceLastLength = 0;
    private GetLengthState getLengthState = GetLengthState.NO_LENGTH;

    public List() {
        super("list" + new Random().nextInt(1000000), Scope.LOCAL);
        VariableControl.CreateList(this);
    }
    
    public List(Variable list) {
        super(list);
    }

    public List(String name) {
        super(name, Scope.LOCAL);
        VariableControl.CreateList(this);
    }

    public List(Scope scope) {
        super("list" + new Random().nextInt(1000000), scope);
        VariableControl.CreateList(this);
    }

    public List(String name, Scope scope) {
        super(name, scope);
        VariableControl.CreateList(this);
    }

    public List(CodeValue... contents) {
        super("list" + new Random().nextInt(1000000), Scope.LOCAL);
        if(contents.length >= 27) {throw new IllegalArgumentException("You can't create a list with more than 26 elements in one code block.");}
        VariableControl.CreateList(this, contents);
    }

    public List(String name, CodeValue... contents) {
        super(name, Scope.LOCAL);
        if(contents.length >= 27) {throw new IllegalArgumentException("You can't create a list with more than 26 elements in one code block.");}
        VariableControl.CreateList(this, contents);
    }

    public List(Scope scope, CodeValue... contents) {
        super("list" + new Random().nextInt(1000000), scope);
        if(contents.length >= 27) {throw new IllegalArgumentException("You can't create a list with more than 26 elements in one code block.");}
        VariableControl.CreateList(this, contents);
    }

    public List(String name, Scope scope, CodeValue... contents) {
        super(name, scope);
        if(contents.length >= 27) {throw new IllegalArgumentException("You can't create a list with more than 26 elements in one code block.");}
        VariableControl.CreateList(this, contents);
    }
    
    public List addZeros(int zerosAmount) {
        CodeValue[] fillArray = new CodeValue[26];
        Arrays.fill(fillArray, new Number().Set(0.0f));

        for (int i = 0; i < zerosAmount; i += 26) {
            if((zerosAmount - i) < 26) {
                fillArray = new CodeValue[zerosAmount - i];
                Arrays.fill(fillArray, new Number().Set(0.0f));
            }

            add(fillArray);
        }

        return this;
    }

    public List add(CodeValue... values) {
        if(values.length >= 27) {throw new IllegalArgumentException("You can't add more than 26 elements in one code block.");}
        VariableControl.AppendValue(this, values);
        return this;
    }

    public List add(Object value) {
        if(value instanceof CodeValue) {
            add(new CodeValue[] {(CodeValue) value});
        }

        return this;
    }

    public List addAll(Variable... lists) {
        if(lists.length >= 27) {throw new IllegalArgumentException("You can't add more than 26 lists in one code block.");}
        VariableControl.AppendList(this, Arrays.stream(lists).map(List::new).toArray(List[]::new));
        if(listMode == ListMode.CPU_EFFICIENT && getLengthState != GetLengthState.NO_LENGTH) {
            Variable lengthVariable = CodeManager.instance.getVariable(getName() + "_length", Scope.LOCAL);
            ArrayList<Variable> lengthVariables = new ArrayList<>();

            for (Variable listVariable : lists) {
                Variable listLengthVariable = CodeManager.instance.getVariable(listVariable.getName() + "_length", Scope.LOCAL);
                VariableControl.ListLength(listLengthVariable, new List(listVariable));
                lengthVariables.add(listLengthVariable);
            }

            VariableControl.Increment(lengthVariable, lengthVariables.toArray(new INumber[0]));
        } else {
            getLengthState = GetLengthState.NEEDS_UPDATE_ADD_ALL;
        }
        return this;
    }

    public List remove(CodeValue... values) {
        if(values.length >= 27) {throw new IllegalArgumentException("You can't add more than 26 elements in one code block.");}
        VariableControl.RemoveListValue(this, values);
        return this;
    }

    public List remove(Object value) {
        if(value instanceof CodeValue) {
            remove(new CodeValue[] {(CodeValue) value});
        }

        return this;
    }

    public List removeAt(INumber... indices) {
        VariableControl.RemoveListIndex(this, indices);

        return this;
    }

    public Variable get(Variable valueVariable, INumber index) {
        VariableControl.GetListValue(valueVariable, this, index);
        return new List(valueVariable);
    }

    public List get(INumber index) {
        Variable valueVariable = CodeManager.instance.getVariable(getName() + "_value" + getIndex, Scope.LOCAL);
        VariableControl.GetListValue(valueVariable, this, index);
        getIndex += 1;
        return new List(valueVariable);
    }

    public List get(int index) {return get(Number.New().Set(index));}

    public void set(INumber index, CodeValue value) {
        VariableControl.SetListValue(this, index, value);
    }

    public Variable length() {
        Variable valueVariable = CodeManager.instance.getVariable(getName() + "_length", Scope.LOCAL);
        if(getLengthState == GetLengthState.NO_LENGTH 
            || (getLengthState == GetLengthState.NEEDS_UPDATE_ADD_ALL && listMode != ListMode.CPU_EFFICIENT)
            || (getLengthState == GetLengthState.NEEDS_UPDATE_ADD && listMode == ListMode.LESS_SPACE)) {
            VariableControl.ListLength(valueVariable, this);
            getLengthState = GetLengthState.FOUND;
            addedElementsSinceLastLength = 0;
        } else if((getLengthState == GetLengthState.NEEDS_UPDATE_ADD && listMode != ListMode.LESS_SPACE)) {
            VariableControl.Increment(valueVariable, Number.New().Set(addedElementsSinceLastLength));
            addedElementsSinceLastLength = 0;
        }
        return valueVariable;
    }

    public Variable size() {return length();}

    public Variable length(Variable valueVariable) {
        VariableControl.ListLength(valueVariable, this);
        return valueVariable;
    }

    public Variable indexOf(CodeValue value) {
        Variable valueVariable = CodeManager.instance.getVariable(getName() + "_index" + indexOfIndex, Scope.LOCAL);
        VariableControl.GetValueIndex(valueVariable, this, value, SearchOrder.ASCENDING__FIRST_INDEX_);
        indexOfIndex += 1;
        return valueVariable;
    }

    public Variable lastIndexOf(CodeValue value) {
        Variable valueVariable = CodeManager.instance.getVariable(getName() + "_index" + indexOfIndex, Scope.LOCAL);
        VariableControl.GetValueIndex(valueVariable, this, value, SearchOrder.DESCENDING__LAST_INDEX_);
        indexOfIndex += 1;
        return valueVariable;
    }

    public Variable indexOf(Variable valueVariable, CodeValue value) {
        VariableControl.GetValueIndex(valueVariable, this, value, SearchOrder.ASCENDING__FIRST_INDEX_);
        return valueVariable;
    }

    public Variable lastIndexOf(Variable valueVariable, CodeValue value) {
        VariableControl.GetValueIndex(valueVariable, this, value, SearchOrder.DESCENDING__LAST_INDEX_);
        return valueVariable;
    }

    public List randomize() {
        VariableControl.RandomizeList(this, null);
        return this;
    }

    @Override
    public String toString() {
        return "List{" +
                "name='" + getName() + '\'' +
                ", scope=" + getScope() +
                '}';
    }

    private enum GetLengthState {
        NO_LENGTH,
        NEEDS_UPDATE_ADD,
        NEEDS_UPDATE_ADD_ALL,
        FOUND
    }

    public enum ListMode {
        CPU_EFFICIENT,
        LESS_SPACE,
        BOTH
    }
}