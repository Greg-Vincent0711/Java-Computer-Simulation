import java.lang.Math;
public class Longword implements LongwordInterface {
    private bit [] internalArray = new bit[32];
    //default constructor will create a longword with a value of 0 
    public Longword(){
        for(int i = 0; i < internalArray.length; i++){
            internalArray[i] = new bit();
        }
    }
    

    public bit getBit(int i){
        return internalArray[i]; 
    }

    
    public void setBit(int i, bit value) throws IllegalArgumentException{
        if(i > internalArray.length - 1 || i < 0){
            throw new IllegalArgumentException("Parameter ' i ' is is an invalid length.");
        }
        internalArray[i] = value;
    }
    
    /**
     * Each of these logic gate functions follow the logic for their bitwise counterparts, just with
     * some array operations added in
     */
    public Longword and(Longword other){
        Longword andResult = new Longword();
        for(int i = 0; i < internalArray.length; i++){
            andResult.setBit(i, this.getBit(i).and(other.getBit(i))); 
        }
        return andResult;
    }
    
    public Longword or(Longword other){
        Longword orResult = new Longword();
        for(int i = 0; i < internalArray.length; i++){
            orResult.setBit(i, this.getBit(i).or(other.getBit(i)));
        }
        return orResult;
    }
    
    public Longword xor(Longword other){
        Longword xorResult = new Longword();
        for(int i = 0; i < internalArray.length; i++){
            xorResult.setBit(i, this.getBit(i).xor(other.getBit(i)));
        }
        return xorResult;   
    }
    
    public Longword not(){
        Longword notResult = new Longword();
        for(int i = 0; i < internalArray.length; i++){
            notResult.setBit(i, this.getBit(i).not());
        }
        return notResult;
    }

    public Longword rightShift(int shiftAmount) throws IllegalArgumentException{
        Longword rightShiftResult = new Longword();
        if(shiftAmount < 0 || shiftAmount > 31){
            throw new IllegalArgumentException("Invalid shiftAmount.");
        }
        int shiftDifference = internalArray.length - shiftAmount;
        int i;
        for(i = 0; i < shiftAmount; i++ ){
            //the shifted amount is equal to the amount of zeroes needed
            rightShiftResult.setBit(i, new bit());
        }
        //shiftDifference keeps track of how many original bits are kept
        for(int j = 0; j < shiftDifference; j++){
            rightShiftResult.setBit(i, this.getBit(j));
            i++;
        }    
        return rightShiftResult;
    }

    public Longword leftShift(int shiftAmount) throws IllegalArgumentException{
        Longword leftShiftResult = new Longword();
        if(shiftAmount < 0 || shiftAmount > 31){
            throw new IllegalArgumentException("Invalid shiftAmount.");
        }
        int shiftDifference = (internalArray.length - 1) - shiftAmount;
        //shiftAmount keeps track of how many positions must be set to zero
        for(int i = internalArray.length - 1; i > shiftDifference; i--){
            //array must be filled with zero in spaces post shift
            leftShiftResult.setBit(i, new bit());
        }
        int j = internalArray.length - 1;
        //keep the original amount of bits, set them in the new longword
        for(int i2 = shiftDifference; i2 > -1; i2--){
            leftShiftResult.setBit(i2, this.getBit(j));
            j--;
        }   
        return leftShiftResult;
    }

    public void copy(Longword copyFrom){
        for(int i = 0; i < this.internalArray.length - 1; i++){
            this.setBit(i, copyFrom.getBit(i));
        }
    }

    public long getUnsigned(){
        long unsignedValue = 0;
        int j = 0;
        for(int i = internalArray.length - 1; i >= 0; i--){
            if(internalArray[i].getValue()){
                //we count the true values as a one, so add to the result for each true
                unsignedValue += Math.pow(2,j);
            }
            j++;
        }
        return unsignedValue;
    }
    

    public int getSigned(){
        int signedValue = 0;
        if(internalArray[0].getValue() == false){
            //logic for a positive value is the same as getUnsigned, so we can caste the result
            return signedValue = (int)getUnsigned();
        } else{ 
            Longword signedLongword = this.not();
            /*
            int j = 0;
            for(int i = internalArray.length - 1; i > 1; i--){
                if(!signedLongword.getBit(i).getValue()){
                    //j value is so that the Math.pow uses the correct exponent. Otherwise, [31] would return 2^31, and not 2^0
                    signedValue += Math.pow(2,j);
                }
                j++;
            }
            */
            signedValue = (int)signedLongword.getUnsigned()+1;
            return (-signedValue);
        }
    }


    public void set(int longwordValue) throws IllegalArgumentException{
        if(longwordValue > Math.pow(2,31)){
            throw new IllegalArgumentException("Parameter is too large.");
        }
        if(longwordValue < 0){
            longwordValue = -longwordValue;
            longwordValue--;

            for(int i = internalArray.length - 1; i >= 0; i--){
                //if a remainder is left after the operation, we know that bit's value is used, so set it to true
                if(longwordValue % 2 == 1){
                    this.setBit(i, new bit(false));
                } else{
                    this.setBit(i, new bit(true));
                }
                //we divide by two continuously here to use the remainder in the next loop iteration.
                longwordValue /=2;
            }
        } else{
            //logic for positive numbers
                this.setBit(0, new bit(false));
                for(int i = internalArray.length - 1; i > 0; i--){
                    //if a remainder is left after the operation, we know that bit's value is used, so set it to true
                    if(longwordValue % 2 == 1){
                        this.setBit(i, new bit(true));
                    } else{
                        this.setBit(i, new bit(false));
                    }
                    //we divide by two continuously here to use the remainder in the next loop iteration.
                    longwordValue /=2;
                }
        }
    }

    @Override
    public String toString(){
        String resultantString = "";
        for(int i = 0; i < internalArray.length; i++){
            resultantString += internalArray[i].getValue() ? " 1, " : " 0, ";
        }
        return resultantString;
    }
}
