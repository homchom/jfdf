package net.jfdf.compiler.util;

import net.jfdf.compiler.data.instruction.InstructionData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodsManager {
    private final static Map<Class<?>, Map<String, List<InstructionData>>> methodInstructionDataLists = new HashMap<>();
    private final static List<Class<?>> classesWithClinit = new ArrayList<>();

    public static void addMethodInstructions(Class<?> class_, String methodName, String methodDescriptor, List<InstructionData> instructionDataList) {
        if(!methodInstructionDataLists.containsKey(class_)) {
            methodInstructionDataLists.put(class_, new HashMap<>());
        }

        methodInstructionDataLists.get(class_).put(methodName + methodDescriptor, instructionDataList);
    }

    public static List<InstructionData> getMethodInstructions(Class<?> class_, String methodName, String methodDescriptor) {
        return methodInstructionDataLists.get(class_).get(methodName + methodDescriptor);
    }

    public static boolean hasStaticInitializer(Class<?> class_) {
        return classesWithClinit.contains(class_);
    }

    public static void enableHasStaticInitializer(Class<?> class_) {
        classesWithClinit.add(class_);
    }
}
