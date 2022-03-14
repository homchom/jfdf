package net.jfdf.jfdf.mangement;

import java.lang.reflect.Method;

import net.jfdf.jfdf.blocks.CallFunctionBlock;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Number;
import net.jfdf.jfdf.values.Variable.Scope;

public class Functions {
    public static void Call(String name) {
        CodeManager.instance.addCodeBlock(new CallFunctionBlock(name));
    }

    public static void Call(Text name) {
        CodeManager.instance.addCodeBlock(new CallFunctionBlock(name));
    }

    public static void CallWithArgs(String name, CodeValue... args) {
        if(args.length != 0) {
            boolean isFound = false;

            for (Method function : CodeManager.instance.getFunctionsWithArgs()) {
                String funcName = ((FunctionWithArgs) function.getAnnotation(FunctionWithArgs.class)).name();
                if (((FunctionWithArgs) function.getAnnotation(FunctionWithArgs.class)).name().equals(name)) {
                    isFound = true;
                    Class<?>[] functionArgs = function.getParameterTypes();

                    if(args.length != functionArgs.length) {
                        throw new IllegalArgumentException("Provided args were " + args.length + " while required is " + functionArgs.length);
                    }

                    for (int i = 0; i < functionArgs.length; i++) {
                        Class<?> functionArg = functionArgs[i];
                        if (functionArg == Text.class) functionArg = IText.class;
                        else if (functionArg == Item.class) functionArg = IItem.class;
                        else if (functionArg == Location.class) functionArg = ILocation.class;
                        else if (functionArg == Number.class) functionArg = INumber.class;
                        else if (functionArg == Particle.class) functionArg = IParticle.class;
                        else if (functionArg == Potion.class) functionArg = IPotion.class;
                        else if (functionArg == Projectile.class) functionArg = IProjectile.class;
                        else if (functionArg == Sound.class) functionArg = ISound.class;
                        else if (functionArg == List.class) functionArg = Variable.class;

                        if (functionArg != Variable.class && !functionArg.isAssignableFrom(args[i].getClass()))
                            throw new IllegalArgumentException("Function with arguments called with incorrect arguements");

                        VariableControl.Set(CodeManager.instance.getVariable("_function_" + function.getName() + "_arg" + i, Scope.LOCAL), args[i]);
                    }

                    Call(name);
                    return;
                }
            }
        }

        CodeManager.instance.addCodeBlock(new CallFunctionBlock(name));
    }
}