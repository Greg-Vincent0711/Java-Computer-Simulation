public class ALU {
    
    //this function returns the signed amount of the lowest five bits of a longword.
    private static int getSmallUnSigned(Longword traversalWord){
        int result = 0, count = 0;
        for(int i = traversalWord.getLength() - 1; i > 25; i--){
            if(traversalWord.getBit(i).getValue()){
                //essentially, remake the logic for getUnsigned for 5 positions of the array
                count++;
                result += 2^count;
            }
        }
        return result;
    }
    public static Longword doOp(bit [] operation, Longword a, Longword b) throws IllegalArgumentException{
        if(operation.length > 4){
            throw new IllegalArgumentException("Operation array is too long");
        } else{
            //First Path 1xxx
           if(operation[0].getValue()){
            //true, false. Second Path 10xx
                if(operation[1].getValue() == false){
                    //true, false, false. Third Path 100x
                    if(operation[2].getValue() == false){
                        //true, false, false, false - and 1000
                        if(operation[3].getValue() == false){
                            return a.and(b);
                        }
                        //true, false, false, true - or 1001
                        else{
                            return a.or(b);
                        }
                    } 
                    else{
                        //if operation[2].getValue() == true Fourth path 101x
                        //true, false, true, false - xor 1010
                        if(operation[3].getValue() == false){
                            return a.xor(b);
                        }
                        //true, false, true, true - not  1011
                        else{
                            return a.not();
                        }
                    }
                }
                //True, True. Third path 11xx. 
                 else{
                    //True, True, False. Fourth path 110x
                    if(operation[2].getValue() == false){
                        //true,true,false,false - left shift 1100
                        if(operation[3].getValue() == false){
                            return a.leftShift(getSmallUnSigned(b));
                        } else{
                            //true, true, false, true - right shift 1101
                            return a.rightShift(getSmallUnSigned(b));
                        }
                    } 
                    //True, True, True. Fifth path. 111x.
                    else{
                        //True, True, True, False - add 1110
                        if(operation[3].getValue() == false){
                            return rippleAdder.add(a,b);
                        } 
                        //True, True, True, True - subtract 1111
                        else{
                            return rippleAdder.subtract(a,b);
                        }
                    }
                }
           } 
           //operation[0].getValue() == false. Multiply path 
           else{
            if(operation[1].getValue()){
                if(operation[2].getValue()){
                    if(operation[3].getValue()){
                        return Multiplier.multiply(a,b);
                    }
                }
              }
           }
        }
        return null;
    }    
}    

// 1000 - and
// 1001 - or
// 1010 - xor
//1011 - not
//1100 - left shift
//1101 - right shift
//1110 - add
//1111 - subtract          