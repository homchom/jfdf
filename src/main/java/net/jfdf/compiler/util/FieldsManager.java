package net.jfdf.compiler.util;

import net.jfdf.compiler.JFDFCompiler;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FieldsManager {
    private static final Map<Class<?>, Map<String, Integer>> fieldIndices = new HashMap<>();
    private static final Map<Class<?>, Map<String, Object>> fieldDefaultValues = new HashMap<>();

    public static void analyzeClassFields(Class<?> class_) {
        if(fieldIndices.containsKey(class_)) {
            return;
        }

        JFDFCompiler.analyzeClass(class_);

        Map<String, Integer> classFieldIndices;
        if(class_.getSuperclass() != Object.class && class_.getSuperclass() != Enum.class) {
            analyzeClassFields(class_.getSuperclass());
            classFieldIndices = new HashMap<>(fieldIndices.get(class_.getSuperclass()));
        } else {
            classFieldIndices = new HashMap<>();
        }

        int firstFieldIndex = classFieldIndices.size();
        firstFieldIndex += (Modifier.isFinal(class_.getModifiers())
                && (class_.getSuperclass() == Object.class || class_.getSuperclass() == Enum.class)) ? 0 : 1;

        int i = 1;
        for(Field field : class_.getDeclaredFields()) {
            if(!Modifier.isStatic(field.getModifiers())) {
                classFieldIndices.put(field.getName(), firstFieldIndex + i);

                i++;
            }
        }

        fieldIndices.put(class_, classFieldIndices);
    }

    public static int getFieldIndex(Class<?> class_, String name) {
        if(!fieldIndices.containsKey(class_)) analyzeClassFields(class_);

        return fieldIndices.get(class_).get(name);
    }

    public static boolean hasFieldWithDefaultValue(Class<?> class_) {
        if(!fieldDefaultValues.containsKey(class_)) {
            fieldDefaultValues.put(class_, new HashMap<>());
        }

        return fieldDefaultValues.get(class_).values().stream().anyMatch(Objects::nonNull);
    }

    public static Map<String, Object> getClassDefaultValues(Class<?> class_) {
        if(!fieldDefaultValues.containsKey(class_)) {
            fieldDefaultValues.put(class_, new HashMap<>());
        }

        return fieldDefaultValues.get(class_);
    }

    public static void setFieldDefaultValue(Class<?> class_, String name, Object defaultValue) {
        if(!fieldDefaultValues.containsKey(class_)) {
            fieldDefaultValues.put(class_, new HashMap<>());
        }

        fieldDefaultValues.get(class_).put(name, defaultValue);
    }
}
