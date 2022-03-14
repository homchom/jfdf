package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.values.CodeValue;

public interface IStackValue {
    /**
     * This converts java stack value
     * to JFDF code value which can be used
     * to call actions
     *
     * @return Converted value.
     */
    public CodeValue getTransformedValue();
    public String getDescriptor();
}
