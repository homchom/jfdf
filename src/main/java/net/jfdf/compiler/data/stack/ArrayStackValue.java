package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.blocks.SetVariableBlock;
import net.jfdf.jfdf.mangement.CodeManager;
import net.jfdf.jfdf.mangement.Repeat;
import net.jfdf.jfdf.mangement.VariableControl;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;
import net.jfdf.transformer.util.NumberMath;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayStackValue extends ReferencedStackValue {
    private final String descriptor;
    private final List<CodeValue> dataList;

    public ArrayStackValue(String descriptor, IStackValue length, String methodName, int blockOperatorIndex) {
        super(methodName, blockOperatorIndex);

        this.descriptor = "[" + descriptor;

        if(length instanceof NumberStackValue) {
            int arrayLength = ((NumberStackValue) length).getJavaValue().intValue();

            // Makes a new array
            dataList = Arrays.asList(new CodeValue[arrayLength + (int) Math.max(1, Math.ceil(arrayLength / 26.0f))]);

            // Fills the array with 0s then sets first block argument to reference's variable
            Collections.fill(dataList, new Number().Set(0));
            dataList.set(0, reference);

            // Add the blocks
            CodeManager.instance.addCodeBlock(new SetVariableBlock("CreateList").SetItems(dataList.subList(0, Math.min(27, dataList.size()))));
            for (int i = 27; i < dataList.size(); i += 27) {
                dataList.set(i, reference);
                CodeManager.instance.addCodeBlock(new SetVariableBlock("AppendValue").SetItems(dataList.subList(i, Math.min(i + 27, dataList.size()))));
            }
            return;
        }

        VariableControl.CreateList(reference);
        Repeat.MultipleTimes(null, (INumber) length.getTransformedValue());
        VariableControl.AppendValue(reference, new Number().Set(0));
        Repeat.End();

        dataList = null;
    }

    public ArrayStackValue(Variable array, String methodName, int blockOperatorIndex) {
        super(methodName, blockOperatorIndex);
        this.descriptor = "[Ljava/lang/Object;";

        VariableControl.Set(reference, array);
        dataList = null;
    }

    public void set(IStackValue indexValue, CodeValue value) {
        if(dataList != null && indexValue instanceof NumberStackValue
                && !(value instanceof Variable) && !(value instanceof GameValue)) {
            int index = ((NumberStackValue) indexValue).getJavaValue().intValue();

            dataList.set(index + (int)Math.floor(index / 26.0f) + 1, value);
        } else {
            VariableControl.SetListValue(
                    reference,
                    NumberMath.add((INumber) indexValue.getTransformedValue(), new Number().Set(1)),
                    value
            );
        }
    }

    @Override
    public String getDescriptor() {
        return this.descriptor;
    }
}
