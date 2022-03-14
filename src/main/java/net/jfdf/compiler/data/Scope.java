package net.jfdf.compiler.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scope {
    private final ScopeVariable[] scopeVariables;
    private List<ScopeVariable> unknownScopeVariables;

    public Scope(ScopeVariable[] scopeVariables) {
        this.scopeVariables = scopeVariables;
    }

    public void addUnknownScopeVariable(ScopeVariable scopeVariable) {
        if(unknownScopeVariables == null) unknownScopeVariables = new ArrayList<>();
        if(!unknownScopeVariables.contains(scopeVariable)) unknownScopeVariables.add(scopeVariable);
    }

    public void end() {
        collect(Arrays.asList(this.scopeVariables));

        if(unknownScopeVariables != null) {
            collect(unknownScopeVariables);
        }
    }

    private void collect(List<ScopeVariable> scopeVariables) {
        for (ScopeVariable scopeVariable : scopeVariables) {
            if(scopeVariable.type.startsWith("L")
                    && !scopeVariable.type.startsWith("Lnet/jfdf/jfdf/values/")) {

            } else if(scopeVariable.type.startsWith("[")) {

            }
        }
    }
}
