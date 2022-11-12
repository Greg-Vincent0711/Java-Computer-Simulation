public class Memory {

    private bit[] workingMemory = new bit[8192];

    public Memory(){
        for(int i = 0; i < 8191; i++){
            workingMemory[i] = new bit();
        }
    } 

    public Longword read(Longword address)throws IllegalArgumentException{
        if(address.getUnsigned() > 1020){
            throw new IllegalArgumentException("Address is outside excepted range.");
        }
        if(address.getUnsigned() < 0){
            throw new IllegalArgumentException("Address is outside excepted range.");
        }
        Longword readWord  = new Longword();
        int readCount = 0;
        //multiply by 8 because we want the memory that maps to the address in bit form(since we assume it's value is in byte form)
        for(int i = (int)address.getUnsigned() * 8; i < (int)address.getUnsigned() * 8 + 32; i++){
            readWord.setBit(readCount, new bit(workingMemory[i].getValue()));
            //Longwords don't have indexes that high, so count keeps us within the bounds of the memory
            readCount++;
        }
        //return that read data as a longword
        return readWord;
    }

    public void write(Longword address, Longword value){
        if(address.getUnsigned() > 1020){
            throw new IllegalArgumentException("Address is outside excepted range.");
        }
        if(address.getUnsigned() < 0){
            throw new IllegalArgumentException("Address is outside excepted range.");
        }
        int writeCount = 0;
        for(int i = (int)address.getUnsigned() * 8; i < (int)address.getUnsigned() * 8 + 32; i++){
            workingMemory[i] = value.getBit(writeCount);
            writeCount++;
        }
    }
}
