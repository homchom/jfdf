package net.jfdf.compiler.visitor;

import net.jfdf.compiler.JFDFCompiler;
import net.jfdf.compiler.annotation.CompileWithExecute;
import net.jfdf.compiler.annotation.MethodFallback;
import net.jfdf.compiler.data.instruction.InstructionData;
import net.jfdf.compiler.data.instruction.JumpInstructionData;
import net.jfdf.compiler.data.instruction.TypeInstructionData;
import net.jfdf.compiler.data.stack.*;
import net.jfdf.compiler.library.References;
import net.jfdf.compiler.util.FieldsManager;
import net.jfdf.compiler.util.MethodWrapper;
import net.jfdf.compiler.util.MethodsManager;
import net.jfdf.compiler.util.ReferenceUtils;
import net.jfdf.jfdf.blocks.*;
import net.jfdf.jfdf.mangement.*;
import net.jfdf.jfdf.mangement.Process;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;
import net.jfdf.transformer.Saved;
import net.jfdf.transformer.util.NumberMath;
import org.objectweb.asm.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class CompilerMethodVisitor extends MethodVisitor {
    private final Class<?> class_;
    private final MethodWrapper method;

    private int blockOperationIndex = 0;
    private final List<IStackValue> stack = new ArrayList<>();

    private int lineNumber = -1;
    private int instructionIndex = -1;
    private int labelInstructionIndex = -1;

    private final List<InstructionData> instructionDataList;
    private final Map<Integer, String> variableDescriptors = new HashMap<>();

    private final List<Integer> startRepeatBracketIndices = new ArrayList<>();
    private final List<Integer> endBracketIndices = new ArrayList<>();

    private boolean isThread = false;
    private boolean compileWithExecute = false;

    public CompilerMethodVisitor(MethodVisitor methodVisitor, Class<?> class_, MethodWrapper method) {
        super(Opcodes.ASM9, methodVisitor);

        this.class_ = class_;
        this.method = method;

        this.instructionDataList = MethodsManager.getMethodInstructions(class_, method.getName(), method.getDescriptor());
    }

    @Override
    public void visitCode() {
        if(method.getAnnotation(PlayerEvent.class) != null) {
            CodeManager.instance.addCodeBlocks(
                    new PlayerEventBlock(
                            method.getAnnotation(PlayerEvent.class)
                                    .eventType()
                                    .getJSONValue()
                    ), new ArrayList<>()
            );

            isThread = true;
            Functions.Call("_jfdf>std>procStart");

            if(method.getAnnotation(PlayerEvent.class).eventType() == PlayerEventBlock.Event.JOIN) {
                If.Variable.Equals(new GameValue(GameValue.Value.TOTAL_PLAYER_COUNT), new CodeValue[]{ new Number().Set(1) }, false);
                Functions.Call("_jfdf>init");
                If.End();
            }
        } else if(method.getAnnotation(Process.class) != null) {
            Process processData = method.getAnnotation(Process.class);

            CodeManager.instance.addCodeBlocks(
                    new ProcessBlock(
                            processData.name()
                    ).SetTags(
                            new Tag(
                                    "Is Hidden",
                                    processData.isHidden() ? "True" : "False"
                            )
                    ), new ArrayList<>()
            );

            isThread = true;
            Functions.Call("_jfdf>std>procStart");
        } else if(method.getAnnotation(Function.class) != null) {
            Function functionData = method.getAnnotation(Function.class);

            CodeManager.instance.addCodeBlocks(
                    new FunctionBlock(
                            functionData.name()
                    ).SetTags(
                            new Tag(
                                    "Is Hidden",
                                    functionData.isHidden() ? "True" : "False"
                            )
                    ), new ArrayList<>()
            );
        } else if(method.getAnnotation(FunctionWithArgs.class) != null) {
            FunctionWithArgs functionData = method.getAnnotation(FunctionWithArgs.class);

            CodeManager.instance.addCodeBlocks(
                    new FunctionBlock(
                            functionData.name()
                    ).SetItems(new Item(
                            functionData.iconId(),
                            1,
                            functionData.iconNbt()
                    )).SetTags(
                            new Tag(
                                    "Is Hidden",
                                    functionData.isHidden() ? "True" : "False"
                            )
                    ), new ArrayList<>()
            );
        } else {
            boolean isMethodStatic = Modifier.isStatic(method.getModifiers());

            // Starts a new function
            CodeManager.instance.addCodeBlocks(
                    new FunctionBlock("_jfdf>" + class_.getName().replace('.', '/') + ">" + method.getName() + ">" + method.getDescriptor())
                            // Setting function's icon
                            .SetItems(new Item(
                                    method.isStaticInitializer() ? "lime_concrete" : (method.isConstructor() ? "orange_concrete" : (isMethodStatic ? "blue_concrete" : "red_concrete")),
                                    1,
                                    method.isStaticInitializer()
                                    ? "display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"green\",\"text\":\"Static Initializer\"}],\"text\":\"\"}',Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"blue\",\"text\":\"  Class:\"}],\"text\":\"\"}','{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"aqua\",\"text\":\"    " + Type.getInternalName(class_) + "\"}],\"text\":\"\"}']}"
                                    : (!method.isConstructor()
                                            ? ("display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"" + (isMethodStatic ? "dark_aqua" : "red") + "\",\"text\":\"" + method.getName() + "\"}],\"text\":\"\"}',Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"blue\",\"text\":\"Location:\"}],\"text\":\"\"}','{\"extra\":[{\"text\":\"    \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"aqua\",\"text\":\"" + Type.getInternalName(class_) + " >\"}],\"text\":\"\"}','{\"extra\":[{\"text\":\"      \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"" + method.getName() + " >\"}],\"text\":\"\"}','{\"extra\":[{\"text\":\"        \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"" + method.getDescriptor() + "\"}],\"text\":\"\"}']}")
                                            : ("display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"Constructor\"}],\"text\":\"\"}',Lore:['{\"extra\":[{\"text\":\"  \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"blue\",\"text\":\"Location:\"}],\"text\":\"\"}','{\"extra\":[{\"text\":\"    \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"aqua\",\"text\":\"" + Type.getInternalName(class_) + " >\"}],\"text\":\"\"}','{\"extra\":[{\"text\":\"      \"},{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"" + method.getDescriptor() + "\"}],\"text\":\"\"}']}"))
                            )), new ArrayList<>()
            );
        }

        if(method.getAnnotation(CompileWithExecute.class) != null
                && Modifier.isStatic(method.getModifiers())) {
            compileWithExecute = true;
            method.setAccessible(true);

            try {
                int argsCount = Type.getArgumentTypes(method.getDescriptor()).length;

                if(argsCount > 0) {
                    method.invoke(
                            null,
                            IntStream.range(0, argsCount)
                                    .mapToObj(operand -> new net.jfdf.jfdf.values.List(new Variable("_jfdffa>%var(_jfdfFD)>" + operand, Variable.Scope.LOCAL)))
                                    .toArray()
                    );
                } else {
                    method.invoke(null);
                }

                VariableControl.Decrement(new Variable("_jfdfFD", Variable.Scope.LOCAL));
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        if(method.isStaticInitializer()) {
            MethodsManager.enableHasStaticInitializer(class_);

            String classInternalName = Type.getInternalName(class_);
            for (Map.Entry<String, Object> defaultValueEntry : FieldsManager.getClassDefaultValues(class_).entrySet()) {
                String fieldName = defaultValueEntry.getKey();
                Object defaultValue = defaultValueEntry.getValue();

                if(defaultValue == null) continue;

                try {
                    CodeValue defaultCodeValue;

                    if(defaultValue instanceof String) {
                        defaultCodeValue = new Text().Set((String) defaultValue);
                    } else if(defaultValue instanceof java.lang.Number) {
                        defaultCodeValue = new Number().Set(((java.lang.Number) defaultValue).doubleValue());
                    } else if(defaultValue instanceof Character) {
                        defaultCodeValue = new Number().Set(Character.getNumericValue((Character) defaultValue));
                    } else {
                        throw new IllegalStateException("Unknown field's default value type.");
                    }

                    VariableControl.Set(
                            new Variable(
                                    "_jfdf>" + classInternalName + ">" + fieldName,
                                    class_.getDeclaredField(fieldName).getAnnotation(Saved.class) == null
                                            ? Variable.Scope.NORMAL : Variable.Scope.SAVED
                            ), defaultCodeValue
                    );
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException("Something went wrong.", e);
                }
            }
        }

//        super.visitCode();
    }

    @Override
    public void visitLabel(Label label) {
        if(compileWithExecute) return;
        labelInstructionIndex = ++instructionIndex;

        endBracketIndices.stream()
                .sorted(Collections.reverseOrder())
                .filter(index -> instructionIndex == Math.abs(index))
                .forEach(index -> {
                    if(index < 0) {
                        Repeat.End();
                    } else {
                        If.End();
                    }
                });

        endBracketIndices.removeIf(index -> instructionIndex == Math.abs(index));
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        if(compileWithExecute) return;
        instructionIndex++;

        this.lineNumber = line;
    }

    @Override
    public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
        if(compileWithExecute) return;
        instructionIndex++;
    }

    @Override
    public void visitInsn(int opcode) {
        if(compileWithExecute) return;
        instructionIndex++;

        switch (opcode) {
            case Opcodes.ACONST_NULL ->
                    stack.add(new NumberStackValue());
            case Opcodes.ICONST_M1,
                    Opcodes.ICONST_0,
                    Opcodes.ICONST_1,
                    Opcodes.ICONST_2,
                    Opcodes.ICONST_3,
                    Opcodes.ICONST_4,
                    Opcodes.ICONST_5 ->
                stack.add(new NumberStackValue(opcode - Opcodes.ICONST_0));
            case Opcodes.LCONST_0,
                    Opcodes.LCONST_1 ->
                    stack.add(new NumberStackValue((long) (opcode - Opcodes.LCONST_0)));
            case Opcodes.FCONST_0,
                    Opcodes.FCONST_1,
                    Opcodes.FCONST_2 ->
                    stack.add(new NumberStackValue((float) (opcode - Opcodes.FCONST_0)));
            case Opcodes.DCONST_0,
                    Opcodes.DCONST_1 ->
                    stack.add(new NumberStackValue((double) (opcode - Opcodes.DCONST_0)));
            case Opcodes.IALOAD,
                    Opcodes.LALOAD,
                    Opcodes.FALOAD,
                    Opcodes.DALOAD,
                    Opcodes.BALOAD,
                    Opcodes.CALOAD,
                    Opcodes.SALOAD -> {
                IStackValue array = stack.remove(stack.size() - 2);
                Variable reference;

                if(array instanceof ReferencedStackValue) {
                    reference = ((ReferencedStackValue) array).getReference();
                } else {
                    reference = new Variable("_jfdfR%var(" + ((Variable) array.getTransformedValue()).getName() + ")", Variable.Scope.NORMAL);
                }

                stack.add(
                        new MathFunctionStackValue(
                                reference,
                                NumberMath.add((INumber) stack.remove(stack.size() - 1).getTransformedValue(), new Number().Set(2)),
                                MathFunctionStackValue.MathFunction.LIST_VALUE
                        )
                );
            }
            case Opcodes.AALOAD -> {
                IStackValue array = stack.remove(stack.size() - 2);
                String elementDescriptor;

                Variable value = getTempVariable();
                Variable reference;

                if(array instanceof ArrayStackValue) {
                    ArrayStackValue newArray = (ArrayStackValue) array;

                    reference = newArray.getReference();
                    elementDescriptor = Type.getType(newArray.getDescriptor()).getElementType().getDescriptor();
                } else {
                    reference = new Variable("_jfdfR%var(" + ((Variable) array.getTransformedValue()).getName() + ")", Variable.Scope.NORMAL);
                    elementDescriptor = Type.getType(array.getDescriptor()).getElementType().getDescriptor();
                }

                VariableControl.GetListValue(
                        value,
                        reference,
                        NumberMath.add((INumber) stack.remove(stack.size() - 1).getTransformedValue(), new Number().Set(2))
                );

                ReferenceUtils.incrementIfReference(elementDescriptor, value);
                stack.add(new VariableStackValue(elementDescriptor, value.getName()));
            }
            case Opcodes.IASTORE,
                    Opcodes.LASTORE,
                    Opcodes.FASTORE,
                    Opcodes.DASTORE,
                    Opcodes.BASTORE,
                    Opcodes.CASTORE,
                    Opcodes.SASTORE,
                    Opcodes.AASTORE -> {
                IStackValue array = stack.remove(stack.size() - 3);
                IStackValue index = stack.remove(stack.size() - 2);
                IStackValue value = stack.remove(stack.size() - 1);

                String valueDescriptor = value.getDescriptor();

                if(array instanceof ArrayStackValue) {
                    ArrayStackValue arrayStackValue = (ArrayStackValue) array;

                    if(opcode == Opcodes.AASTORE) {
                        References.decrementRefCount(
                                NumberMath.listValue(
                                        arrayStackValue.getReference(),
                                        (INumber) index.getTransformedValue()
                                )
                        );
                    }

                    arrayStackValue.set(
                            index,
                            value.getTransformedValue()
                    );
                } else {
                    Variable reference = new Variable("_jfdfR%var(" + ((Variable) array.getTransformedValue()).getName() + ")", Variable.Scope.NORMAL);
                    INumber newIndex = NumberMath.add((INumber) index.getTransformedValue(), new Number().Set(2));

                    if(opcode == Opcodes.AASTORE) {
                        References.decrementRefCount(
                                NumberMath.listValue(
                                        reference,
                                        newIndex
                                )
                        );
                    }

                    VariableControl.SetListValue(
                            reference,
                            newIndex,
                            value.getTransformedValue()
                    );
                }

                if(value.getTransformedValue() instanceof INumber) {
                    ReferenceUtils.incrementIfReference(valueDescriptor, (INumber) value.getTransformedValue());
                }
            }
            case Opcodes.POP ->
                stack.remove(stack.size() - 1);
            case Opcodes.DUP ->
                stack.add(stack.get(stack.size() - 1));
            case Opcodes.DUP2 -> {
                stack.add(stack.get(stack.size() - 2));
                stack.add(stack.get(stack.size() - 2));
            }
            case Opcodes.IADD,
                    Opcodes.LADD,
                    Opcodes.FADD,
                    Opcodes.DADD ->
                    stack.add(new MathStackValue(
                            (INumber) stack.remove(stack.size() - 2).getTransformedValue(),
                            (INumber) stack.remove(stack.size() - 1).getTransformedValue(),
                            MathStackValue.MathOperation.ADD
                    ));
            case Opcodes.ISUB,
                    Opcodes.LSUB,
                    Opcodes.FSUB,
                    Opcodes.DSUB ->
                    stack.add(new MathStackValue(
                            (INumber) stack.remove(stack.size() - 2).getTransformedValue(),
                            (INumber) stack.remove(stack.size() - 1).getTransformedValue(),
                            MathStackValue.MathOperation.SUBTRACT
                    ));
            case Opcodes.IMUL,
                    Opcodes.LMUL,
                    Opcodes.FMUL,
                    Opcodes.DMUL ->
                    stack.add(new MathStackValue(
                            (INumber) stack.remove(stack.size() - 2).getTransformedValue(),
                            (INumber) stack.remove(stack.size() - 1).getTransformedValue(),
                            MathStackValue.MathOperation.MULTIPLY
                    ));
            case Opcodes.IDIV,
                    Opcodes.LDIV ->
                    stack.add(new MathStackValue(
                            (INumber) stack.remove(stack.size() - 2).getTransformedValue(),
                            (INumber) stack.remove(stack.size() - 1).getTransformedValue(),
                            MathStackValue.MathOperation.DIVIDE_INTEGER
                    ));
            case Opcodes.FDIV,
                    Opcodes.DDIV ->
                    stack.add(new MathStackValue(
                            (INumber) stack.remove(stack.size() - 2).getTransformedValue(),
                            (INumber) stack.remove(stack.size() - 1).getTransformedValue(),
                            MathStackValue.MathOperation.DIVIDE
                    ));
            case Opcodes.IREM,
                    Opcodes.LREM,
                    Opcodes.FREM,
                    Opcodes.DREM ->
                    stack.add(new MathStackValue(
                            (INumber) stack.remove(stack.size() - 2).getTransformedValue(),
                            (INumber) stack.remove(stack.size() - 1).getTransformedValue(),
                            MathStackValue.MathOperation.REMAINDER
                    ));
            case Opcodes.INEG,
                    Opcodes.LNEG,
                    Opcodes.FNEG,
                    Opcodes.DNEG ->
                    stack.add(new MathFunctionStackValue(
                            (INumber) stack.remove(stack.size() - 1).getTransformedValue(),
                            MathFunctionStackValue.MathFunction.NEGATIVE
                    ));
            case Opcodes.ISHL,
                    Opcodes.LSHL,
                    Opcodes.ISHR,
                    Opcodes.LSHR,
                    Opcodes.IUSHR,
                    Opcodes.LUSHR,
                    Opcodes.IAND,
                    Opcodes.LAND,
                    Opcodes.IOR,
                    Opcodes.LOR,
                    Opcodes.IXOR,
                    Opcodes.LXOR -> {
                Variable value = getTempVariable();

                Tags.Operator operatorType = switch (opcode) {
                    case Opcodes.ISHL,
                            Opcodes.LSHL ->
                            Tags.Operator.LEFT_SHIFT;
                    case Opcodes.ISHR,
                            Opcodes.LSHR ->
                            Tags.Operator.RIGHT_SHIFT;
                    case Opcodes.IUSHR,
                            Opcodes.LUSHR ->
                            Tags.Operator.UNSIGNED_RIGHT_SHIFT;
                    case Opcodes.IAND,
                            Opcodes.LAND ->
                            Tags.Operator.AND;
                    case Opcodes.IOR,
                            Opcodes.LOR ->
                            Tags.Operator.OR;
                    case Opcodes.IXOR,
                            Opcodes.LXOR ->
                            Tags.Operator.XOR;
                    default ->
                            throw new IllegalStateException("Unknown bitwise operator opcode: " + opcode);
                };

                VariableControl.Bitwise(
                        value,
                        (INumber) stack.remove(stack.size() - 2).getTransformedValue(),
                        (INumber) stack.remove(stack.size() - 1).getTransformedValue(),
                        operatorType
                );

                stack.add(new VariableStackValue("J", value.getName()));
            }
            case Opcodes.L2I,
                    Opcodes.F2I,
                    Opcodes.D2I -> {
                IStackValue stackValue = stack.get(stack.size() - 1);

                if(stackValue instanceof NumberStackValue) {
                    stack.set(stack.size() - 1, new NumberStackValue(((NumberStackValue) stackValue).getJavaValue().intValue()));
                }
            }
            case Opcodes.I2L,
                    Opcodes.F2L,
                    Opcodes.D2L -> {
                IStackValue stackValue = stack.get(stack.size() - 1);

                if(stackValue instanceof NumberStackValue) {
                    stack.set(stack.size() - 1, new NumberStackValue(((NumberStackValue) stackValue).getJavaValue().longValue()));
                }
            }
            case Opcodes.I2F,
                    Opcodes.L2F,
                    Opcodes.D2F -> {
                IStackValue stackValue = stack.get(stack.size() - 1);

                if(stackValue instanceof NumberStackValue) {
                    stack.set(stack.size() - 1, new NumberStackValue(((NumberStackValue) stackValue).getJavaValue().floatValue()));
                }
            }
            case Opcodes.I2D,
                    Opcodes.L2D,
                    Opcodes.F2D -> {
                IStackValue stackValue = stack.get(stack.size() - 1);

                if(stackValue instanceof NumberStackValue) {
                    stack.set(stack.size() - 1, new NumberStackValue(((NumberStackValue) stackValue).getJavaValue().doubleValue()));
                }
            }
            case Opcodes.I2B ->
                    stack.set(stack.size() - 1,
                            new MathFunctionStackValue(
                                    (INumber) stack.get(stack.size() - 1).getTransformedValue(),
                                    MathFunctionStackValue.MathFunction.CAST_TO_BYTE,
                                    method.getName(), blockOperationIndex++
                            )
                    );
            case Opcodes.I2C ->
                    stack.set(stack.size() - 1,
                            new MathFunctionStackValue(
                                    (INumber) stack.get(stack.size() - 1).getTransformedValue(),
                                    MathFunctionStackValue.MathFunction.CAST_TO_CHAR,
                                    method.getName(), blockOperationIndex++
                            )
                    );
            case Opcodes.I2S ->
                    stack.set(stack.size() - 1,
                            new MathFunctionStackValue(
                                    (INumber) stack.get(stack.size() - 1).getTransformedValue(),
                                    MathFunctionStackValue.MathFunction.CAST_TO_SHORT,
                                    method.getName(), blockOperationIndex++
                            )
                    );
            case Opcodes.FCMPL,
                    Opcodes.FCMPG,
                    Opcodes.DCMPL,
                    Opcodes.DCMPG ->
                this.stack.add(
                        new CompareStackValue(
                                CompareStackValue.CompareType.NORMAL,
                                stack.remove(stack.size() - 2),
                                stack.remove(stack.size() - 1)
                        )
                );
            case Opcodes.IRETURN,
                    Opcodes.LRETURN,
                    Opcodes.FRETURN,
                    Opcodes.DRETURN,
                    Opcodes.ARETURN -> {
                IStackValue stackValue = stack.remove(stack.size() - 1);

                VariableControl.Increment(new Variable("_jfdfRF", Variable.Scope.LOCAL), new Number().Set(1));

                // Checks if value is a new reference object
                if(stackValue instanceof ReferencedStackValue) {
                    // Changes array's variable to return variable
                    ((ReferencedStackValue) stackValue).setAllocationVariable("_functionr%var(_jfdfRF)", Variable.Scope.LOCAL);
                } else {
                    // Sets return variable to a value
                    VariableControl.Set(new Variable("_functionr%var(_jfdfRF)", Variable.Scope.LOCAL), stackValue.getTransformedValue());
                }

                int firstLocalVar = (method.isMember() ? 1 : 0)
                        + Type.getArgumentTypes(method.getDescriptor()).length;

                for (Map.Entry<Integer, String> descriptorEntry : variableDescriptors.entrySet()) {
                    int var = descriptorEntry.getKey();
                    String descriptor = descriptorEntry.getValue();

                    if(var >= firstLocalVar) {
                        Variable valuePointer = new Variable("_jfdffv>%var(_jfdfFD)>" + var, Variable.Scope.LOCAL);
                        ReferenceUtils.decrementIfReference(descriptor, valuePointer);
                    }
                }

                VariableControl.Decrement(new Variable("_jfdfFD", Variable.Scope.LOCAL), new Number().Set(1));
                Control.Return();
            }
            case Opcodes.RETURN -> {
                int firstLocalVar = (method.isMember() ? 1 : 0)
                        + Type.getArgumentTypes(method.getDescriptor()).length;

                for (Map.Entry<Integer, String> descriptorEntry : variableDescriptors.entrySet()) {
                    int var = descriptorEntry.getKey();
                    String descriptor = descriptorEntry.getValue();

                    if(var >= firstLocalVar) {
                        Variable valuePointer = new Variable("_jfdffv>%var(_jfdfFD)>" + var, Variable.Scope.LOCAL);
                        ReferenceUtils.decrementIfReference(descriptor, valuePointer);
                    }
                }

                if(isThread) {
                    Functions.Call("_jfdf>std>procEnd");
                } else {
                    VariableControl.Decrement(new Variable("_jfdfFD", Variable.Scope.LOCAL), new Number().Set(1));
                    Control.Return();
                }
            }
            case Opcodes.ARRAYLENGTH -> {
                IStackValue array = stack.remove(stack.size() - 1);

                Variable value = getTempVariable();
                Variable reference;

                if(array instanceof ReferencedStackValue) {
                    reference = ((ReferencedStackValue) array).getReference();
                } else {
                    reference = new Variable("_jfdfR%var(" + ((Variable) array.getTransformedValue()).getName() + ")", Variable.Scope.NORMAL);
                }

                VariableControl.ListLength(
                        value,
                        reference
                );

                stack.add(new VariableStackValue("I", value.getName()));
            }
            default -> throw new IllegalStateException("Unsupported opcode: " + opcode);
        }

        super.visitInsn(opcode);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        if(compileWithExecute) return;
        instructionIndex++;

        String variableName;
        if(opcode >= Opcodes.ISTORE && opcode <= Opcodes.ASTORE) {
            IStackValue stackValue = stack.remove(stack.size() - 1);

            // Getting variable name (if variable is argument prefix it is going to be "_jfdffa" else it will be be "_jfdffv")
            if((Type.getArgumentTypes(method.getDescriptor()).length + (method.isMember() ? 1 : 0)) > var) {
                variableName = "_jfdffa>%var(_jfdfFD)>" + var;
            } else {
                variableName = "_jfdffv>%var(_jfdfFD)>" + var;
            }

            Variable valuePointer = new Variable(variableName, Variable.Scope.NORMAL);

            String variableDescriptor = variableDescriptors.get(var);
            ReferenceUtils.decrementIfReference(variableDescriptor, valuePointer);

            // Checks if value is a new reference object
            if(stackValue instanceof ReferencedStackValue) {
                ReferencedStackValue referencedStackValue = ((ReferencedStackValue) stackValue);

                if(ReferenceUtils.isReferenceDescriptor(stackValue.getDescriptor())
                        && !ReferenceUtils.isReferenceDescriptor(variableDescriptor)
                        && variableDescriptor != null) {
                    referencedStackValue.resetBeforeAllocate();
                }

                // Changes array's variable to this variable and updates variable's descriptor
                referencedStackValue.setAllocationVariable(variableName, Variable.Scope.LOCAL);
                variableDescriptors.put(var, stackValue.getDescriptor());
            } else {
                // Sets this variable to a value and updates variable's descriptor
                VariableControl.Set(new Variable(variableName, Variable.Scope.LOCAL), stackValue.getTransformedValue());
                variableDescriptors.put(var, stackValue.getDescriptor());

                ReferenceUtils.incrementIfReference(stackValue.getDescriptor(), valuePointer);
            }
        } else if(opcode >= Opcodes.ILOAD && opcode <= Opcodes.ALOAD) {
            if((Type.getArgumentTypes(method.getDescriptor()).length + (method.isMember() ? 1 : 0)) > var) {
                if(var == 0 && method.isConstructor()) {
                    stack.add(new VariableStackValue("Ljava/lang/Object;", "_jfdfRP"));
                } else {
                    stack.add(new VariableStackValue(variableDescriptors.get(var), "_jfdffa>%var(_jfdfFD)>" + var));
                }
            } else {
                stack.add(new VariableStackValue(variableDescriptors.get(var), "_jfdffv>%var(_jfdfFD)>" + var));
            }
        } else {
            throw new IllegalStateException("Unsupported opcode: " + opcode);
        }

        super.visitVarInsn(opcode, var);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        if(compileWithExecute) return;
        instructionIndex++;

        switch (opcode) {
            case Opcodes.INVOKEVIRTUAL, Opcodes.INVOKEINTERFACE -> {
                int argsCount = Type.getArgumentTypes(descriptor).length;
                IStackValue pointerStackValue = stack.get(stack.size() - (argsCount + 1));

                if(pointerStackValue instanceof CodeStackValue && !(name.equals("equals") && descriptor.equals("(Ljava/lang/Object;)Z"))) {
                    Object[] methodArgs = new Object[argsCount];
                    Type[] argumentTypes = Type.getArgumentTypes(descriptor);

                    for (int i = argsCount - 1; i >= 0; i--) {
                        IStackValue argStackValue = stack.remove(stack.size() - 1);

                        if(argStackValue instanceof TextStackValue) {
                            methodArgs[i] = ((TextStackValue) argStackValue).getValue().Get();
                        } else if(argStackValue instanceof NumberStackValue) {
                            if(argumentTypes[i] == Type.BOOLEAN_TYPE) {
                                methodArgs[i] = ((NumberStackValue) argStackValue).getJavaValue().intValue() == 1;
                            } else {
                                methodArgs[i] = ((NumberStackValue) argStackValue).getJavaValue();
                            }
                        } else {
                            throw new IllegalStateException("Passing non-constant value is not allowed in a code value method.");
                        }
                    }

                    ((CodeStackValue) pointerStackValue).invokeMethod(name, descriptor, methodArgs);
                    stack.remove(stack.size() - 1);

                    if(descriptor.endsWith(")L" + owner + ";")) {
                        stack.add(pointerStackValue);
                    }

                    return;
                } else if(pointerStackValue instanceof CodeArrayStackValue) {
                    if(name.equals("next")) {
                        IStackValue stackValue = stack.remove(stack.size() - 1);

                        ((CodeArrayStackValue) pointerStackValue).next(stackValue.getTransformedValue());
                    } else if(!name.equals("getArray")) {
                        throw new IllegalStateException("Unknown CodeArrayStackValue method: " + name + descriptor + ".");
                    }

                    return;
                }

                if(name.equals("valueOf") && descriptor.equals("(Ljava/lang/String;)L" + owner + ";")) {
                    try {
                        Class<?> ownerClass = Class.forName(owner);

                        if(ownerClass.getSuperclass() == Enum.class) {
                            CodeValue fieldNameValue = stack.remove(stack.size() - 1).getTransformedValue();
                            String fieldName = "";

                            if(fieldNameValue instanceof Text) {
                                fieldName = ((Text) fieldNameValue).Get();
                            } else if(fieldNameValue instanceof Variable) {
                                fieldName = "%var(" + ((Variable) fieldNameValue).getName() + ")";
                            }

                            stack.remove(stack.size() - 1);
                            stack.add(new VariableStackValue("L" + owner + ";", "_jfdf>" + owner + ">" + fieldName, Variable.Scope.NORMAL));
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("Something went wrong.", e);
                    }
                }

                if(name.equals("equals")
                        && descriptor.equals("(Ljava/lang/Object;)Z")
                        && (owner.equals("java/lang/Object")
                            || owner.equals("java/lang/String"))) {
                    stack.add(
                            new CompareStackValue(
                                    CompareStackValue.CompareType.NORMAL,
                                    stack.remove(stack.size() - 2),
                                    stack.remove(stack.size() - 1)
                            )
                    );

                    return;
                } else if(name.equals("clone")
                        && descriptor.equals("()Ljava/lang/Object;")
                        && owner.startsWith("[")) {
                    String variableName = getTempVariableName();
                    Variable reference;

                    if(pointerStackValue instanceof ReferencedStackValue) {
                        reference = ((ReferencedStackValue) pointerStackValue).getReference();
                    } else {
                        reference = new Variable("_jfdfR%var(" + ((Variable) pointerStackValue.getTransformedValue()).getName() + ")", Variable.Scope.NORMAL);
                    }

                    VariableControl.Set(
                            new Variable(variableName, Variable.Scope.LOCAL),
                            reference
                    );

                    stack.remove(stack.size() - 1);
                    stack.add(new VariableStackValue("L" + owner + ";", variableName));
                    return;
                } else if(owner.startsWith("net/jfdf/jfdf/mangement/")) {
                    Object[] methodArgs = new Object[argsCount];

                    if(!(pointerStackValue instanceof SpecialStackValue)) {
                        throw new IllegalStateException("Given stack value should be a special stack value.");
                    }

                    SpecialStackValue specialStackValue = (SpecialStackValue) pointerStackValue;
                    Class<?> class_;
                    Object object;

                    switch (owner) {
                        case "net/jfdf/jfdf/mangement/Player" -> {
                            class_ = Player.class;

                            object = switch (specialStackValue.getType()) {
                                case PLAYER_CURRENT_SELECTION -> Player.getCurrentSelection();
                                case PLAYER_DEFAULT -> Player.getDefaultPlayer();
                                case PLAYER_KILLER -> Player.getKiller();
                                case PLAYER_DAMAGER -> Player.getDamager();
                                case PLAYER_SHOOTER -> Player.getShooter();
                                case PLAYER_VICTIM -> Player.getVictim();
                                case PLAYER_ALL_PLAYERS -> Player.getAllPlayers();
                                default -> throw new IllegalStateException("Non-player selection special type \"" + specialStackValue.getType() + "\".");
                            };
                        }
                        case "net/jfdf/jfdf/mangement/Entity" -> {
                            class_ = Entity.class;

                            object = switch (specialStackValue.getType()) {
                                case ENTITY_CURRENT_SELECTION -> Entity.getCurrentSelection();
                                case ENTITY_DEFAULT -> Entity.getDefaultEntity();
                                case ENTITY_KILLER -> Entity.getKiller();
                                case ENTITY_DAMAGER -> Entity.getDamager();
                                case ENTITY_SHOOTER -> Entity.getShooter();
                                case ENTITY_VICTIM -> Entity.getVictim();
                                case ENTITY_PROJECTILE -> Entity.getProjectile();
                                case ENTITY_ALL_ENTITIES -> Entity.getAllEntities();
                                case ENTITY_ALL_MOBS -> Entity.getAllMobs();
                                case ENTITY_LAST_ENTITY_SPAWNED -> Entity.getLastEntitySpawned();
                                case ENTITY_LAST_MOB_SPAWNED -> Entity.getLastMobSpawned();
                                default -> throw new IllegalStateException("Non-entity selection special type \"" + specialStackValue.getType() + "\".");
                            };
                        }
                        case "net/jfdf/jfdf/mangement/If$Player" -> {
                            class_ = If.Player.class;

                            object = switch (specialStackValue.getType()) {
                                case PLAYER_CURRENT_SELECTION -> If.Player.getCurrentSelection();
                                case PLAYER_DEFAULT -> If.Player.getDefaultPlayer();
                                case PLAYER_KILLER -> If.Player.getKiller();
                                case PLAYER_DAMAGER -> If.Player.getDamager();
                                case PLAYER_SHOOTER -> If.Player.getShooter();
                                case PLAYER_VICTIM -> If.Player.getVictim();
                                case PLAYER_ALL_PLAYERS -> If.Player.getAllPlayers();
                                default -> throw new IllegalStateException("Non-player selection special type \"" + specialStackValue.getType() + "\".");
                            };
                        }
                        case "net/jfdf/jfdf/mangement/If$Entity" -> {
                            class_ = If.Entity.class;

                            object = switch (specialStackValue.getType()) {
                                case ENTITY_CURRENT_SELECTION -> If.Entity.getCurrentSelection();
                                case ENTITY_DEFAULT -> If.Entity.getDefaultEntity();
                                case ENTITY_KILLER -> If.Entity.getKiller();
                                case ENTITY_DAMAGER -> If.Entity.getDamager();
                                case ENTITY_SHOOTER -> If.Entity.getShooter();
                                case ENTITY_VICTIM -> If.Entity.getVictim();
                                case ENTITY_PROJECTILE -> If.Entity.getProjectile();
                                case ENTITY_ALL_ENTITIES -> If.Entity.getAllEntities();
                                case ENTITY_ALL_MOBS -> If.Entity.getAllMobs();
                                case ENTITY_LAST_ENTITY_SPAWNED -> If.Entity.getLastEntitySpawned();
                                case ENTITY_LAST_MOB_SPAWNED -> If.Entity.getLastMobSpawned();
                                default -> throw new IllegalStateException("Non-entity selection special type \"" + specialStackValue.getType() + "\".");
                            };
                        }
                        default -> throw new IllegalStateException("Unknown class type during invoking virtual method from management api: " + owner + ".");
                    }

                    Type[] argumentTypes = Type.getArgumentTypes(descriptor);

                    for (int i = argsCount - 1; i >= 0; i--) {
                        IStackValue stackValue = stack.remove(stack.size() - 1);

                        if(stackValue instanceof CodeArrayStackValue) {
                            try {
                                Class<?> arrayClass = Class.forName(argumentTypes[i].getElementType().getClassName()).arrayType();
                                CodeValue[] array = ((CodeArrayStackValue) stackValue).getValues();

                                methodArgs[i] = Arrays.copyOf(array, array.length, (Class<? extends CodeValue[]>) arrayClass);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException("Something went wrong.", e);
                            }
                        } else if(stackValue instanceof EnumStackValue) {
                            methodArgs[i] = ((EnumStackValue) stackValue).getEnumValue();
                        } else {
                            if(stackValue instanceof NumberStackValue) {
                                if(((NumberStackValue) stackValue).getJavaValue() == null) {
                                    methodArgs[i] = null;
                                    continue;
                                } else if(argumentTypes[i] == Type.BOOLEAN_TYPE) {
                                    methodArgs[i] = (((NumberStackValue) stackValue).getJavaValue().intValue() == 1);
                                    continue;
                                }
                            }

                            methodArgs[i] = stackValue.getTransformedValue();
                        }
                    }

                    try {
                        MethodWrapper.getWrapper(class_, name, descriptor).invoke(object, methodArgs);
                    } catch (NoSuchMethodException | IllegalAccessException e) {
                        throw new RuntimeException("Something went wrong.", e);
                    } catch (InvocationTargetException | InstantiationException | IllegalArgumentException e) {
                        throw new RuntimeException("Invalid value types during invoking \"" + name + descriptor + "\" of class \"" + owner + "\".\n"
                                + "Passed arguments: " + Arrays.deepToString(methodArgs) + "\n"
                                + "Invoked by : " + Type.getInternalName(class_) + ">" + method.getName() + method.getDescriptor() + ":" + lineNumber, e);
                    }

                    stack.remove(stack.size() - 1);
                    return;
                } else if(owner.equals("java/io/PrintStream")
                        && name.equals("println")) {
                    SpecialStackValue.SpecialValueType streamType = ((SpecialStackValue) stack.remove(stack.size() - 2)).getType();

                    // Checks stream type
                    if(streamType == SpecialStackValue.SpecialValueType.SYSTEM_OUT) {

                        // Sends a message to currently selected
                        // players with white color
                        Player.getCurrentSelection().SendMessage(
                                new CodeValue[]{
                                        stack.remove(stack.size() - 1).getTransformedValue()
                                },
                                Tags.AlignmentMode.REGULAR,
                                Tags.TextValueMerging.NO_SPACES
                        );
                    } else if(streamType == SpecialStackValue.SpecialValueType.SYSTEM_ERR) {

                        // Sends a message to currently selected
                        // players with red color
                        Player.getCurrentSelection().SendMessage(
                                new CodeValue[]{
                                        new Text().Set("§c"),
                                        stack.remove(stack.size() - 1).getTransformedValue()
                                },
                                Tags.AlignmentMode.REGULAR,
                                Tags.TextValueMerging.NO_SPACES
                        );
                    } else {
                        throw new IllegalStateException("Unknown special value type: " + streamType);
                    }
                } else if(owner.equals("java/util/List") || owner.equals("java/util/ArrayList") || owner.equals("java/util/Collection")) {
                    stack.remove(stack.size() - (argsCount + 1));

                    Variable reference;

                    if(pointerStackValue instanceof ReferencedStackValue) {
                        reference = ((ReferencedStackValue) pointerStackValue).getReference();
                    } else {
                        reference = new Variable("_jfdfR%var(" + ((Variable) pointerStackValue.getTransformedValue()).getName() + ")", Variable.Scope.NORMAL);
                    }

                    switch (name + descriptor) {
                        case "add(Ljava/lang/Object;)Z" -> {
                            if(pointerStackValue instanceof ArrayStackValue) {
                                throw new UnsupportedOperationException("Trying to append value to array.");
                            }

                            IStackValue stackValue = stack.remove(stack.size() - 1);


                            VariableControl.AppendValue(
                                    reference,
                                    stackValue.getTransformedValue()
                            );

                            String valueDescriptor = stackValue.getDescriptor();
                            ReferenceUtils.incrementIfReference(valueDescriptor, (INumber) stackValue.getTransformedValue());

                            stack.add(new NumberStackValue(1));
                        }
                        case "add(ILjava/lang/Object;)V" -> {
                            if(pointerStackValue instanceof ArrayStackValue) {
                                throw new UnsupportedOperationException("Trying to insert value on array.");
                            }

                            IStackValue stackValue = stack.remove(stack.size() - 1);

                            VariableControl.InsertListValue(
                                    reference,
                                    NumberMath.add((INumber) stack.remove(stack.size() - 2).getTransformedValue(), new Number().Set(1)),
                                    stackValue.getTransformedValue()
                            );

                            String valueDescriptor = stackValue.getDescriptor();
                            ReferenceUtils.incrementIfReference(valueDescriptor, (INumber) stackValue.getTransformedValue());
                        }
                        case "remove(Ljava/lang/Object;)Z" -> {
                            if(pointerStackValue instanceof ArrayStackValue) {
                                throw new UnsupportedOperationException("Trying to remove value from array.");
                            }

                            System.out.println("[WARNING] Using List.remove(java.lang.Object) is not stable yet");

                            VariableControl.RemoveListValue(
                                    reference,
                                    stack.remove(stack.size() - 1).getTransformedValue()
                            );

                            stack.add(new NumberStackValue(1));
                        }
                        case "remove(I)Ljava/lang/Object;" -> {
                            if(pointerStackValue instanceof ArrayStackValue) {
                                throw new UnsupportedOperationException("Trying to remove value from array.");
                            }

                            INumber index = NumberMath.add((INumber) stack.remove(stack.size() - 1).getTransformedValue(), new Number().Set(2));

                            InstructionData nextInsn = instructionDataList.get(instructionIndex + 1);
                            if(nextInsn.instructionOpcode != Opcodes.POP) {
                                String getValueDescriptor = "Ljava/lang/Object;";

                                if(nextInsn.instructionOpcode == Opcodes.CHECKCAST) {
                                    getValueDescriptor = ((TypeInstructionData) nextInsn).type;

                                    if(!getValueDescriptor.startsWith("[")) {
                                        getValueDescriptor = "L" + getValueDescriptor + ";";
                                    }

                                    getValueDescriptor = switch (getValueDescriptor) {
                                        case "Ljava/lang/Boolean;" -> "Z";
                                        case "Ljava/lang/Byte;"    -> "B";
                                        case "Ljava/lang/Short;"   -> "S";
                                        case "Ljava/lang/Integer;" -> "I";
                                        case "Ljava/lang/Long;"    -> "J";

                                        case "Ljava/lang/Float;"   -> "F";
                                        case "Ljava/lang/Double;"  -> "D";

                                        default -> getValueDescriptor;
                                    };
                                }

                                String variableName = getTempVariableName();

                                VariableControl.GetListValue(
                                        new Variable(variableName, Variable.Scope.LOCAL),
                                        reference,
                                        index
                                );

                                stack.add(new VariableStackValue(getValueDescriptor, variableName));
                            } else {
                                References.decrementRefCount(NumberMath.listValue(reference, index));

                                // This would go away with POP
                                stack.add(new NumberStackValue(0));
                            }

                            VariableControl.RemoveListIndex(
                                    reference,
                                    index
                            );
                        }
                        case "size()I" -> {
                            String variableName = getTempVariableName();

                            VariableControl.ListLength(
                                    new Variable(variableName, Variable.Scope.LOCAL),
                                    reference
                            );

                            stack.add(new VariableStackValue("I", variableName));
                        }
                        case "addAll(Ljava/lang/Collection;)Z" -> {
                            if(pointerStackValue instanceof ArrayStackValue) {
                                throw new UnsupportedOperationException("Trying to append a collection to array.");
                            }

                            System.out.println("[WARNING] Using List.addAll(java.lang.Collection) is not stable yet");

                            VariableControl.AppendList(
                                    reference,
                                    (Variable) stack.get(stack.size() - 1).getTransformedValue()
                            );

                            stack.add(new NumberStackValue(1));
                        }
                        case "addAll(ILjava/lang/Collection;)Z" -> {
                            if(pointerStackValue instanceof ArrayStackValue) {
                                throw new UnsupportedOperationException("Trying to insert a collection on array.");
                            }

                            throw new IllegalStateException("Inserting a collection at index is not supported.");
                        }
                        case "toArray()[Ljava/lang/Object;" ->
                            stack.add(
                                    new ArrayStackValue(
                                            (Variable) pointerStackValue.getTransformedValue(),
                                            method.getName(),
                                            blockOperationIndex++
                                    )
                            );
                        case "clear()V" -> {
                            if(pointerStackValue instanceof ArrayStackValue) {
                                throw new UnsupportedOperationException("Trying to clear an array.");
                            }

                            VariableControl.CreateList(
                                    reference
                            );
                        }
                        case "indexOf(Ljava/lang/Object;)I" -> {
                            CodeValue value = stack.remove(stack.size() - 1).getTransformedValue();
                            String variableName = getTempVariableName();

                            VariableControl.GetValueIndex(
                                    new Variable(variableName, Variable.Scope.LOCAL),
                                    reference,
                                    value,
                                    Tags.SearchOrder.ASCENDING__FIRST_INDEX_
                            );

                            stack.add(new MathStackValue(new Variable(variableName, Variable.Scope.LOCAL), new Number().Set(1), MathStackValue.MathOperation.SUBTRACT));
                        }
                        case "get(I)Ljava/lang/Object;" -> {
                            INumber index = NumberMath.add((INumber) stack.remove(stack.size() - 1).getTransformedValue(), new Number().Set(1));
                            String variableName = getTempVariableName();

                            Variable value = new Variable(variableName, Variable.Scope.LOCAL);
                            VariableControl.GetListValue(
                                    value,
                                    reference,
                                    index
                            );

                            InstructionData nextInsn = instructionDataList.get(instructionIndex + 1);
                            String getValueDescriptor = "Ljava/lang/Object;";

                            if(nextInsn.instructionOpcode == Opcodes.CHECKCAST) {
                                getValueDescriptor = ((TypeInstructionData) nextInsn).type;

                                if(!getValueDescriptor.startsWith("[")) {
                                    getValueDescriptor = "L" + getValueDescriptor + ";";
                                }

                                getValueDescriptor = switch (getValueDescriptor) {
                                    case "Ljava/lang/Boolean;" -> "Z";
                                    case "Ljava/lang/Byte;"    -> "B";
                                    case "Ljava/lang/Short;"   -> "S";
                                    case "Ljava/lang/Integer;" -> "I";
                                    case "Ljava/lang/Long;"    -> "J";

                                    case "Ljava/lang/Float;"   -> "F";
                                    case "Ljava/lang/Double;"  -> "D";

                                    default -> getValueDescriptor;
                                };
                            }

                            ReferenceUtils.decrementIfReference(getValueDescriptor, value);
                            stack.add(new VariableStackValue("Ljava/lang/Object;", variableName));
                        }
                        case "set(ILjava/lang/Object;)Ljava/lang/Object;" -> {
                            INumber index = NumberMath.add((INumber) stack.remove(stack.size() - 1).getTransformedValue(), new Number().Set(2));
                            IStackValue value = stack.remove(stack.size() - 1);

                            InstructionData nextInsn = instructionDataList.get(instructionIndex + 1);
                            if(nextInsn.instructionOpcode != Opcodes.POP) {
                                String getValueDescriptor = "Ljava/lang/Object;";

                                if(nextInsn.instructionOpcode == Opcodes.CHECKCAST) {
                                    getValueDescriptor = ((TypeInstructionData) nextInsn).type;

                                    if(!getValueDescriptor.startsWith("[")) {
                                        getValueDescriptor = "L" + getValueDescriptor + ";";
                                    }

                                    getValueDescriptor = switch (getValueDescriptor) {
                                        case "Ljava/lang/Boolean;" -> "Z";
                                        case "Ljava/lang/Byte;"    -> "B";
                                        case "Ljava/lang/Short;"   -> "S";
                                        case "Ljava/lang/Integer;" -> "I";
                                        case "Ljava/lang/Long;"    -> "J";

                                        case "Ljava/lang/Float;"   -> "F";
                                        case "Ljava/lang/Double;"  -> "D";

                                        default -> getValueDescriptor;
                                    };
                                }

                                String variableName = getTempVariableName();

                                VariableControl.GetListValue(
                                        new Variable(variableName, Variable.Scope.LOCAL),
                                        reference,
                                        index
                                );

                                stack.add(new VariableStackValue(getValueDescriptor, variableName));
                            } else {
                                References.decrementRefCount(NumberMath.listValue(reference, index));

                                // This would go away with POP
                                stack.add(new NumberStackValue(0));
                            }

                            VariableControl.SetListValue(
                                    reference,
                                    index,
                                    value.getTransformedValue()
                            );

                            ReferenceUtils.incrementIfReference(value.getDescriptor(), (INumber) value.getTransformedValue());
                        }
                    }

                    return;
                } else {
                    CodeValue[] invokeVirtualArgs = new CodeValue[3 + argsCount];

                    invokeVirtualArgs[0] = pointerStackValue.getTransformedValue();

                    if(JFDFCompiler.useNextPatchFeatures) {
                        ((Variable) invokeVirtualArgs[0]).setName(((Variable) invokeVirtualArgs[0]).getName().replace("%var(_jfdfFD)", "%math(%var(_jfdfFD) - 1)"));
                    } else {
                        ((Variable) invokeVirtualArgs[0]).setName(((Variable) invokeVirtualArgs[0]).getName().replace("%var(_jfdfFD)", "%var(_jfdfPFD)"));
                    }

                    invokeVirtualArgs[1] = new Text().Set(name);
                    invokeVirtualArgs[2] = new Text().Set(descriptor);

                    for (int i = argsCount - 1; i >= 0; i--) {
                        CodeValue arg = stack.remove(stack.size() - 1).getTransformedValue();

                        String replaceValue = JFDFCompiler.useNextPatchFeatures
                                ? "%math(%var(_jfdfFD) - 1)"
                                : "%var(_jfdfPFD)";

                        if(arg instanceof Variable && ((Variable) arg).getName().contains("%var(_jfdfFD)")) {
                            ((Variable) arg).setName(((Variable) arg).getName().replace("%var(_jfdfFD)", replaceValue));
                        } else if(arg instanceof Number) {
                            ((Number) arg).SetToString(((Number) arg).getValue().replace("%var(_jfdfFD)", replaceValue));
                        } else if(arg instanceof Text) {
                            ((Text) arg).Set(((Text) arg).Get().replace("%var(_jfdfFD)", replaceValue));
                        }

                        invokeVirtualArgs[i + 3] = arg;
                    }

                    if(!JFDFCompiler.useNextPatchFeatures) VariableControl.Set(new Variable("_jfdfPFD", Variable.Scope.LOCAL), new Variable("_jfdfFD", Variable.Scope.LOCAL));
                    VariableControl.Increment(new Variable("_jfdfFD", Variable.Scope.LOCAL));

                    VariableControl.CreateList(
                            new Variable("_jfdffa>%var(_jfdfFD)>0", Variable.Scope.LOCAL),
                            invokeVirtualArgs
                    );

                    Functions.Call("_jfdf>std>invokeVirtual");

                    stack.remove(stack.size() - 1);

                    String returnDescriptor = Type.getReturnType(descriptor).getDescriptor();
                    if(!returnDescriptor.equals("V")) {
                        stack.add(new VariableStackValue(returnDescriptor, "_functionr%var(_jfdfRF)"));
                    }
                }

                return;
            }
            case Opcodes.INVOKESPECIAL -> {
                int argsCount = Type.getArgumentTypes(descriptor).length;
                IStackValue objectStackValue = stack.get(stack.size() - (argsCount + 1));

                if(objectStackValue instanceof CodeStackValue) {
                    Object[] constructorArgs = new Object[argsCount];

                    for (int i = argsCount - 1; i >= 0; i--) {
                        IStackValue argStackValue = stack.remove(stack.size() - 1);

                        if(argStackValue instanceof TextStackValue) {
                            constructorArgs[i] = ((TextStackValue) argStackValue).getValue().Get();
                        } else if(argStackValue instanceof NumberStackValue) {
                            constructorArgs[i] = ((NumberStackValue) argStackValue).getJavaValue();
                        } else if(argStackValue instanceof EnumStackValue) {
                            constructorArgs[i] = ((EnumStackValue) argStackValue).getEnumValue();
                        } else {
                            throw new IllegalStateException("Passing non-constant value is not allowed in a code value constructor.");
                        }
                    }

                    ((CodeStackValue) objectStackValue).callConstructor(descriptor, constructorArgs);
                    stack.remove(stack.size() - 1);
                    return;
                }

                if(name.equals("<init>")) {
                    if(objectStackValue instanceof ListStackValue) {
                        switch (descriptor) {
                            case "()V" ->
                                    VariableControl.CreateList(
                                            ((ListStackValue) objectStackValue).getReference()
                                    );
                            case "(Ljava/util/Collection;)V" ->
                                    VariableControl.Set(
                                            ((ListStackValue) objectStackValue).getReference(),
                                            stack.remove(stack.size() - 1).getTransformedValue()
                                    );
                            default -> throw new IllegalStateException("Unsupported ArrayList constructor: " + name + descriptor + ".");
                        }

                        stack.remove(stack.size() - 1);
                        return;
                    }

                    if (method.isConstructor()) {
                        if (objectStackValue instanceof VariableStackValue
                                && ((Variable) objectStackValue.getTransformedValue()).getName().equals("_jfdfRP")) {
                            int memberFieldsCount = (int) Arrays.stream(class_.getDeclaredFields())
                                    .filter(field -> !Modifier.isStatic(field.getModifiers()))
                                    .count();

                            if (owner.equals("java/lang/Object") || owner.equals("java/lang/Enum")) {
                                boolean containClassName = !Modifier.isFinal(class_.getModifiers());
                                CodeValue[] objectData = new CodeValue[memberFieldsCount + (containClassName ? 1 : 0)];

                                Arrays.fill(objectData, new Number().Set(0));
                                if (containClassName) objectData[0] = new Text().Set(class_.getName().replace('.', '/'));

                                VariableControl.CreateList(
                                        new Variable("_jfdfR%var(_jfdfRP)", Variable.Scope.NORMAL),
                                        objectData
                                );
                            } else {
                                CodeValue[] objectDataAddition = new CodeValue[memberFieldsCount];
                                Arrays.fill(objectDataAddition, new Number().Set(0));

                                if(!JFDFCompiler.useNextPatchFeatures) VariableControl.Set(new Variable("_jfdfPFD", Variable.Scope.LOCAL), new Variable("_jfdfFD", Variable.Scope.LOCAL));
                                VariableControl.Increment(new Variable("_jfdfFD", Variable.Scope.LOCAL));

                                // Setting pointer here is unnecessary, because
                                // it has been set previously when the constructor
                                // got called
                                for (int i = 1; i <= argsCount; i++) {
                                    CodeValue arg = stack.remove(stack.size() - (argsCount - i) - 1).getTransformedValue();

                                    String replaceValue = JFDFCompiler.useNextPatchFeatures
                                            ? "%math(%var(_jfdfFD) - 1)"
                                            : "%var(_jfdfPFD)";

                                    if(arg instanceof Variable && ((Variable) arg).getName().contains("%var(_jfdfFD)")) {
                                        ((Variable) arg).setName(((Variable) arg).getName().replace("%var(_jfdfFD)", replaceValue));
                                    } else if(arg instanceof Number) {
                                        ((Number) arg).SetToString(((Number) arg).getValue().replace("%var(_jfdfFD)", replaceValue));
                                    } else if(arg instanceof Text) {
                                        ((Text) arg).Set(((Text) arg).Get().replace("%var(_jfdfFD)", replaceValue));
                                    }

                                    VariableControl.Set(
                                            new Variable("_jfdffa>%var(_jfdfFD)>" + i, Variable.Scope.LOCAL),
                                            arg
                                    );
                                }

                                String callDescriptor = descriptor;

                                try {
                                    MethodWrapper invokeMethod = MethodWrapper.getWrapper(Class.forName(owner.replace('/', '.')), name, callDescriptor);

                                    if (invokeMethod.getAnnotation(MethodFallback.class) != null) {
                                        callDescriptor = invokeMethod.getAnnotation(MethodFallback.class).descriptor();
                                    }
                                } catch (ClassNotFoundException | NoSuchMethodException e) {
                                    throw new RuntimeException("Something went wrong.", e);
                                }

                                Functions.Call("_jfdf>" + owner + ">" + name + ">" + callDescriptor);

                                VariableControl.SetListValue(
                                        new Variable("_jfdfR%var(_jfdfRP)", Variable.Scope.NORMAL),
                                        new Number().Set(1),
                                        new Text().Set(class_.getName().replace('.', '/'))
                                );

                                VariableControl.AppendValue(
                                        new Variable("_jfdfR%var(_jfdfRP)", Variable.Scope.NORMAL),
                                        objectDataAddition
                                );
                            }

                            stack.remove(stack.size() - 1);
                            return;
                        }
                    }

                    stack.remove(stack.size() - argsCount - 1);

                    if(!JFDFCompiler.useNextPatchFeatures) VariableControl.Set(new Variable("_jfdfPFD", Variable.Scope.LOCAL), new Variable("_jfdfFD", Variable.Scope.LOCAL));
                    VariableControl.Increment(new Variable("_jfdfFD", Variable.Scope.LOCAL));

                    for (int i = 1; i <= argsCount; i++) {
                        CodeValue arg = stack.remove(stack.size() - (argsCount - i) - 1).getTransformedValue();

                        String replaceValue = JFDFCompiler.useNextPatchFeatures
                                ? "%math(%var(_jfdfFD) - 1)"
                                : "%var(_jfdfPFD)";

                        if(arg instanceof Variable && ((Variable) arg).getName().contains("%var(_jfdfFD)")) {
                            ((Variable) arg).setName(((Variable) arg).getName().replace("%var(_jfdfFD)", replaceValue));
                        } else if(arg instanceof Number) {
                            ((Number) arg).SetToString(((Number) arg).getValue().replace("%var(_jfdfFD)", replaceValue));
                        } else if(arg instanceof Text) {
                            ((Text) arg).Set(((Text) arg).Get().replace("%var(_jfdfFD)", replaceValue));
                        }

                        VariableControl.Set(
                                new Variable("_jfdffa>%var(_jfdfFD)>" + i, Variable.Scope.LOCAL),
                                arg
                        );
                    }

                    String callDescriptor = descriptor;

                    try {
                        MethodWrapper invokeMethod = MethodWrapper.getWrapper(Class.forName(owner.replace('/', '.')), name, callDescriptor);

                        if (invokeMethod.getAnnotation(MethodFallback.class) != null) {
                            callDescriptor = invokeMethod.getAnnotation(MethodFallback.class).descriptor();
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException e) {
                        throw new RuntimeException("Something went wrong.", e);
                    }

                    Functions.Call("_jfdf>" + owner + "><init>>" + callDescriptor);
                    return;
                }

                if(!JFDFCompiler.useNextPatchFeatures) VariableControl.Set(new Variable("_jfdfPFD", Variable.Scope.LOCAL), new Variable("_jfdfFD", Variable.Scope.LOCAL));
                VariableControl.Increment(new Variable("_jfdfFD", Variable.Scope.LOCAL));

                for (int i = 0; i <= argsCount; i++) {
                    CodeValue arg = stack.remove(stack.size() - (argsCount - i) - 1).getTransformedValue();

                    String replaceValue = JFDFCompiler.useNextPatchFeatures
                            ? "%math(%var(_jfdfFD) - 1)"
                            : "%var(_jfdfPFD)";

                    if(arg instanceof Variable && ((Variable) arg).getName().contains("%var(_jfdfFD)")) {
                        ((Variable) arg).setName(((Variable) arg).getName().replace("%var(_jfdfFD)", replaceValue));
                    } else if(arg instanceof Number) {
                        ((Number) arg).SetToString(((Number) arg).getValue().replace("%var(_jfdfFD)", replaceValue));
                    } else if(arg instanceof Text) {
                        ((Text) arg).Set(((Text) arg).Get().replace("%var(_jfdfFD)", replaceValue));
                    }

                    VariableControl.Set(
                            new Variable("_jfdffa>%var(_jfdfFD)>" + i, Variable.Scope.LOCAL),
                            arg
                    );
                }

                String callDescriptor = descriptor;

                try {
                    MethodWrapper invokeMethod = MethodWrapper.getWrapper(Class.forName(owner.replace('/', '.')), name, callDescriptor);

                    if (invokeMethod.getAnnotation(MethodFallback.class) != null) {
                        callDescriptor = invokeMethod.getAnnotation(MethodFallback.class).descriptor();
                    }
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    throw new RuntimeException("Something went wrong.", e);
                }

                Functions.Call("_jfdf>" + owner + ">" + name + ">" + callDescriptor);

                String returnDescriptor = Type.getReturnType(descriptor).getDescriptor();
                if(!returnDescriptor.equals("V")) {
                    stack.add(new VariableStackValue(returnDescriptor, "_functionr%var(_jfdfRF)"));
                }

                return;
            }
            case Opcodes.INVOKESTATIC -> {
                int argsCount = Type.getArgumentTypes(descriptor).length;

                if(owner.equals("net/jfdf/compiler/util/CodeValueArrayBuilder")
                        && name.equals("start")) {
                    int length = ((NumberStackValue) stack.remove(stack.size() - 1)).getJavaValue().intValue();
                    stack.add(new CodeArrayStackValue(length));
                } else if(owner.equals("net/jfdf/jfdf/mangement/Player")
                        || owner.equals("net/jfdf/jfdf/mangement/If$Player")) {
                    stack.add(new SpecialStackValue(
                            switch (name) {
                                case "getCurrentSelection" -> SpecialStackValue.SpecialValueType.PLAYER_CURRENT_SELECTION;
                                case "getDefault" -> SpecialStackValue.SpecialValueType.PLAYER_DEFAULT;
                                case "getKiller" -> SpecialStackValue.SpecialValueType.PLAYER_KILLER;
                                case "getDamager" -> SpecialStackValue.SpecialValueType.PLAYER_DAMAGER;
                                case "getShooter" -> SpecialStackValue.SpecialValueType.PLAYER_SHOOTER;
                                case "getVictim" -> SpecialStackValue.SpecialValueType.PLAYER_VICTIM;
                                case "getAllPlayers" -> SpecialStackValue.SpecialValueType.PLAYER_ALL_PLAYERS;
                                default -> throw new IllegalStateException("Unknown Player class static method \"" + name + "\".");
                            }
                    ));
                } else if(owner.equals("net/jfdf/jfdf/management/Entity")
                        || owner.equals("net/jfdf/jfdf/mangement/If$Entity")) {
                    stack.add(new SpecialStackValue(
                            switch (name) {
                                case "getCurrentSelection" -> SpecialStackValue.SpecialValueType.ENTITY_CURRENT_SELECTION;
                                case "getDefault" -> SpecialStackValue.SpecialValueType.ENTITY_DEFAULT;
                                case "getKiller" -> SpecialStackValue.SpecialValueType.ENTITY_KILLER;
                                case "getDamager" -> SpecialStackValue.SpecialValueType.ENTITY_DAMAGER;
                                case "getShooter" -> SpecialStackValue.SpecialValueType.ENTITY_SHOOTER;
                                case "getVictim" -> SpecialStackValue.SpecialValueType.ENTITY_VICTIM;
                                case "getProjectile" -> SpecialStackValue.SpecialValueType.ENTITY_PROJECTILE;
                                case "getAllEntities" -> SpecialStackValue.SpecialValueType.ENTITY_ALL_ENTITIES;
                                case "getAllMobs" -> SpecialStackValue.SpecialValueType.ENTITY_ALL_MOBS;
                                case "getLastEntitySpawned" -> SpecialStackValue.SpecialValueType.ENTITY_LAST_ENTITY_SPAWNED;
                                case "getLastMobSpawned" -> SpecialStackValue.SpecialValueType.ENTITY_LAST_MOB_SPAWNED;
                                default -> throw new IllegalStateException("Unknown Entity class static method \"" + name + "\".");
                            }
                    ));
                } else if(owner.startsWith("net/jfdf/jfdf/mangement/")) {
                    if(method.isStaticInitializer()) {
                        if(owner.equals("net/jfdf/jfdf/mangement/Control")) {
                            if(name.equals("Wait")) {
                                throw new IllegalStateException("Control: Wait is not allowed inside static initializer.");
                            } else if(name.equals("End")) {
                                throw new IllegalStateException("Control: End is not allowed inside static initializer.");
                            }
                        }
                    }

                    Object[] methodArgs = new Object[argsCount];
                    Type[] argumentTypes = Type.getArgumentTypes(descriptor);

                    for (int i = argsCount - 1; i >= 0; i--) {
                        IStackValue stackValue = stack.remove(stack.size() - 1);

                        if(stackValue instanceof CodeArrayStackValue) {
                            try {
                                Class<?> arrayClass = Class.forName(argumentTypes[i].getElementType().getClassName()).arrayType();
                                CodeValue[] array = ((CodeArrayStackValue) stackValue).getValues();

                                methodArgs[i] = Arrays.copyOf(array, array.length, (Class<? extends CodeValue[]>) arrayClass);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException("Something went wrong.", e);
                            }
                        } else if(stackValue instanceof EnumStackValue) {
                            methodArgs[i] = ((EnumStackValue) stackValue).getEnumValue();
                        } else {
                            if((name.equals("Call") || name.equals("CallWithArgs"))
                                    && owner.equals("net/jfdf/jfdf/mangement/Functions")
                                    && stackValue instanceof TextStackValue) {
                                methodArgs[i] = ((Text) stackValue.getTransformedValue()).Get();
                            } else {
                                if(stackValue instanceof NumberStackValue) {
                                    if(((NumberStackValue) stackValue).getJavaValue() == null) {
                                        methodArgs[i] = null;
                                        continue;
                                    } else if(argumentTypes[i] == Type.BOOLEAN_TYPE) {
                                        methodArgs[i] = (((NumberStackValue) stackValue).getJavaValue().intValue() == 1);
                                        continue;
                                    }
                                }

                                methodArgs[i] = stackValue.getTransformedValue();
                            }
                        }
                    }

                    try {
                        MethodWrapper.getWrapper(Class.forName(owner.replace('/', '.')), name, descriptor).invoke(null, methodArgs);
                    } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException e) {
                        throw new RuntimeException("Something went wrong.", e);
                    } catch (InvocationTargetException | InstantiationException | IllegalArgumentException e) {
                        throw new RuntimeException("Invalid value types during invoking \"" + name + descriptor + "\" of class \"" + owner + "\".\n"
                                + "Passed arguments: " + Arrays.deepToString(methodArgs) + "\n"
                                + "Invoked by : " + Type.getInternalName(class_) + ">" + method.getName() + method.getDescriptor() + ":" + lineNumber, e);
                    }

                    return;
                } else if(owner.equals("net/jfdf/compiler/util/ValueUtils")) {
                    if(name.equals("asReference")) {
                        IStackValue pointerStackValue = stack.remove(stack.size() - 1);
                        Variable reference;

                        if(pointerStackValue instanceof ReferencedStackValue) {
                            reference = ((ReferencedStackValue) pointerStackValue).getReference();
                        } else {
                            reference = new Variable("_jfdfR%var(" + ((Variable) pointerStackValue.getTransformedValue()).getName() + ")", Variable.Scope.NORMAL);
                        }

                        stack.add(new VariableStackValue("Ljava/lang/Object;", reference.getName(), Variable.Scope.NORMAL));
                    } else {
                        return;
                    }
                } else if((name.equals("valueOf") || name.endsWith("Value")) && owner.matches("java/lang/(Boolean|Byte|Short|Integer|Long|Character)")) {
                    if(name.equals("valueOf")) {
                        Variable value = getTempVariable();
                        VariableControl.CreateList(value, stack.remove(stack.size() - 1).getTransformedValue());

                        stack.add(new VariableStackValue("J", value.getName()));
                    } else {
                        stack.add(new MathFunctionStackValue((Variable) stack.remove(stack.size() - 1), new Number().Set(1), MathFunctionStackValue.MathFunction.LIST_VALUE));
                    }

                    return;
                } else if(owner.equals("java/util/Arrays")) {
                    if(name.equals("toString")) {
                        IStackValue stackValue = stack.remove(stack.size() - 1);

                        stack.add(new TextStackValue("%var(_jfdfR%var(" + ((Variable) stackValue.getTransformedValue()).getName() + "))"));
                    } else if(name.equals("asList")) {
                        return;
                    }
                } else {
                    if(!JFDFCompiler.useNextPatchFeatures) VariableControl.Set(new Variable("_jfdfPFD", Variable.Scope.LOCAL), new Variable("_jfdfFD", Variable.Scope.LOCAL));
                    VariableControl.Increment(new Variable("_jfdfFD", Variable.Scope.LOCAL));

                    for (int i = 0; i < argsCount; i++) {
                        CodeValue arg = stack.remove(stack.size() - (argsCount - i)).getTransformedValue();

                        String replaceValue = JFDFCompiler.useNextPatchFeatures
                                ? "%math(%var(_jfdfFD) - 1)"
                                : "%var(_jfdfPFD)";

                        if(arg instanceof Variable && ((Variable) arg).getName().contains("%var(_jfdfFD)")) {
                            ((Variable) arg).setName(((Variable) arg).getName().replace("%var(_jfdfFD)", replaceValue));
                        } else if(arg instanceof Number) {
                            ((Number) arg).SetToString(((Number) arg).getValue().replace("%var(_jfdfFD)", replaceValue));
                        } else if(arg instanceof Text) {
                            ((Text) arg).Set(((Text) arg).Get().replace("%var(_jfdfFD)", replaceValue));
                        }

                        VariableControl.Set(
                                new Variable("_jfdffa>%var(_jfdfFD)>" + i, Variable.Scope.LOCAL),
                                arg
                        );
                    }

                    String callDescriptor = descriptor;

                    try {
                        MethodWrapper invokeMethod = MethodWrapper.getWrapper(Class.forName(owner.replace('/', '.')), name, callDescriptor);

                        if (invokeMethod.getAnnotation(MethodFallback.class) != null) {
                            callDescriptor = invokeMethod.getAnnotation(MethodFallback.class).descriptor();
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException e) {
                        throw new RuntimeException("Something went wrong.", e);
                    }

                    Functions.Call("_jfdf>" + owner + ">" + name + ">" + callDescriptor);

                    String returnDescriptor = Type.getReturnType(descriptor).getDescriptor();
                    if (!returnDescriptor.equals("V")) {
                        stack.add(new VariableStackValue(returnDescriptor, "_functionr%var(_jfdfRF)"));
                    }
                }

                return;
            }
        }

        throw new IllegalStateException("Trying to call not supported method !\n" +
                "Invoked Class: " + owner.replace('/', '.') + "\n" +
                "Invoked Method: " + name + descriptor + "\n" +
                "Invoke Opcode: " + opcode);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        if(compileWithExecute) return;
        instructionIndex++;

        if(opcode == Opcodes.NEW) {
            if(type.equals("java/util/List") || type.equals("java/util/ArrayList")) {
                stack.add(new ListStackValue(type, method.getName(), blockOperationIndex++));
            } else if(type.startsWith("net/jfdf/jfdf/values/")) {
                stack.add(new CodeStackValue(type));
            } else {
                stack.add(new ObjectStackValue(type, method.getName(), blockOperationIndex++));
            }
        } else if(opcode == Opcodes.ANEWARRAY) {
            IStackValue arrayLength = stack.remove(stack.size() - 1);

            stack.add(new ArrayStackValue("L" + type + ";", arrayLength, method.getName(), blockOperationIndex++));
        } else if(opcode == Opcodes.CHECKCAST) {
            if(stack.get(stack.size() - 1) instanceof VariableStackValue) {
                VariableStackValue variableStackValue = (VariableStackValue) stack.get(stack.size() - 1);
                Variable variable = (Variable) variableStackValue.getTransformedValue();

                stack.set(stack.size() - 1, new VariableStackValue(type, variable.getName(), variable.getScope()));
            }
        }

        super.visitTypeInsn(opcode, type);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        if(compileWithExecute) return;
        instructionIndex++;

        switch (opcode) {
            case Opcodes.GETSTATIC -> {
                if(owner.startsWith("net/jfdf/jfdf/values/") || owner.startsWith("net/jfdf/jfdf/blocks")) {
                    try {
                        Object tag = Class.forName(owner.replace('/', '.')).getField(name).get(null);
                        stack.add(new EnumStackValue(tag));
                    } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
                        throw new RuntimeException("Something went wrong.", e);
                    }
                    // Checks if field's owner is java.lang.System
                } else if(owner.equals("java/lang/System")) {
                    if(name.equals("out")) {

                        // Pushes stdout stream to stack as special value
                        stack.add(new SpecialStackValue(SpecialStackValue.SpecialValueType.SYSTEM_OUT));
                    } else if(name.equals("err")) {

                        // Pushes stderr stream to stack as special value
                        stack.add(new SpecialStackValue(SpecialStackValue.SpecialValueType.SYSTEM_ERR));
                    } else {
                        throw new IllegalStateException("You can't access any fields from java.lang.System except \"out\" and \"err\".");
                    }
                } else {
                    try {
                        Class<?> fieldClass = Class.forName(owner.replace('/', '.'));

                        Field field = null;
                        while(field == null && fieldClass != Object.class) {
                            try{
                                field = fieldClass.getDeclaredField(name);
                            } catch (NoSuchFieldException e) {
                                fieldClass = fieldClass.getSuperclass();
                            }
                        }

                        if(field == null) {
                            throw new NoSuchFieldException("Couldn't find static field \"" + name + "\" inside \"" + owner + "\".");
                        }

                        stack.add(
                                new VariableStackValue(
                                        descriptor,
                                        "_jfdf>" + Type.getInternalName(fieldClass) + ">" + name,
                                        field.getAnnotation(Saved.class) == null
                                                ? Variable.Scope.NORMAL : Variable.Scope.SAVED
                                )
                        );
                    } catch (ClassNotFoundException | NoSuchFieldException e) {
                        throw new RuntimeException("Something went wrong.", e);
                    }
                }
            }
            case Opcodes.GETFIELD -> {
                try {
                    IStackValue objectStackValue = stack.remove(stack.size() - 1);
                    Variable reference;

                    if(objectStackValue instanceof ReferencedStackValue) {
                        reference = ((ReferencedStackValue) objectStackValue).getReference();
                    } else {
                        reference = new Variable("_jfdfR%var(" + ((Variable) objectStackValue.getTransformedValue()).getName() + ")", Variable.Scope.NORMAL);
                    }

                    Class<?> fieldClass = Class.forName(owner.replace('/', '.'));
                    int fieldIndex = FieldsManager.getFieldIndex(fieldClass, name);

                    if(descriptor.equals("Ljava/lang/String;")) {
                        stack.add(
                                new TextStackValue(
                                        "%index(" + reference.getName() + ", " + fieldIndex + ")"
                                )
                        );
                    } else if(descriptor.matches("[ZBSIJFDC]")) {
                        stack.add(
                                new MathFunctionStackValue(
                                        reference,
                                        new Number().Set(fieldIndex),
                                        MathFunctionStackValue.MathFunction.LIST_VALUE
                                )
                        );
                    } else {
                        Variable value = getTempVariable();

                        VariableControl.GetListValue(
                                value,
                                reference,
                                new Number().Set(fieldIndex)
                        );

                        stack.add(new VariableStackValue(descriptor, value.getName()));
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Something went wrong.", e);
                }
            }
            case Opcodes.PUTSTATIC -> {
                try {
                    Class<?> fieldClass = Class.forName(owner.replace('/', '.'));
                    Field field = fieldClass.getDeclaredField(name);

                    IStackValue value = stack.remove(stack.size() - 1);
                    boolean isNewValue = value instanceof ReferencedStackValue;

                    // Checks if value is a new reference object
                    if(isNewValue) {
                        // Changes reference's variable to this variable
                        ((ReferencedStackValue) value).setAllocationVariable("_jfdf>" + owner + ">" + name, Variable.Scope.NORMAL);
                    } else {
                        Variable valuePointer = new Variable(
                                "_jfdf>" + owner + ">" + name,
                                field.getAnnotation(Saved.class) == null
                                        ? Variable.Scope.NORMAL : Variable.Scope.SAVED
                        );

                        ReferenceUtils.decrementIfReference(descriptor, valuePointer);

                        // Sets this variable to a value
                        VariableControl.Set(
                                valuePointer, value.getTransformedValue()
                        );

                        ReferenceUtils.incrementIfReference(descriptor, valuePointer);
                    }
                } catch (ClassNotFoundException | NoSuchFieldException e) {
                    throw new RuntimeException("Something went wrong.", e);
                }
            }
            case Opcodes.PUTFIELD -> {
                try {
                    IStackValue objectStackValue = stack.remove(stack.size() - 2);
                    Variable reference;

                    if(objectStackValue instanceof ReferencedStackValue) {
                        reference = ((ReferencedStackValue) objectStackValue).getReference();
                    } else {
                        reference = new Variable("_jfdfR%var(" + ((Variable) objectStackValue.getTransformedValue()).getName() + ")", Variable.Scope.NORMAL);
                    }

                    Class<?> fieldClass = Class.forName(owner.replace('/', '.'));
                    int fieldIndex = FieldsManager.getFieldIndex(fieldClass, name);

                    IStackValue newValue = stack.remove(stack.size() - 1);

                    Number valuePointer = Number.AsListValue(reference, new Number().Set(fieldIndex));
                    ReferenceUtils.decrementIfReference(descriptor, valuePointer);

                    // Sets this variable to a value
                    VariableControl.SetListValue(
                            reference,
                            new Number().Set(fieldIndex),
                            newValue.getTransformedValue()
                    );

                    ReferenceUtils.incrementIfReference(descriptor, valuePointer);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Something went wrong.", e);
                }
            }
        }
    }

    @Override
    public void visitLdcInsn(Object value) {
        if(compileWithExecute) return;
        instructionIndex++;

        if(value.getClass() == String.class) {
            stack.add(new TextStackValue((String) value));
        } else if(value.getClass() == Byte.class) {
            stack.add(new NumberStackValue((Byte) value));
        } else if(value.getClass() == Short.class) {
            stack.add(new NumberStackValue((Short) value));
        } else if(value.getClass() == Integer.class) {
            stack.add(new NumberStackValue((Integer) value));
        } else if(value.getClass() == Long.class) {
            stack.add(new NumberStackValue((Long) value));
        } else if(value.getClass() == Float.class) {
            stack.add(new NumberStackValue((Float) value));
        } else if(value.getClass() == Double.class) {
            stack.add(new NumberStackValue((Double) value));
        } else if(value.getClass() == Character.class) {
            stack.add(new NumberStackValue((Character) value));
        } else {
            throw new IllegalStateException("Unsupported constant value type: " + value.getClass().getName());
        }

        super.visitLdcInsn(value);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        if(compileWithExecute) return;
        instructionIndex++;

        throw new IllegalStateException("Try-catch statements are not supported !");
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        if(compileWithExecute) return;
        instructionIndex++;

        throw new IllegalStateException("Lookup switches are not supported !");
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        if(compileWithExecute) return;
        instructionIndex++;

        throw new IllegalStateException("Table switches are not supported !");
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        if(compileWithExecute) return;
        instructionIndex++;

        throw new IllegalStateException("Invoke dynamic is not supported !");
    }

    @Override
    public void visitIincInsn(int var, int increment) {
        if(compileWithExecute) return;
        instructionIndex++;

        String variableName;
        if((Type.getArgumentTypes(method.getDescriptor()).length + (method.isMember() ? 1 : 0)) > var) {
            variableName = "_jfdffa>%var(_jfdfFD)>" + var;
        } else {
            variableName = "_jfdffv>%var(_jfdfFD)>" + var;
        }

        IStackValue newVariableValue = null;
        for (int i = 0; i < stack.size(); i++) {
            IStackValue stackValue = stack.get(i);

            if(stackValue instanceof VariableStackValue) {
                VariableStackValue varStackValue = (VariableStackValue) stackValue;
                Variable variable = (Variable) varStackValue.getTransformedValue();

                if(variable.getName().equals(variableName)) {
                    if(newVariableValue == null) {
                        String newVariableName = getTempVariableName();
                        newVariableValue = new VariableStackValue("J", newVariableName);

                        VariableControl.Set(
                                new Variable(newVariableName, Variable.Scope.LOCAL),
                                variable
                        );
                    }

                    stack.set(i, newVariableValue);
                }
            }
        }

        VariableControl.Increment(
                new Variable(
                        variableName,
                        Variable.Scope.LOCAL
                ), new Number().Set(increment)
        );

        super.visitIincInsn(var, increment);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        if(compileWithExecute) return;
        instructionIndex++;

        switch (opcode) {
            case Opcodes.BIPUSH, Opcodes.SIPUSH -> stack.add(new NumberStackValue(operand));
            case Opcodes.NEWARRAY -> {
                IStackValue arrayLength = stack.remove(stack.size() - 1);

                String descriptor = switch (operand) {
                    case Opcodes.T_BOOLEAN -> "Z";
                    case Opcodes.T_CHAR -> "C";
                    case Opcodes.T_FLOAT -> "F";
                    case Opcodes.T_DOUBLE -> "D";
                    case Opcodes.T_BYTE -> "B";
                    case Opcodes.T_SHORT -> "S";
                    case Opcodes.T_INT -> "I";
                    case Opcodes.T_LONG -> "J";
                    default -> throw new IllegalStateException("Unknown primitive array type: " + operand);
                };

                stack.add(new ArrayStackValue(descriptor, arrayLength, method.getName(), blockOperationIndex++));
            }
            default -> throw new IllegalStateException("Unknown opcode: " + opcode);
        }

        super.visitIntInsn(opcode, operand);
    }

    @Override
    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        if(compileWithExecute) return;
        instructionIndex++;

        throw new IllegalStateException("Multi-dimensional arrays are not supported !");
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        if (compileWithExecute) return;
        instructionIndex++;

        boolean whileRepeat = false;
        boolean invertIf = false;

        boolean continueIf = false;
        boolean breakIf = false;

        int jumpToIndex = ((JumpInstructionData) instructionDataList.get(instructionIndex)).jumpToInstructionIndex;
        if (opcode >= Opcodes.IFEQ && opcode <= Opcodes.IF_ACMPNE) {
            if(endBracketIndices.contains(-jumpToIndex)) {
                if(endBracketIndices.get(endBracketIndices.size() - 1) == -jumpToIndex) {
                    breakIf = true;
                    invertIf = true;
                } else {
                    throw new IllegalStateException("break label is not supported !");
                }
            } else {
                InstructionData beforeLabelInsn = instructionDataList.get(jumpToIndex - 1);

                if (beforeLabelInsn.instructionOpcode == Opcodes.GOTO) {
                    JumpInstructionData gotoInsn = (JumpInstructionData) beforeLabelInsn;

                    if (gotoInsn.jumpToInstructionIndex == labelInstructionIndex) {
                        whileRepeat = true;
                    }
                }
            }

            if(startRepeatBracketIndices.contains(jumpToIndex)) {
                if(startRepeatBracketIndices.get(startRepeatBracketIndices.size() - 1) == jumpToIndex) {
                    continueIf = true;
                    invertIf = true;
                } else {
                    throw new IllegalStateException("continue label is not supported !");
                }
            }
        }

        switch (opcode) {
            case Opcodes.IF_ICMPEQ,
                    Opcodes.IF_ACMPEQ,
                    Opcodes.IF_ICMPNE,
                    Opcodes.IF_ACMPNE,
                    Opcodes.IF_ICMPGT,
                    Opcodes.IF_ICMPLT,
                    Opcodes.IF_ICMPGE,
                    Opcodes.IF_ICMPLE -> {
                String ifType = switch (opcode) {
                    case Opcodes.IF_ICMPEQ, Opcodes.IF_ACMPEQ -> "!=";
                    case Opcodes.IF_ICMPNE, Opcodes.IF_ACMPNE -> "=";
                    case Opcodes.IF_ICMPGT -> "<=";
                    case Opcodes.IF_ICMPLT -> ">=";
                    case Opcodes.IF_ICMPGE -> "<";
                    case Opcodes.IF_ICMPLE -> ">";
                    default -> throw new IllegalStateException("Unexpected opcode: " + opcode);
                };

                if(whileRepeat) {
                    CodeManager.instance.addCodeBlock(new RepeatBlock(ifType, invertIf)
                            .SetItems(
                                    stack.remove(stack.size() - 2).getTransformedValue(),
                                    stack.remove(stack.size() - 1).getTransformedValue()
                            )
                    );
                } else {
                    CodeManager.instance.addCodeBlock(new IfVariableBlock(ifType, invertIf)
                            .SetItems(
                                    stack.remove(stack.size() - 2).getTransformedValue(),
                                    stack.remove(stack.size() - 1).getTransformedValue()
                            )
                    );
                }

                CodeManager.instance.addCodeBlock(new BracketBlock(false, whileRepeat));
            }
            case Opcodes.IFEQ,
                    Opcodes.IFNE,
                    Opcodes.IFLT,
                    Opcodes.IFGE,
                    Opcodes.IFGT,
                    Opcodes.IFLE -> {
                IStackValue stackValue = stack.remove(stack.size() - 1);

                String ifType = switch (opcode) {
                    case Opcodes.IFEQ -> "!=";
                    case Opcodes.IFNE -> "=";
                    case Opcodes.IFLT -> ">=";
                    case Opcodes.IFGE -> "<";
                    case Opcodes.IFGT -> "<=";
                    case Opcodes.IFLE -> ">";
                    default -> throw new IllegalStateException("Unexpected opcode: " + opcode);
                };

                CodeValue[] items;

                if(stackValue instanceof CompareStackValue) {
                    CompareStackValue.CompareType compareType = ((CompareStackValue) stackValue).getCompareType();
                    items = new CodeValue[] {
                            ((CompareStackValue) stackValue).getStackValue1().getTransformedValue(),
                            ((CompareStackValue) stackValue).getStackValue2().getTransformedValue()
                    };

                    if(compareType == CompareStackValue.CompareType.NORMAL) {
                        invertIf = !invertIf;
                    } else if(compareType == CompareStackValue.CompareType.LIST_CONTAINS) {
                        ifType = "ListContains";
                        invertIf = (opcode == Opcodes.IFNE) != invertIf;
                    }
                } else {
                    items = new CodeValue[] {
                            stackValue.getTransformedValue(),
                            new Number().Set(0)
                    };
                }

                if(whileRepeat) {
                    CodeManager.instance.addCodeBlock(new RepeatBlock(ifType, invertIf)
                            .SetItems(items)
                    );
                } else {
                    CodeManager.instance.addCodeBlock(new IfVariableBlock(ifType, invertIf)
                            .SetItems(items)
                    );
                }

                CodeManager.instance.addCodeBlock(new BracketBlock(false, whileRepeat));
            }
            case Opcodes.GOTO -> {
                if (startRepeatBracketIndices.contains(jumpToIndex)
                        && endBracketIndices.contains(-(instructionIndex + 1))) {
                    startRepeatBracketIndices.remove((Object) jumpToIndex);
                    return;
                } else if(startRepeatBracketIndices.contains(jumpToIndex)) {
                    if(startRepeatBracketIndices.get(startRepeatBracketIndices.size() - 1) == jumpToIndex) {
                        Control.Skip();
                    } else {
                        throw new IllegalStateException("continue label is not supported !");
                    }

                    return;
                } else if(endBracketIndices.contains(-jumpToIndex)) {
                    if(endBracketIndices
                            .stream()
                            .filter(integer -> integer < 0)
                            .max(Comparator.naturalOrder())
                            .orElse(0) == -jumpToIndex) {
                        Control.StopRepeat();
                    } else {
                        throw new IllegalStateException("break label is not supported !");
                    }

                    return;
                }
            }
        }

        // Checks if instruction is if instruction
        if (opcode >= Opcodes.IFEQ && opcode <= Opcodes.IF_ACMPNE) {
            if(breakIf) {
                Control.StopRepeat();
                If.End();
            } else if(continueIf) {
                Control.Skip();
                If.End();
            } else {
                if (whileRepeat) startRepeatBracketIndices.add(labelInstructionIndex);

                // Adds end bracket's label index (If: Positive index, Repeat: Negative index)
                endBracketIndices.add(whileRepeat ? -jumpToIndex : jumpToIndex);
            }

            return;
        }

        throw new IllegalStateException("Jump instructions are not supported ! (Opcode: " + opcode + ")\n"
                + "Method: " + Type.getInternalName(class_) + ">" + method.getName() + method.getDescriptor() + ":" + lineNumber);
    }

    private Variable getTempVariable() {
        return new Variable("_jco>" + method.getName() + ">" + (blockOperationIndex++), Variable.Scope.LOCAL);
    }

    private String getTempVariableName() {
        return "_jco>" + method.getName() + ">" + (blockOperationIndex++);
    }
}
