package net.botwithus.rs3.cache.assets.cs2;

public class JumpInstruction extends Instruction {
    private final int jump;

    public JumpInstruction(int opcode, int jump) {
        super(opcode);
        this.jump = jump;
    }

    public int jump() {
        return jump;
    }
}
