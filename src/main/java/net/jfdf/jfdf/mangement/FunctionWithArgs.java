package net.jfdf.jfdf.mangement;

import net.jfdf.jfdf.values.Item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionWithArgs {
    public String name() default "<Not Named>";
    public boolean isHidden() default false;

    public String iconId() default "lapis_lazuli";
    public String iconNbt() default "";
}