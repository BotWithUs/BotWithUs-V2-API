package net.botwithus.rs3.cache.assets.cs2;

public class Instruction {

    private final int opcode;

    private String name;

    private int operand1;
    private int operand2;
    private int operand3;
    private long longOperand;
    private String stringOperand;

    private InstructionType type;


    public Instruction(int opcode) {
        this.opcode = opcode;
    }

    public int opcode() {
        return opcode;
    }

    public int getOperand1() {
        return operand1;
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public int getOperand3() {
        return operand3;
    }

    public void setOperand3(int operand3) {
        this.operand3 = operand3;
    }

    public long getLongOperand() {
        return longOperand;
    }

    public void setLongOperand(long longOperand) {
        this.longOperand = longOperand;
    }

    public String getStringOperand() {
        return stringOperand;
    }

    public void setStringOperand(String stringOperand) {
        this.stringOperand = stringOperand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstructionType getType() {
        return type;
    }

    public void setType(InstructionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "opcode=" + opcode +
                ", name='" + name + '\'' +
                ", operand1=" + operand1 +
                ", operand2=" + operand2 +
                ", operand3=" + operand3 +
                ", longOperand=" + longOperand +
                ", stringOperand='" + stringOperand + '\'' +
                '}';
    }
}
