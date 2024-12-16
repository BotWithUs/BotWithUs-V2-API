package net.botwithus.modules;

import net.botwithus.rs3.world.ClientState;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BotModuleInfo {

    String name();

    String author();

    String description();

    String version();

    ClientState[] states() default ClientState.GAME;

}
