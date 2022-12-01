public class CPU_Test3 {
    
    
    public static void main(String[] args){
        // Computer MicrosoftWindows = new Computer();
        //INTERRUPT0 - print Registers
        //INTERRUPT1 - print Mem,
        Computer Unix  = new Computer();
        Computer Windows = new Computer();
        String [] PushPopTest = {
            "MOVE R2 11 ",
            "PUSH R2 ",
            "INTERRUPT1 ",
            "POP R6 ",
            "INTERRUPT1 ",
            "INTERRUPT0 "
        };
        Unix.preLoad(Assembler.assemble(PushPopTest));
        Unix.run();
        String [] CallReturnTest = {
            "CALL 4  ",
            "HALT ",
            "MOVE R1 10 ",
            "MOVE R5 15 ",
            "INTERRUPT0 ",
            "RETURN ",
            "INTERRUPT1 ", //This command won't be fired
        };
        Windows.preLoad(Assembler.assemble(CallReturnTest));
        Windows.run();
    }

}

//Push Works
