package net.jfdf.compiler.data.stack;

import net.jfdf.compiler.util.ReferenceUtils;
import net.jfdf.jfdf.values.Variable;
import org.objectweb.asm.Opcodes;

import java.util.*;

public class Stack extends ArrayList<IStackValue> {
    private final List<TempValueData> tempValueUses = new ArrayList<>();

    @Override
    public boolean add(IStackValue stackValue) {
        if(stackValue instanceof VariableStackValue
                || stackValue instanceof ReferencedStackValue) {
            String variableName = ((Variable) stackValue.getTransformedValue()).getName();

            if(variableName.startsWith("_jco") || variableName.startsWith("_functionr")) {
                boolean tempValueDataFound = false;

                for(TempValueData tempValueData : tempValueUses) {
                    if(tempValueData.variable.getName().equals(variableName)) {
                        tempValueData.uses += 1;
                        tempValueDataFound = true;

                        break;
                    }
                }

                if(!tempValueDataFound) {
                    tempValueUses.add(new TempValueData(stackValue));
                }
            }
        }

        return super.add(stackValue);
    }

    @Override
    public IStackValue remove(int index) {
        IStackValue removedValue = super.remove(index);

        if(removedValue instanceof VariableStackValue
                || removedValue instanceof ReferencedStackValue) {
            String variableName = ((Variable) removedValue.getTransformedValue()).getName();

            if(variableName.startsWith("_jco") || variableName.startsWith("_functionr")) {
                for(TempValueData tempValueData : tempValueUses) {
                    if(tempValueData.variable.getName().equals(variableName)) {
                        tempValueData.uses -= 1;

                        break;
                    }
                }
            }
        }

        return removedValue;
    }

    public void onVisitInsn(int instructionOpcode) {
        Iterator<TempValueData> iterator = tempValueUses.iterator();

        while(iterator.hasNext()) {
            TempValueData tempValueData = iterator.next();
            String variableName = tempValueData.variable.getName();

            if(!variableName.startsWith("_jco")) {
                if(!variableName.startsWith("_functionr")
                        && ((instructionOpcode >= Opcodes.IRETURN && instructionOpcode <= Opcodes.RETURN) || instructionOpcode < 0)) {
                    iterator.remove();
                    return;
                }
            }

            if(tempValueData.uses == 0) {
                ReferenceUtils.decrementIfReference(tempValueData.descriptor, tempValueData.variable);

                iterator.remove();
            }
        }
    }

    private static class TempValueData {
        public Variable variable;
        public String descriptor;
        public int uses = 1;

        public TempValueData(IStackValue variableStackValue) {
            this.variable = (Variable) variableStackValue.getTransformedValue();
            this.descriptor = variableStackValue.getDescriptor();
        }
    }
}
