package net.botwithus.rs3.cs2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScriptDescriptor {

    private final List<Layout> args;
    private final List<Layout> returns;

    private ScriptDescriptor(List<Layout> args, List<Layout> returns) {
        this.args = args;
        this.returns = returns;
    }

    public List<Layout> getArgs() {
        return Collections.unmodifiableList(args);
    }

    public List<Layout> getReturns() {
        return Collections.unmodifiableList(returns);
    }

    public static ScriptDescriptor of(List<Layout> returns, Layout... args) {
        return new ScriptDescriptor(Arrays.stream(args).toList(), returns);
    }

    public static ScriptDescriptor ofVoid(Layout... args) {
        return new ScriptDescriptor(Arrays.stream(args).toList(), List.of());
    }
}
