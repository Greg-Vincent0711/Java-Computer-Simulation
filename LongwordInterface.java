public interface LongwordInterface {
    bit getBit(int i); // Get bit i
    void setBit(int i, bit value); // set bit i's value
    Longword and(Longword other); // and two Longwords, returning a third
    Longword or(Longword other); // or two Longwords, returning a third
    Longword xor(Longword other);// xor two Longwords, returning a third
    Longword not(); // negate this Longword, creating another
    Longword rightShift(int amount); // rightshift this Longword by amount bits, creating a new Longword
    Longword leftShift(int amount);// leftshift this Longword by amount bits, creating a new longword
    @Override
    String toString(); // returns a comma separated string of 0's and 1's: "0,0,0,0,0 (etcetera)" for example
    long getUnsigned(); // returns the value of this longword as a long
    int getSigned(); // returns the value of this longword as an int
    void copy(Longword other); // copies the values of the bits from another longword into this one
    void set(int value); // set the value of the bits of this longword (used for tests)
}
