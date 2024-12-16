package net.botwithus.rs3.cache.assets.cs2;

/**
 * {
 *     "scrambledOpcode": 1698,
 *     "rawOpcode": 0,
 *     "isInt": true,
 *     "name": "jag::game::ClientScriptOpCode::_push_constant_int",
 *     "callback": "",
 *     "args": {
 *       "int_count": 0,
 *       "long_count": 0,
 *       "string_count": 0
 *     },
 *     "rets": {
 *       "int_count": 0,
 *       "long_count": 0,
 *       "string_count": 0
 *     }
 *   }
 */
public class InstructionType {

    private int scrambledOpcode;
    private int rawOpcode;
    private boolean isInt;
    private String name;
    private String callback;

    private ArgumentType args;

    private ReturnType rets;

    public InstructionType() {
    }

    public int getScrambledOpcode() {
        return scrambledOpcode;
    }

    public int getRawOpcode() {
        return rawOpcode;
    }

    public boolean isInt() {
        return isInt;
    }

    public String getName() {
        return name;
    }

    public String getCallback() {
        return callback;
    }

    public ArgumentType getArgs() {
        return args;
    }

    public ReturnType getRets() {
        return rets;
    }

    public record ArgumentType(int intCount, int longCount, int stringCount) {
    }
    public record ReturnType(int intCount, int longCount, int stringCount) {
    }
}
