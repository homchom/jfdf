package net.jfdf.compiler.visitor;

import net.jfdf.compiler.annotation.NoCompile;
import net.jfdf.compiler.data.instruction.InstructionData;
import net.jfdf.compiler.util.FieldsManager;
import net.jfdf.compiler.util.MethodWrapper;
import net.jfdf.compiler.util.MethodsManager;
import org.objectweb.asm.*;

import java.util.*;

public class CompilerClassAnalyzer extends ClassVisitor {
    private final Map<String, Object> fieldDefaultValues = new HashMap<>();
    private final Class<?> class_;

    public CompilerClassAnalyzer(ClassVisitor classVisitor, Class<?> class_) {
        super(Opcodes.ASM9, classVisitor);

        this.class_ = class_;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        fieldDefaultValues.put(name, value);

        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        try {
            MethodWrapper methodWrapper = MethodWrapper.getWrapper(class_, name, descriptor);

            if(methodWrapper.getAnnotation(NoCompile.class) == null) {
                List<InstructionData> instructionDataList = new ArrayList<>();
                MethodsManager.addMethodInstructions(class_, name, descriptor, instructionDataList);

                // Analyzes class method
                return new CompilerMethodAnalyzer(
                        super.visitMethod(access, name, descriptor, signature, exceptions),
                        instructionDataList
                );
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Something went wrong.", e);
        }

        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        FieldsManager.analyzeClassFields(class_);

        super.visitEnd();
    }
}
