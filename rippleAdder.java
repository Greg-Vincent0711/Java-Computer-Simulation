public class rippleAdder {
    public rippleAdder(){}
    public static Longword add(Longword a, Longword b){
        bit carryBit = new bit(false);
        Longword addResult = new Longword();
        for(int i = a.getLength() - 1; i >= 0; i--){
            //s = a XOR b XOR cin
            addResult.setBit(i, a.getBit(i).xor(b.getBit(i)).xor(carryBit));
            //carryBit = a AND b OR ((a XOR b) AND carryBit)
            carryBit.set((a.getBit(i).and(b.getBit(i)).or((a.getBit(i).xor(b.getBit(i)).and(carryBit)))).getValue());
        }
        return addResult;
    }
    public static Longword subtract(Longword a, Longword b){
        //perform twos complement in order to subtract
        return add(a, add(b.not(), new Longword(1)));
    }
}
