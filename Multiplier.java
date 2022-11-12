public class Multiplier {

    public static Longword multiply(Longword multiplicand, Longword multiplier){
        Longword resultingWord = new Longword();
        for(int i = multiplier.getLength() - 1; i >= 0; i--){
            if(multiplier.getBit(i).getValue()){
                resultingWord = rippleAdder.add(resultingWord, new Longword(multiplicand.leftShift(31 - i).getSigned()));
            }
        }
        return resultingWord;
    }
}
