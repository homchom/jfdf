package net.jfdf.compiler.visitor;

import net.jfdf.compiler.data.instruction.InstructionData;
import net.jfdf.compiler.data.instruction.InstructionType;
import net.jfdf.compiler.data.instruction.JumpInstructionData;
import org.objectweb.asm.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompilerMethodAnalyzer extends MethodVisitor {
    private final List<InstructionData> instructionDataList;
    private int instructionIndex = -1;

    private final Map<Label, List<JumpInstructionData>> assignToLabels = new HashMap<>();
    private final Map<Label, Integer> labelInstructionIndices = new HashMap<>();

    public CompilerMethodAnalyzer(MethodVisitor methodVisitor, List<InstructionData> instructionDataList) {
        super(Opcodes.ASM9, methodVisitor);

        this.instructionDataList = instructionDataList;
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = opcode;
        instructionData.instructionType = InstructionType.NO_ARGUMENTS;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        ++instructionIndex;

        JumpInstructionData instructionData = new JumpInstructionData();
        instructionData.instructionOpcode = opcode;
        instructionData.instructionType = InstructionType.JUMP;

        if(!assignToLabels.containsKey(label)) {
            assignToLabels.put(label, new ArrayList<>());
        }

        assignToLabels.get(label).add(instructionData);

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitLabel(Label label) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = -1;
        instructionData.instructionType = InstructionType.LABEL;

        labelInstructionIndices.put(label, instructionIndex);
        instructionDataList.add(instructionData);
    }

    @Override
    public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = -2;
        instructionData.instructionType = InstructionType.FRAME;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = -3;
        instructionData.instructionType = InstructionType.LINE_NUMBER;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = opcode;
        instructionData.instructionType = InstructionType.FIELD;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = opcode;
        instructionData.instructionType = InstructionType.VARIABLE;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = opcode;
        instructionData.instructionType = InstructionType.INTEGER;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitIincInsn(int var, int increment) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = Opcodes.IINC;
        instructionData.instructionType = InstructionType.INTEGER_INCREMENT;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = opcode;
        instructionData.instructionType = InstructionType.INVOKE;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = Opcodes.INVOKEDYNAMIC;
        instructionData.instructionType = InstructionType.INVOKE_DYNAMIC;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitLdcInsn(Object value) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = Opcodes.LDC;
        instructionData.instructionType = InstructionType.LDC;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = opcode;
        instructionData.instructionType = InstructionType.TYPE;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = Opcodes.MULTIANEWARRAY;
        instructionData.instructionType = InstructionType.MULTI_ANEW_ARRAY;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = -4;
        instructionData.instructionType = InstructionType.TRY_CATCH;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = -5;
        instructionData.instructionType = InstructionType.TABLE_SWITCH;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = -6;
        instructionData.instructionType = InstructionType.LOOKUP_SWITCH;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        instructionIndex++;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = -7;
        instructionData.instructionType = InstructionType.MAXS;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        ++instructionIndex;

        InstructionData instructionData = new InstructionData();
        instructionData.instructionOpcode = -8;
        instructionData.instructionType = InstructionType.LOCAL_VARIABLE;

        instructionDataList.add(instructionData);
    }

    @Override
    public void visitEnd() {
        for(Map.Entry<Label, List<JumpInstructionData>> entry : assignToLabels.entrySet()) {
            int labelInstructionIndex = labelInstructionIndices.get(entry.getKey());

            List<JumpInstructionData> jumpInstructions = entry.getValue();
            for (JumpInstructionData instructionData : jumpInstructions) {
                instructionData.jumpToInstructionIndex = labelInstructionIndex;
            }
        }

        super.visitEnd();
    }
}
