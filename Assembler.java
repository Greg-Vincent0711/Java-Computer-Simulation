import java.util.HashMap;
import java.util.ArrayList;
public class Assembler{ 
    private static String indexStr = "", assemblyStr = "", currentCommand = "";
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
                        assemblyStr += " " + getNum(indexStr);
                        indexStr = "";
                    }
                    //negative number checking
                    else if(indexStr.charAt(0) == '-'){
                        assemblyStr += " " + getNum(indexStr);
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

    private static String getNum(String strNum){
        int num = Integer.parseInt(strNum);
        String strBin = Integer.toBinaryString(num);
        String signExtend;
        num < 0 ? signExtend = "1" : signExtend = "0";
        switch(currentCommand){
            case "MOVE": 
            case "R0":
            case "R1":
            case "R2":
            case "R3":
            case "R4":
            case "R5":
            case "R6":
            case "R7":
            case "R8":
            case "R9":
            case "R10":
            case "R11":
            case "R12":
            case "R13":
            case "R14":
            case "R15":
                for(int i = strBin.length(); i < 8; i++){
                    strBin = signExtend + strBin;
                }
                break;
            case "JUMP":
                for(int i = strBin.length(); i < 12; i++){
                    strBin = signExtend + strBin;
                }
                break;
            case "BRANCHIFEQUAL":
            case "BRANCHIFNOTEQUAL":
            case "BRANCHIFGREATERTHAN":
            case "BRANCHIFGREATERTHANOREQUAL":
            case "CALL":
                for(int i = strBin.length(); i < 10; i++){
                    strBin = signExtend + strBin;
                }
                break;
            default:
            break;
        }
        return strBin;
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
        commands.put("JUMP","0011");
        commands.put("COMPARE", "0100 0000");
        commands.put("BRANCHIFEQUAL", "010101");
        commands.put("BRANCHIFNOTEQUAL", "010100");
        commands.put("BRANCHIFGREATERTHAN", "010110");
        commands.put("BRANCHIFGREATERTHANOREQUAL", "010111");
        commands.put("PUSH", "0110 0000 0000");
        commands.put("POP", "0110 0100 0000");
        commands.put("RETURN", "0110 1100 0000 0000");
        commands.put("CALL", "011010");
        

        if(commands.containsKey(word)){
            indexStr = commands.get(word);
            validStatement = true;
            currentCommand = word;
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
            currentCommand = registerNum;
        } else{
            throw new IllegalArgumentException("Invalid register.");
        }
    }
}

