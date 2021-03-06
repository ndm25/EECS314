package mips.sim.instructions;

import mips.sim.Instruction;
import mips.sim.Memory;
import mips.sim.RegisterFile;
import mips.sim.Word;

public class SubtractUnsignedInstruction extends RTypeInstruction {

	public SubtractUnsignedInstruction(Memory memory, RegisterFile regFile,
			Word instruction) {
		super(memory, regFile, instruction);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Word getResult() {
		long one = Instruction.unsignedLongFromInt(this.inputRegisters.get(0).getWord().asInt());
		long two = Instruction.unsignedLongFromInt(this.inputRegisters.get(1).getWord().asInt());
		return new Word((int)(two - one));
	}

	@Override
	public String getInstructionName() {
		return "SUBU";
	}

}
