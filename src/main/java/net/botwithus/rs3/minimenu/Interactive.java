package net.botwithus.rs3.minimenu;

import java.util.regex.Pattern;

public interface Interactive {

    boolean interact();

    boolean interact(int option);

    boolean interact(String option);

    boolean interact(Pattern pattern);

}
