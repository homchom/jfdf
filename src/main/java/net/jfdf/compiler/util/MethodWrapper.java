package net.jfdf.compiler.util;

import org.objectweb.asm.Type;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class MethodWrapper {
    private final Method method;
    private final Constructor<?> constructor;
    private final boolean isStaticInitalizer;

    private final String descriptor;

    /**
     * This is used for static initializer
     */
    public MethodWrapper() {
        method = null;
        constructor = null;
        isStaticInitalizer = true;

        descriptor = "()V";
    }

    public MethodWrapper(Method method) {
        this.method = method;
        this.constructor = null;
        isStaticInitalizer = false;

        this.descriptor = Type.getMethodDescriptor(this.method);
    }

    public MethodWrapper(Constructor<?> constructor) {
        this.method = null;
        this.constructor = constructor;
        isStaticInitalizer = false;

        this.descriptor = Type.getConstructorDescriptor(this.constructor);
    }

    public static MethodWrapper getWrapper(Class<?> class_, String methodName, String methodDescriptor) throws NoSuchMethodException {
        // Converts descriptor to parameter types of the method
        Class<?>[] parameterTypes = Arrays.stream(Type.getArgumentTypes(methodDescriptor))
                .map(type -> {
                    try {
                        String typeInternalName = type.getInternalName().replace('/', '.');

                        return switch (typeInternalName) {
                            case "Z" -> boolean.class;
                            case "B" -> byte.class;
                            case "S" -> short.class;
                            case "I" -> int.class;
                            case "J" -> long.class;
                            case "F" -> float.class;
                            case "D" -> double.class;
                            case "C" -> char.class;
                            default -> Class.forName(typeInternalName);
                        };
                    } catch (Exception e) {
                        throw new RuntimeException("Something went wrong.", e);
                    }
                }).toArray(Class<?>[]::new);

        if(methodName.equals("<init>")) {
            // Gets reflection constructor from constructor's
            // class and wraps it using MethodWrapper
            return new MethodWrapper(class_.getDeclaredConstructor(parameterTypes));
        } else if(methodName.equals("<clinit>")) {
            return new MethodWrapper();
        } else {
            // Gets reflection method from method class and
            // wraps it using MethodWrapper
            return new MethodWrapper(class_.getDeclaredMethod(methodName, parameterTypes));
        }
    }

    public boolean isConstructor() {
        return constructor != null;
    }

    public boolean isStaticInitializer() {
        return isStaticInitalizer;
    }
    
    public boolean isMember() {
        if(method != null) return !Modifier.isStatic(method.getModifiers());
        else if(constructor != null) return true;
        else return false;
    }

    public String getName() {
        if(method != null) return method.getName();
        else if(constructor != null) return "<init>";
        else return "<clinit>";
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        if(method != null) return method.getAnnotation(annotationClass);
        else if(constructor != null) return constructor.getAnnotation(annotationClass);
        else return null;
    }

    public int getModifiers() {
        if(method != null) return method.getModifiers();
        else if(constructor != null) return constructor.getModifiers();
        else return Modifier.STATIC;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setAccessible(boolean accessible) {
        if(method != null) method.setAccessible(accessible);
        else if(constructor != null) constructor.setAccessible(accessible);
        else throw new IllegalStateException("Couldn't set static initializer accessible.");
    }

    public Object invoke(Object obj, Object... arguments) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        if(method != null) return method.invoke(obj, arguments);
        else if(constructor != null) return constructor.newInstance(arguments);
        else throw new IllegalStateException("Couldn't invoke static initializer.");
    }
}
