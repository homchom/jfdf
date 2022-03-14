package net.jfdf.compiler.visitor;

import net.jfdf.compiler.annotation.MethodFallback;
import net.jfdf.compiler.annotation.NoConstructors;
import net.jfdf.compiler.util.FieldsManager;
import net.jfdf.compiler.util.MethodWrapper;
import net.jfdf.compiler.util.MethodsManager;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class CompilerClassVisitor extends ClassVisitor {
    private final Class<?> class_;

    public CompilerClassVisitor(ClassVisitor classVisitor, Class<?> class_) {
        super(Opcodes.ASM9, classVisitor);

        this.class_ = class_;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        // Checks if the class is enum (Enums on Java language always have Enum class as super class)
        if(class_.getSuperclass() == Enum.class) {
            // Checks if the method is valueOf (Auto-generated method by javac)
            if(name.equals("valueOf")
                    && descriptor.equals("(Ljava/lang/String;)L" + Type.getInternalName(class_) + ";")) {
                // Removes the method, this method will be replaced when used
                return null;
            }
        }

        try {
            MethodWrapper methodWrapper = MethodWrapper.getWrapper(class_, name, descriptor);

            // Checks if method has fallback annotation
            if(methodWrapper.getAnnotation(MethodFallback.class) != null) {
                // Removes this method, this method will be replaced by fallback's method when used
                return null;
            }

            // Checks if method is a constructor and class does not compile constructors
            if(methodWrapper.isConstructor() && class_.getAnnotation(NoConstructors.class) != null) {
                // Removes this constructor
                return null;
            }

            // Check if method is lambda
            if(name.startsWith("lambda$")) {
                // Lambda is not supported, so it doesn't compile
                return null;
            }

            // Compiles the method into DF code
            return new CompilerMethodVisitor(
                    super.visitMethod(access, name, descriptor, signature, exceptions),
                    class_,
                    methodWrapper
            );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Something went wrong.", e);
        }
    }

    @Override
    public void visitEnd() {
        if(FieldsManager.hasFieldWithDefaultValue(class_)
                && !MethodsManager.hasStaticInitializer(class_)) {
            CompilerMethodVisitor compilerMethodVisitor = new CompilerMethodVisitor(
                    super.visitMethod(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null),
                    class_,
                    new MethodWrapper()
            );

            compilerMethodVisitor.visitCode();
            compilerMethodVisitor.visitEnd();
        }

        super.visitEnd();
    }
}
