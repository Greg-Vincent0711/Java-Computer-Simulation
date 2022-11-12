public class Computer {
    private bit notHalted;
    private Memory internalMemory;
    private Longword programCounter;
    private Longword[] computerRegisters;
    private Longword currentInstruction;
    private Longword op1;
    private Longword op2;
    private Longword resultant;

    public Computer(){
        notHalted = new bit();
        internalMemory = new Memory();
        programCounter = new Longword();
        currentInstruction = new Longword();
        computerRegisters = new Longword[16];
        for(int i = 0; i < 16; i++){
            computerRegisters[i] = new Longword();
        }
    }

    //reading and storing instructions from memory, incrementing the PC
    private void fetch(){
        currentInstruction = internalMemory.read(programCounter);
        programCounter = rippleAdder.add(programCounter, new Longword(2)); 
    }

    private void decode(){
        //Registers are from 0 - 15, so shift to get least significant bits. then mask by ANDing with 15
        op1.copy(computerRegisters[currentInstruction.rightShift(24).and(new Longword(15))]);

        op2.copy(computerRegisters[currentInstruction.rightShift(20).and(new Longword(15))]);
    }

    private void execute(){
        //collecting the opCode from it's array location. No masking needed since we're at the end of the longword
        Longword opCodeWord = currentInstruction.rightShift(28);
        bit [] opCodeArr = new Bit[4];
        for(int i = 3; i > -1; i--){
            opCodeArr[i] = opCodeWord.getBit(i);
        }
        resultant = ALU.doOp(opCodeArr, op1, op2);
    }

    private void store(){
        //Copying into the register R3. Whatever address is stored in the position of rightshift 16.
        computerRegisters[currentInstruction.rightShift(16).and(new Longword(15))].copy(resultant);
    }

    //run cycle
    public void run(){
        while(notHalted.getValue()){
            fetch();
            decode();
            execute();
            store();
        }
    }
}
