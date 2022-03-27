package net.jfdf.addon.random;

import net.jfdf.compiler.ICompilerAddon;
import net.jfdf.compiler.data.stack.IStackValue;
import net.jfdf.compiler.data.stack.MathFunctionStackValue;
import net.jfdf.compiler.data.stack.MathStackValue;
import net.jfdf.compiler.data.stack.NumberStackValue;
import net.jfdf.jfdf.values.INumber;
import net.jfdf.jfdf.values.Number;
import org.objectweb.asm.Type;

import java.util.List;
import java.util.Random;

public class RandomAddon implements ICompilerAddon {
    @Override
    public boolean onInitClass(String type, List<IStackValue> stack) {
        if(type.equals("java/util/Random")) {
            stack.add(new NumberStackValue(0));

            return true;
        }

        return false;
    }

    @Override
    public boolean onInvokeConstructor(String type, String descriptor, List<IStackValue> stack) {
        if(type.equals("java/util/Random")) {
            if(descriptor.equals("(J)V")) {
                stack.remove(stack.size() - 1);
            }

            stack.remove(stack.size() - 1);
            return true;
        }

        return false;
    }

    @Override
    public boolean onInvokeMember(String owner, String name, String descriptor, List<IStackValue> stack) {
        if(owner.equals("java/util/Random")) {
            stack.remove(stack.size() - (Type.getArgumentTypes(descriptor).length + 1));

            switch (name + descriptor) {
                case "nextBoolean()Z" ->
                        stack.add(
                                new MathFunctionStackValue(
                                        new Number().Set(0),
                                        new Number().Set(1),
                                        MathFunctionStackValue.MathFunction.RANDOM
                                )
                        );
                case "nextInt()I" ->
                        stack.add(
                                new MathFunctionStackValue(
                                        new Number().Set(Integer.MIN_VALUE),
                                        new Number().Set(Integer.MAX_VALUE),
                                        MathFunctionStackValue.MathFunction.RANDOM
                                )
                        );
                case "nextInt(I)I" ->
                        stack.add(
                                new MathFunctionStackValue(
                                        new Number().Set(0),
                                        (INumber) stack.remove(stack.size() - 1).getTransformedValue(),
                                        MathFunctionStackValue.MathFunction.RANDOM
                                )
                        );
                case "nextLong()J" ->
                        stack.add(
                                new MathFunctionStackValue(
                                        new Number().Set(Long.MIN_VALUE / 1000),
                                        new Number().Set(Long.MAX_VALUE / 1000),
                                        MathFunctionStackValue.MathFunction.RANDOM
                                )
                        );
                case "nextFloat()F",
                        "nextDouble()D" ->
                        stack.add(
                                new MathStackValue(
                                        Number.Random(
                                                new Number().Set(0),
                                                new Number().Set(1000)
                                        ),
                                        new Number().Set(1000),
                                        MathStackValue.MathOperation.DIVIDE
                                )
                        );

                default -> throw new IllegalStateException("Unsupported java.lang.Random method: " + name + descriptor + ".");
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean onInvokeStatic(String owner, String name, String descriptor, List<IStackValue> stack) {
        return false;
    }
}
