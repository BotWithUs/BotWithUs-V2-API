package net.botwithus.rs3.cs2.internal;

import net.botwithus.rs3.cs2.ScriptDescriptor;
import net.botwithus.rs3.cs2.ScriptHandle;

public abstract class ScriptLookup {

    public static ScriptLookup LOOKUP;

    public abstract ScriptHandle find(int id, ScriptDescriptor descriptor);

}
