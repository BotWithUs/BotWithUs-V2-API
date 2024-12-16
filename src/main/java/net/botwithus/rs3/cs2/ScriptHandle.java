package net.botwithus.rs3.cs2;

import net.botwithus.rs3.cs2.internal.ScriptLookup;

import java.util.List;
import java.util.logging.Logger;

public class ScriptHandle {

    private static final Logger log = Logger.getLogger(ScriptHandle.class.getName());

    protected final ScriptDescriptor descriptor;
    protected final int scriptId;

    public ScriptHandle(int scriptId, ScriptDescriptor descriptor) {
        this.scriptId = scriptId;
        this.descriptor = descriptor;
    }

    public native List<Object> invoke(Object... args) throws IllegalStateException;

    public native List<Object> invokeExact(Object... args) throws IllegalStateException;

    public ScriptDescriptor getDescriptor() {
        return descriptor;
    }

    public int getScriptId() {
        return scriptId;
    }

    public static ScriptHandle of(int scriptId, ScriptDescriptor descriptor) {
        ScriptLookup lookup = ScriptLookup.LOOKUP;
        if(lookup == null) {
            log.warning("ScriptLookup.LOOKUP is null");
            return null;
        }
        return lookup.find(scriptId, descriptor);
    }
}
