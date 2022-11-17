public class Computer {
    private bit runningState;     
    private Memory internalMemory;
    private Longword programCounter;
    private Longword[] computerRegisters;
    private Longword currentInstruction; 
    private Longword op1; 
    private Longword op2; 
    private Longword resultant; 
    private bit [] indicatorBits;

    public Computer(){
        op1 = new Longword();
        op2 = new Longword();
        resultant = new Longword();
        runningState = new bit(true);
        internalMemory = new Memory();
        programCounter = new Longword();
        currentInstruction = new Longword();
        computerRegisters = new Longword[16];
        indicatorBits = new bit[2];
        indicatorBits[0] = new bit(false);
        indicatorBits[1] = new bit(false);
        for(int i = 0; i < 16; i++){
            computerRegisters[i] = new Longword();
        }
    }

    private void fetch(){
        currentInstruction = internalMemory.read(programCounter);
        programCounter = rippleAdder.add(programCounter, new Longword(2)); 
    }

    private void decode(){
        //Regular Instructions - 1xxx
        if(currentInstruction.getBit(0).getValue()){
            op1.copy(computerRegisters[currentInstruction.rightShift(24).and(new Longword(15)).getSigned()]);
            op2.copy(computerRegisters[currentInstruction.rightShift(20).and(new Longword(15)).getSigned()]);
        }
        //Special Instructions - 0xxx from here down
        else{
            //00xx
            if(currentInstruction.getBit(1).getValue() == false){
                //000x from here down
                if(currentInstruction.getBit(2).getValue() == false){
                    //Move Instruction - 0001 from here down 
                    if(currentInstruction.getBit(3).getValue()){
                        //original right shfit amount was 24
                        op1.copy(computerRegisters[currentInstruction.rightShift(8).and(new Longword(15)).getSigned()]);
                    }
                }        
            }
            //01xx 
            else{
                if(currentInstruction.getBit(2).getValue() == false){
                    //0100 - Compare
                    if(currentInstruction.getBit(3).getValue() == false){
                        //get the right registers for op1 and op2
                        op1.copy(computerRegisters[currentInstruction.rightShift(20).and(new Longword(15)).getSigned()]);
                        op2.copy(computerRegisters[currentInstruction.rightShift(16).and(new Longword(15)).getSigned()]);
                    }
                    //011x
                    else{

                    }
                }
                //010x 
                else{

                }
            }
        }
    }
    

    private void execute(){
        //Regular Instructions
        bit [] opCodeArr = new bit[4];
        //1xxx
        if(currentInstruction.getBit(0).getValue()){
            for(int i = 0; i < 4; i++){
                opCodeArr[i] = currentInstruction.getBit(i);
            }
            resultant = ALU.doOp(opCodeArr, op1, op2);  
        }
        //0xxx 
        else{
            //00xx
            if(currentInstruction.getBit(1).getValue() == false){
                if(currentInstruction.getBit(2).getValue() == false){
                    if(currentInstruction.getBit(3).getValue() == false){
                    //0000, execute the halt instruction
                    runningState.clear();
                    } else{
                    //0001, execute the move instruction, check for negatives
                        if(currentInstruction.getBit(8).getValue()){
                            resultant.copy(currentInstruction.rightShift(16).or((new Longword(255).not())));
                        }
                        else{
                            resultant.copy(currentInstruction.rightShift(16).and(new Longword(255)));    
                        }
                    }
                } else{
                    if(currentInstruction.getBit(3).getValue() == false){
                    //interrupt instruction
                        if(currentInstruction.getBit(12).getValue() == false){
                            if(currentInstruction.getBit(13).getValue() == false){
                                if(currentInstruction.getBit(14).getValue() == false){
                                    if(currentInstruction.getBit(15).getValue() == false){
                                    //0000 path of interrupt, print all registers
                                        for(int i = 0; i < 16; i++){
                                            System.out.println(i + ". " + computerRegisters[i]);
                                        }
                                    } 
                                    //0001 path of interrupt, print memory
                                    else{
                                        internalMemory.toString();
                                        }
                                }
                            }
                        }
                    }
                }
            }
            //01xx 
            else{
                if(currentInstruction.getBit(2).getValue()){
                    if(currentInstruction.getBit(3).getValue()){  
                        //0111 - Multiply              
                        for(int i = 0; i < 4; i++){
                            opCodeArr[i] = currentInstruction.getBit(i);
                        }
                        resultant = ALU.doOp(opCodeArr, op1, op2);
                    }
                }
                //010x
                else{
                    //0100 - Compare
                    if(currentInstruction.getBit(3).getValue() == false){
                        compare(op1,op2);
                    }
                    //0101 - Branch commands 
                    else{
                        
                    }
                }
            }
        }            
    }

    private void store(){
        //Both move operations, store, interrupts
        //Move Logic for store
        //ALU Storing
        if(currentInstruction.getBit(0).getValue() == true){
            computerRegisters[(int) currentInstruction.rightShift(12).and(new Longword(15)).getUnsigned()].copy(resultant);
        }
        //0xxx
        else{
            if(currentInstruction.getBit(1).getValue() == true){//01xx
                if(currentInstruction.getBit(2).getValue() == true){//011x
                    if(currentInstruction.getBit(3).getValue() == true){//0111
                        computerRegisters[(int) currentInstruction.rightShift(12).and(new Longword(15)).getUnsigned()].copy(resultant);
                    }
                    else{//0110
                        
                    }
                }
                else{//010x
                    if(currentInstruction.getBit(3).getValue() == true)
                    {//0101
                        Longword mask = new Longword(1023);
                        Longword tempNum = new Longword();
                        if(currentInstruction.getBit(6).getValue()){
                            //Negative numbers
                            tempNum = currentInstruction.rightShift(16).or((mask.not()));
                        } 
                        else{
                            tempNum = currentInstruction.rightShift(16).and(mask);
                        }

                        if(currentInstruction.getBit(4).getValue()){//1x
                            if(currentInstruction.getBit(5).getValue()){//11
                                if(indicatorBits[0].getValue() || indicatorBits[1].getValue()){
                                    programCounter = rippleAdder.add(programCounter, tempNum);
                                }
                            }
                            else{//10
                                if(indicatorBits[0].getValue()){
                                    programCounter = rippleAdder.add(programCounter, tempNum);
                                }
                            }
                        }
                        else{//0x
                            if(currentInstruction.getBit(5).getValue()){//01
                                if(indicatorBits[1].getValue()){
                                    programCounter = rippleAdder.add(programCounter, tempNum);
                                }
                            }
                            else{//00
                                if(indicatorBits[1].getValue() == false){
                                    programCounter = rippleAdder.add(programCounter, tempNum);
                                }
                            }
                        }
                    }
                }

            }
            else{//00xx
                if(currentInstruction.getBit(2).getValue() == true){//001x
                    if(currentInstruction.getBit(3).getValue() == true){//0011 - Jump
                        programCounter.copy(currentInstruction.rightShift(16).and(new Longword(1023)));
                    }
                    else{//0010 - Move 
                        computerRegisters[(int) currentInstruction.rightShift(8).and(new Longword(255)).getUnsigned()].copy(resultant);
                    }
                
                }
                else{//000x
                    if(currentInstruction.getBit(3).getValue() == true){//0001
                        computerRegisters[(int) currentInstruction.rightShift(24).and(new Longword(15)).getUnsigned()].copy(resultant);
                    }
                    else{//0000
                    }
                }
            }

        }
    }


    private void compare(Longword first, Longword second){
        indicatorBits[0].clear();
        indicatorBits[1].clear();

        Longword difference = rippleAdder.subtract(first, second);
        if(difference.getSigned() == 0){
            //Equal to case
            indicatorBits[1].set();
        } else if(difference.getBit(0).getValue() == false){
            indicatorBits[0].set();
        }
    }

    public void preLoad(String [] newInstruction) {
        Longword preLoadAddr =  new Longword();
        for(int i = 0; i < newInstruction.length; i++){
            Longword tempWord = new Longword();
            for(int j = 0, k = 0; j < newInstruction[i].length(); j++){
                if(newInstruction[i].charAt(j) == '1'){
                    tempWord.setBit(k, new bit(true));
                    k++;
                }
                else if(newInstruction[i].charAt(j) == '0'){
                    tempWord.setBit(k, new bit(false));
                    k++;
                }
                //skip spaces 
                else{
                    continue;
                }
            }
            internalMemory.write(preLoadAddr, tempWord);
            preLoadAddr = rippleAdder.add(preLoadAddr, new Longword(2));
        }
    }  
   
    public void run(){
        while(runningState.getValue()){
            fetch();
            decode();
            execute();
            store();
        }
    }
}
