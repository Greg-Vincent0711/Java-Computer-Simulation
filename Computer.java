public class Computer {
    private bit runningState;     
    private Memory internalMemory;
    private Longword programCounter;
    private Longword[] computerRegisters;
    private Longword currentInstruction; 
    private Longword op1; 
    private Longword op2; 
    private Longword resultant; 

    public Computer(){
        op1 = new Longword();
        op2 = new Longword();
        resultant = new Longword();
        runningState = new bit(true);
        internalMemory = new Memory();
        programCounter = new Longword();
        currentInstruction = new Longword();
        computerRegisters = new Longword[16];
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
            //00xx from here down
            if(currentInstruction.getBit(1).getValue() == false){
                //000x from here down
                if(currentInstruction.getBit(2).getValue() == false){
                    //Move Instruction - 0001 from here down 
                    if(currentInstruction.getBit(3).getValue()){
                        op1.copy(computerRegisters[currentInstruction.rightShift(24).and(new Longword(15)).getSigned()]);
                    }
                }    
            }
        }
    }
    

    private void execute(){
        //Regular Instructions
        bit [] opCodeArr = new bit[4];
        if(currentInstruction.getBit(0).getValue()){
            for(int i = 0; i < 4; i++){
                opCodeArr[i] = currentInstruction.getBit(i);
            }
            resultant = ALU.doOp(opCodeArr, op1, op2);  
        } 
        else{
            //0xxx
            if(currentInstruction.getBit(1).getValue() == false){
                if(currentInstruction.getBit(2).getValue() == false){
                    if(currentInstruction.getBit(3).getValue() == false){
                    //0000, execute the halt instruction
                    runningState.clear();
                    } else{
                    //0001, execute the move instruction, check for negatives
                        if(currentInstruction.getBit(8).getValue())
                        {
                            /**
                             * Longword signedLongword = this.not();
                                signedValue = (int)signedLongword.getUnsigned()+1;
                                return (-signedValue);
                             */
                            Longword temp = new Longword().not();
                            //get the 
                            for(int i = 8; i < 17; i++){
                                temp.setBit(i, op1.getBit(i));
                            }
                            resultant.copy(temp);
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
                                            System.out.println(i + ". " + computerRegisters[i].toString());
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
            } else{
                if(currentInstruction.getBit(1).getValue()){
                    if(currentInstruction.getBit(2).getValue()){
                        if(currentInstruction.getBit(3).getValue()){                
                            for(int i = 0; i < 4; i++){
                                opCodeArr[i] = currentInstruction.getBit(i);
                            }
                            resultant = ALU.doOp(opCodeArr, op1, op2);
                        }
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
                    if(currentInstruction.getBit(3).getValue() == true){//0101

                
                    }
                    else{//0100
        
                    }
                }

            }
            else{//00xx
                if(currentInstruction.getBit(2).getValue() == true){//001x
                    if(currentInstruction.getBit(3).getValue() == true){//0011

                
                    }
                    else{//0010 - Move 
                        computerRegisters[(int) currentInstruction.rightShift(8).and(new Longword(15)).getUnsigned()].copy(resultant);
                    }
                
                }
                else{//000x
                    if(currentInstruction.getBit(3).getValue() == true){//0001
                        computerRegisters[(int) currentInstruction.rightShift(24).and(new Longword(15)).getUnsigned()].copy(resultant);
                    }
                    else{//0000
                        //Do nothing branch for interrupts
                    }
                }
            }

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

