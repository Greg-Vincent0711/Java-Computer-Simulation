import java.util.HashMap;
import java.util.ArrayList;
public class Assembler{ 
    private static String indexStr = "", assemblyStr = "";
    //this boolean is flagged based on the validity of the inputted string
    private static boolean validStatement;
    private static HashMap<String,String> registerNumbers = new HashMap<String,String>();
    private static HashMap<String,String> commands = new HashMap<String,String>();
    public static String[] assemble(String [] readableArr){
        ArrayList<String> tempList = new ArrayList<String>();
        for(int i = 0; i < readableArr.length; i++){
            for(int j = 0; j < readableArr[i].length(); j++){
                if(Character.isWhitespace(readableArr[i].charAt(j)) == false){
                    indexStr += readableArr[i].charAt(j);
                }
                //if this is the case, we're in a space between statements 
                else if(Character.isWhitespace(readableArr[i].charAt(j)) == true && indexStr.length() > 0){ 
                    if(indexStr.charAt(0) == 'R' && indexStr.length() < 4){
                        isValidRegister(indexStr);
                        if(validStatement){
                            assemblyStr += " " + indexStr;
                            //after each iteration, we have to reset our string reference
                            indexStr = "";
                        }
                    }
                    //positve number checking
                    else if(Character.isDigit(indexStr.charAt(0))){
                        String tempBitStr = Integer.toBinaryString(Integer.parseInt(indexStr));
                        assemblyStr += " " + tempBitStr;
                        indexStr = "";
                    }
                    //negative number checking
                    else if(indexStr.charAt(0) == '-'){
                        assemblyStr += " " + binaryRepMaker(indexStr);
                        indexStr = "";

                    }
                    //must be a word otherwise 
                    else{
                        isValidWord(indexStr);
                        if(validStatement){
                            assemblyStr += " " + indexStr; 
                            indexStr = "";
                        }
                    }
                }
            }
            tempList.add(assemblyStr); 
            assemblyStr = "";
        }
        String [] assemblyArr = new String[tempList.size()];
        for(int i = 0; i < tempList.size(); i++){
            assemblyArr[i] = tempList.get(i);
        }
        return assemblyArr;
    }
    
    
    //helper function for negative numbers, since toBinaryString won't handle them. Similar logic to getSigned
    private static String binaryRepMaker(String test){
        //get the integer representation of the string
        int intRep = Integer.parseInt(test);
        Longword tempWord = new Longword(intRep);
        String binaryRep = "";
        if(intRep < 16){
            //a number 15 or less can be represented with 4 bits
            for(int i = tempWord.getLength() - 1; i > tempWord.getLength() - 8; i--){
                if(tempWord.getBit(i).getValue()){
                    binaryRep += "1";
                } else{
                    binaryRep += "0";
                }
            }
        } 
        else{
            for(int i = tempWord.getLength() - 1; i > tempWord.getLength() - 16; i--){
                if(tempWord.getBit(i).getValue()){
                    binaryRep += "1";
                } else{
                    binaryRep += "0";
                }
            }
        }
    return binaryRep;
}

    //Both of these hash maps hold allowed values and register numbers    
    private static void isValidWord(String word) throws IllegalArgumentException{
        commands.put("AND","1000");
        commands.put("OR","1001");
        commands.put("XOR","1010");
        commands.put("NOT","1011");
        commands.put("LEFTSHIFT","1100");
        commands.put("RIGHTSHIFT","1101");
        commands.put("ADD", "1110");
        commands.put("SUBTRACT","1111");
        commands.put("MULTIPLY","0111");
        commands.put("INTERRUPT0","0010000000000000");
        commands.put("INTERRUPT1","0010000000000001");
        commands.put("HALT","0000000000000000");
        commands.put("MOVE","0001");
        if(commands.containsKey(word)){
            indexStr = commands.get(word);
            validStatement = true;
        } else{
            throw new IllegalArgumentException("Invalid command.");
        }
    }
    //check if a register is validStatement
    private static void isValidRegister(String registerNum){
        registerNumbers.put("R0","0000");
        registerNumbers.put("R1","0001");
        registerNumbers.put("R2","0010");
        registerNumbers.put("R3","0011");
        registerNumbers.put("R4","0100");
        registerNumbers.put("R5","0101");
        registerNumbers.put("R6","0110");
        registerNumbers.put("R7","0111");
        registerNumbers.put("R8","1000");
        registerNumbers.put("R9","1001");
        registerNumbers.put("R10","1010");
        registerNumbers.put("R11","1011");
        registerNumbers.put("R12","1100");
        registerNumbers.put("R13","1101");
        registerNumbers.put("R14","1110");
        registerNumbers.put("R15","1111");
        if(registerNumbers.containsKey(registerNum)){
            indexStr = registerNumbers.get(registerNum);
            validStatement = true;
        } else{
            throw new IllegalArgumentException("Invalid register.");
        }
    }
}
