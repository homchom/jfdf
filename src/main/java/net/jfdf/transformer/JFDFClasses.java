package net.jfdf.transformer;

import net.jfdf.jfdf.blocks.CallProcessBlock;
import net.jfdf.jfdf.blocks.IfVariableBlock;
import net.jfdf.jfdf.blocks.ProcessBlock;
import net.jfdf.jfdf.mangement.*;
import net.jfdf.jfdf.mangement.Process;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;
import org.objectweb.asm.Type;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Stream;

@Deprecated(forRemoval = true)
public class JFDFClasses {
    public static void Consume(Object object) {
    }

    public static void SetVarValue(List class_, CodeValue value, int varIndex) {
        class_.set(new Number().Set(varIndex), value);
    }

    public static List GetInitList(int argIndex, int fieldsCount) {
        CodeValue[] listValues = new CodeValue[fieldsCount];
        Number number = new Number().Set(0.0f);

        for (int i = 0; i < fieldsCount; i++) {
            listValues[i] = number;
        }

        return new List("%var(_function_jfdf@init_arg" + argIndex + ")", listValues);
    }

    public static boolean Equals(CodeValue a, CodeValue b, boolean not) {
        if(not) {
            If.Variable.NotEquals(a, new CodeValue[]{b}, false);
        } else {
            If.Variable.Equals(a, new CodeValue[]{b}, false);
        }

        return true;
    }

    public static List getGlobalVar(String className, String varName) {
        try {
            Field field = Class.forName(className.replace('/', '.')).getDeclaredField(varName);

            Saved isSaved = field.getAnnotation(Saved.class);
            VariablePrefix prefixAnnotation = field.getAnnotation(VariablePrefix.class);
            VariableSuffix suffixAnnotation = field.getAnnotation(VariableSuffix.class);
            VariableOverride overrideAnnotation = field.getAnnotation(VariableOverride.class);

            String newVarName = varName;

            newVarName = overrideAnnotation != null ? overrideAnnotation.name() : "";
            newVarName = (prefixAnnotation != null ? prefixAnnotation.prefix() : "") + newVarName;
            newVarName = newVarName + (suffixAnnotation != null ? suffixAnnotation.suffix() : "");

            if(isSaved != null) {
                return new List(new Variable("_jfdf>" + className + ">" + newVarName, Variable.Scope.SAVED));
            } else {
                return new List(new Variable("_jfdf>" + className + ">" + newVarName, Variable.Scope.NORMAL));
            }
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setGlobalVar(CodeValue value, String className, String varName) {
        VariableControl.Set(getGlobalVar(className, varName), value);
    }

    public static void callMethod(String className, String methodName, String realDescriptor, String modifiedDescriptor, CodeValue... codeValues) {
        try {
            Class<?> methodClass =  Class.forName(className.replace('/', '.'));

            String newModifiedDescriptor = modifiedDescriptor;
            if(methodName.equals("jfdf@init")) {
                newModifiedDescriptor = newModifiedDescriptor.replace(")", "Lnet/jfdf/jfdf/values/CodeValue;)");
            } else if(methodClass.getAnnotation(ExportClass.class) != null) {
                newModifiedDescriptor = newModifiedDescriptor.replace("(", "(Lnet/jfdf/jfdf/values/List;");
            }

            Method callMethod;

            try {
                callMethod = methodClass.getDeclaredMethod(methodName, readDescriptor(newModifiedDescriptor));
            } catch (NoSuchMethodException e) {
                callMethod = methodClass.getDeclaredMethod(methodName, readDescriptor(modifiedDescriptor));
            }

            if(callMethod.getAnnotation(PlayerEvent.class) != null) {
                throw new IllegalStateException("Calling a player event is not allowed.");
            } else if(callMethod.getAnnotation(Function.class) != null) {
                Functions.Call("_jfdf>" + className + ">" + methodName + ">" + realDescriptor);
            } else if(callMethod.getAnnotation(FunctionWithArgs.class) != null) {
                Functions.CallWithArgs("_jfdf>" + className + ">" + methodName + ">" + realDescriptor, codeValues);
            } else if(callMethod.getAnnotation(Process.class) != null) {
                CodeManager.instance.addCodeBlock(new CallProcessBlock("_jfdf>" + className + ">" + methodName + ">" + realDescriptor));
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static CodeValue[] newArray(Number number) {
        return new CodeValue[Integer.parseInt(number.getValue())];
    }

    public static void storeArrayValue(CodeValue[] array, Number number, CodeValue value) {
        array[Integer.parseInt(number.getValue())] = value;
    }

    private static Class<?>[] readDescriptor(String desc) {
        return Arrays.stream(Type.getMethodType(desc).getArgumentTypes()).map(type -> {
            try {
                return Class.forName(type.getClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }).toArray(Class[]::new);
    }
}
