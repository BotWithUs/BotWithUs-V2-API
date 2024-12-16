package net.botwithus.scripts;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // Restricts usage to class, interface, or enum declarations
@Retention(RetentionPolicy.RUNTIME) // Optional, specify when the annotation is retained (e.g., runtime, source)
public @interface Info {

    String name();

    String author();

    String description() default "";

    String version() default "1.0";

}
