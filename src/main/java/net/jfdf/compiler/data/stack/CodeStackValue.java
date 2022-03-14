package net.jfdf.compiler.data.stack;

import net.jfdf.compiler.util.MethodWrapper;
import net.jfdf.jfdf.values.CodeValue;
import org.objectweb.asm.Type;

import java.lang.reflect.InvocationTargetException;

public class CodeStackValue implements IStackValue {
    private final Class<?> class_;

    private CodeValue codeValue;

    public CodeStackValue(String type) {
        try {
            this.class_ = Class.forName(type.replace('/', '.'));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Something went wrong.", e);
        }
    }

    public void callConstructor(String descriptor, Object... arguments) {
        try {
            codeValue = (CodeValue) MethodWrapper.getWrapper(class_, "<init>", descriptor).invoke(null, arguments);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Something went wrong.", e);
        }
    }

    public void invokeMethod(String methodName, String methodDescriptor, Object... arguments) {
        if(!methodDescriptor.endsWith(")V") && !methodDescriptor.endsWith(")L" + Type.getInternalName(class_) + ";")) {
            throw new IllegalStateException("Calling code value's method with a return value is not allowed.");
        }

        try {
            MethodWrapper.getWrapper(class_, methodName, methodDescriptor).invoke(codeValue, arguments);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Something went wrong.", e);
        }
    }

    @Override
    public CodeValue getTransformedValue() {
        return codeValue;
    }

    @Override
    public String getDescriptor() {
        return Type.getDescriptor(class_);
    }
}
