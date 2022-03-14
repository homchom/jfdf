package net.jfdf.jfdf;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.jfdf.jfdf.blocks.CodeHeader;
import net.jfdf.jfdf.blocks.FunctionBlock;
import net.jfdf.jfdf.blocks.PlayerEventBlock;
import net.jfdf.jfdf.blocks.ProcessBlock;
import net.jfdf.jfdf.mangement.CodeManager;
import net.jfdf.jfdf.mangement.Function;
import net.jfdf.jfdf.mangement.FunctionWithArgs;
import net.jfdf.jfdf.mangement.PlayerEvent;
import net.jfdf.jfdf.mangement.VariableControl;
import net.jfdf.jfdf.mangement.CodeManager.PLAYER_RANK;
import net.jfdf.jfdf.mangement.CodeManager.PLOT_SIZE;
import net.jfdf.jfdf.values.*;
import net.jfdf.jfdf.values.Variable.Scope;
import net.jfdf.jfdf.mangement.Process;

public abstract class CodeExecutor extends CodeClass {

	public void execute() {
		CodeManager.instance.playerRank = getAuthorRank();
		CodeManager.instance.plotSize = getPlotSize();
		CodeManager.instance.setAuthor(getAuthor());
		JFDFAddon.addJFDFAddons(Arrays.asList(getAddons()));

		try {
			JFDFAddon.class.getDeclaredMethod("onStart").invoke(null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException ignored) {
		}

		List<Class<?>> codeClasses = new ArrayList<>();
		codeClasses.add(this.getClass());
		codeClasses.addAll(Arrays.asList(this.getExportedClasses()));

		for(Class<?> codeClass : codeClasses) {
			Method[] methods = codeClass.getMethods();

			for (Method classMethod : methods) {
				if (classMethod.getAnnotation(FunctionWithArgs.class) != null) {
					CodeManager.instance.addFunctionWithArgs(classMethod);
				}
			}
		}

		for(Class<?> codeClass : codeClasses) {
			Method[] methods = codeClass.getMethods();
			Object objInstance = null;

			if(!codeClass.isInterface()) {
				if (codeClass == this.getClass()) {
					objInstance = this;
				} else {
					try {
						Constructor<?>[] constructors = codeClass.getConstructors();

						if(constructors.length > 0) {
							objInstance = constructors[0].newInstance();
						}
					} catch (IllegalAccessException | InstantiationException | InvocationTargetException | IllegalArgumentException e) {
						System.err.println("Error while trying to create a constructor for " + codeClass.getName() + ":");
						e.printStackTrace();
					}
				}

				for (Method classMethod : methods) {
					CodeHeader codeHeader;
					if (classMethod.getAnnotation(Function.class) != null) {
						codeHeader = new FunctionBlock(classMethod.getAnnotation(Function.class).name()).SetTags(new Tag("Is Hidden", classMethod.getAnnotation(Function.class).isHidden() ? "True" : "False"));
						System.out.println("function block");
					} else if (classMethod.getAnnotation(FunctionWithArgs.class) != null) {
						FunctionWithArgs data = classMethod.getAnnotation(FunctionWithArgs.class);
						codeHeader = new FunctionBlock(data.name())
								.SetTags(new Tag("Is Hidden", data.isHidden() ? "True" : "False"));

						if (!data.iconId().equals("lapis_lazuli") || !data.iconNbt().equals(""))
							((FunctionBlock) codeHeader).SetItems(new Item(data.iconId(), 1, data.iconNbt()));
					} else if (classMethod.getAnnotation(Process.class) != null) {
						codeHeader = new ProcessBlock(classMethod.getAnnotation(Process.class).name()).SetTags(new Tag("Is Hidden", classMethod.getAnnotation(Process.class).isHidden() ? "True" : "False"));
					} else if (classMethod.getAnnotation(PlayerEvent.class) != null) {
						codeHeader = new PlayerEventBlock(classMethod.getAnnotation(PlayerEvent.class).eventType().getJSONValue());
					} else continue;

					CodeManager.instance.addCodeBlocks(codeHeader, new ArrayList<>());
					try {
						JFDFAddon.class.getDeclaredMethod("onHeaderStart").invoke(null);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException ignored) {
					}

					List<Variable> args = new ArrayList<>();

					if (classMethod.getAnnotation(FunctionWithArgs.class) != null) {
						for (Class<?> arg : classMethod.getParameterTypes()) {
							if (CodeValue.class.isAssignableFrom(arg)) {
								if (arg == net.jfdf.jfdf.values.List.class)
									args.add(new net.jfdf.jfdf.values.List(CodeManager.instance.getVariable("_function_" + classMethod.getName() + "_arg" + args.size(), Scope.LOCAL)));
								else
									args.add(CodeManager.instance.getVariable("_function_" + classMethod.getName() + "_arg" + args.size(), Scope.LOCAL));

								VariableControl.Set(CodeManager.instance.getVariable(args.get(args.size() - 1).getName() + "__saved", Scope.LOCAL), args.get(args.size() - 1));
							} else {
								throw new IllegalArgumentException("You can't use a function argument that not code value !");
							}
						}
					}

					try {
						classMethod.setAccessible(true);

						if (args.size() == 0) {
							classMethod.invoke(objInstance);
						} else {
							classMethod.invoke(objInstance, args.toArray());
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						if (e instanceof InvocationTargetException) {
							((InvocationTargetException) e).getTargetException().printStackTrace();
						}

						System.err.println("Class: " + codeClass);
						System.err.println("Method: " + classMethod.getName());

						e.printStackTrace();
						throw new RuntimeException("Every code header method should be without any arguments and without \"protected\" tag.");
					}

					if (classMethod.getAnnotation(FunctionWithArgs.class) != null) {
						for (Variable arg : args) {
							VariableControl.Set(CodeManager.instance.getVariable(arg.getName() + "__saved", Scope.LOCAL), arg);
						}

						CodeManager.instance.addFunctionWithArgs(classMethod);
					}

					try {
						JFDFAddon.class.getDeclaredMethod("onHeaderEnd", CodeHeader.class, List.class).invoke(null, codeHeader, CodeManager.instance.getCodeHeaderBlocks(codeHeader));
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException ignored) {
					}
				}
			}
		}
	}

	public abstract Class<? extends CodeClass>[] getCodeClasses();
	
	public abstract String getAuthor();
	
	public abstract PLOT_SIZE getPlotSize();
	
	public abstract PLAYER_RANK getAuthorRank();

	public abstract JFDFAddon[] getAddons();

	public Class<?>[] getExportedClasses() {
		return new Class<?>[] {};
	}

	public static String getGiveCommand() {
		return CodeManager.instance.getGiveCommandsAsList().get(0);
	}

	public static String getGiveCommand(int index) {
		return CodeManager.instance.getGiveCommandsAsList().get(index);
	}
}
