public class Assembler_test {
    static String [] testArray ={ 
        "ADD R9 R2 R3 ", //1110 0001 0010 0011
        "SUBTRACT R4 R3 R5 ", //1111 0100 0011 0101
        "RIGHTSHIFT R4 R3 ", //1101 0100 0011
        "MULTIPLY R1 R9 R7 ", //0111 0001 1001 0111
        "XOR R4 10 ", //1010 0010 1010
        "MOVE R1 -1 ", //0001 0001 11111111
        "HALT ", //0000 0000 0000 0000 -  last 3 won't have spaces in the output
        "INTERRUPT0 ", //0010 0000 0000 0000 
        "INTERRUPT1 ", //0010 0000 0000 0001
    };
    public static void main(String[] args){
        String [] res = Assembler.assemble(testArray);
        for(int i = 0; i < testArray.length; i++){
            System.out.println(res[i]);
        } 

    }   
}
