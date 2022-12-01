import javax.rmi.ssl.SslRMIClientSocketFactory;

public class Longword_Test {

    public Longword_Test(){}
    public static void testParamSet(){
        //empty bit since the default constructor has a false state
        bit testBit = new bit();
        testBit.set(true);
        System.out.println(testBit.getValue() ? "Passed parameter Set() test." : "Failed parameter Set() test.");  
    }

    public static void testToggle(){
        bit testBit = new bit();
        //recording both boolean values between toggling to test
        boolean preToggleVal = testBit.getValue();
        testBit.toggle();
        boolean postToggleVal = testBit.getValue();
        System.out.println(preToggleVal != postToggleVal ? "Passed toggle() test." : "Failed toggle() test.");
    }

    public static void testSet(){
        bit testBit = new bit(); 
        System.out.println("Expected value of true");
        testBit.set();
        System.out.println(testBit.getValue() ? "Passed set() test." : "Failed set() test.");
    }

    public static void testClear(){
        bit testBit = new bit(true);
        System.out.println("Expected value of false after calling clear.");
        testBit.clear();
        System.out.println(!testBit.getValue() ? "Passed clear() test." : "Failed clear() test.");
    }

    public static void getValueTest(){
        bit tempBit = new bit(true);
        bit tempBit2 = new bit(false);
        System.out.println("False value from getValue(): " + tempBit.getValue());
        System.out.println("True value from getValue(): " + tempBit2.getValue());
    }

    /**
     * The following functions test and, or, and xor
     * They have parameters to be called with all boolean possibilities - [00 01 10 11]
     * The "should be" is meant to show the expected value, against getValue()'s return, the actual value
     */
    public static void andTest(boolean testStateOne, boolean testStateTwo){
        bit tempBit = new bit(testStateOne);
        bit tempBit2 = new bit(testStateTwo);
        bit tempBit3 = tempBit.and(tempBit2);
        System.out.println(testStateOne == false || testStateTwo == false ? "Value should be false: " + tempBit3.getValue() : "Value should be true: " + tempBit3.getValue());
    }
    
    public static void orTest(boolean testStateOne, boolean testStateTwo){
        bit tempBit = new bit(testStateOne);
        bit tempBit2 = new bit(testStateTwo);
        bit tempBit3 = new bit(tempBit.or(tempBit2).getValue());
        System.out.println(testStateOne == true || testStateTwo == true ? "Value should be true: " + tempBit3.getValue() : "Value should be false: " + tempBit3.getValue());
    }
    
    public static void xorTest(boolean testStateOne, boolean testStateTwo){
        bit tempBit = new bit(testStateOne);
        bit tempBit2 = new bit(testStateTwo);
        bit tempBit3 = tempBit.xor(tempBit2);
        System.out.println(testStateOne == testStateTwo ? "Value should be false: " + tempBit3.getValue() : "Value should be true: " + tempBit3.getValue());
    }
    
    
    public static void notTest(boolean testState){
        bit tempBit = new bit(testState);
        bit newBit = tempBit.not();
        System.out.println("Expected state should be opposite of tempBit's: " + tempBit.getValue());
        System.out.println(newBit.getValue() != tempBit.getValue() ? "Passed, successful NOT operation: " + newBit.getValue() : "Failed unsuccessful operation.: " + newBit.getValue());
    }

    public static void testToString(){
        bit testBit = new bit(true);
        bit testBit2 =  new bit(false);
        testBit.toString();
        testBit2.toString();
    }

    public static void runTests(){
        System.out.println("                                  ");
        System.out.println("Testing parameter Set function...");
        testParamSet();
        System.out.println("                                  ");
        System.out.println("                                  ");
        
        System.out.println("                                  ");
        System.out.println("Testing Toggle...");
        testToggle();
        System.out.println("                                  ");
        System.out.println("                                  ");
        
        System.out.println("Testing parameter-less set...");
        testSet();
        System.out.println("                                  ");
        System.out.println("                                  ");
        
        System.out.println("Testing clear function...");
        testClear();
        System.out.println("                                  ");
        System.out.println("                                  ");
        
        System.out.println("Testing getValue...");
        getValueTest();
        System.out.println("                                  ");
        System.out.println("                                  ");
        
        System.out.println("Testing all 4 cases of AND...");
        andTest(false,false);
        andTest(false,true);
        andTest(true,false);
        andTest(true,true);
        System.out.println("                                  ");
        System.out.println("                                  ");
        
        System.out.println("Testing all 4 cases of OR...");
        orTest(false,false);
        orTest(false,true);
        orTest(true,false);
        orTest(true,true);
        System.out.println("                                  ");
        System.out.println("                                  ");
        
        System.out.println("Testing all 4 cases of XOR...");
        xorTest(false,false);
        xorTest(false,true);
        xorTest(true,false);
        xorTest(true,true);
        System.out.println("                                  ");
        System.out.println("                                  ");
        
        System.out.println("Testing both cases of NOT...");
        notTest(true);
        notTest(false);
        System.out.println("                                  ");
        System.out.println("                                  ");
        
        System.out.println("                                  ");
        System.out.println(         " Testing toString "       );
        testToString();
        System.out.println("                                  ");
    }


    /**Longword Tests */

    public static void getBitTest(){
        Longword testLongword =  new Longword();
        System.out.println(!testLongword.getBit(0).getValue() ? "Successfully read testLongword's value. Passed test." : "Failed to read testLongword's value.");
    }
    
    public static void setBitTest(){
        Longword testLongword =  new Longword();
        System.out.println("Original longword value: " + testLongword.getBit(0).getValue());
        testLongword.setBit(31, new bit(true));
        System.out.println(testLongword.getBit(31).getValue() ? "Passed test, changed bit zero's value to true." : "Failed test.");
    }

    /**
     * For each logical AND, OR, XOR, NOT test
     * A long word's first value that is comprised of the function is compared to the bitwise value. They should be the same.
     */
    public static void andTest(){
        System.out.println("Longword AND test_________________________________________________________");
        Longword testLongword = new Longword();
        Longword secondTestLongword = testLongword.not();
        Longword andResultWord = testLongword.and(secondTestLongword);
        System.out.println((testLongword.getBit(0).and(secondTestLongword.getBit(0))).getValue() == andResultWord.getBit(0).getValue() ? "Passed andTest." : "Failed andTest");
        System.out.println("                                                                                                               ");
    }
    public static void orTest(){
        System.out.println("Longword OR test");
        Longword testLongword = new Longword();
        Longword secondTestLongword = testLongword.not();
        Longword orResultWord = testLongword.or(secondTestLongword);
        System.out.println((testLongword.getBit(0).or(secondTestLongword.getBit(0))).getValue() == orResultWord.getBit(0).getValue() ? "Passed orTest, two longwords have equal values." : "Failed orTest");
        System.out.println("                                                                                                                             ");
    }
    public static void xorTest(){
        System.out.println("Longword XOR test");
        Longword testLongword = new Longword();
        Longword secondTestLongword = testLongword.not();
        Longword xorResultWord = testLongword.xor(secondTestLongword);
        System.out.println((testLongword.getBit(0).xor(secondTestLongword.getBit(0))).getValue() == xorResultWord.getBit(0).getValue() ? "Passed xorTest." : "Failed xorTest");
        System.out.println("                                                                                                                             ");
    }
    public static void notTest(){
        System.out.println("Longword NOT test");
        Longword testLongword = new Longword();
        Longword secondTestLongword = testLongword.not();
        System.out.println(!testLongword.getBit(0).getValue() == secondTestLongword.getBit(0).getValue() ? "Passed not test." : "Failed not test.");      
        System.out.println("                                                                                                                             ");
    }

    public static void rightShiftTest(){
        System.out.println("Longword rightShift test");
        Longword testLongword = new Longword();
        /**Setting initial longword value to 10 */
        testLongword.setBit(21, new bit(true));
        System.out.println("Pre right shift value of 1024: " + testLongword.getSigned());
        Longword shiftedLongword = testLongword.rightShift(2);
        /**Getting the bits after shift + shift amount to show the change */
        System.out.println("Post right shift value should be 256: " + shiftedLongword.getSigned());
    }
    public static void leftShiftTest(){
        System.out.println("Longword leftShift test");
        Longword testLongword = new Longword();
        /**Setting initial longword value to 10 */
        testLongword.setBit(21, new bit(true));
        System.out.println("Pre left shift value of 1024: " + testLongword.getSigned());
        Longword shiftedLongword = testLongword.leftShift(3);
        /**Getting the bits after shift + shift amount to show the change */
        System.out.println("Post left shift value should be 8192: " + shiftedLongword.getSigned());
    }

  
    public static void longToStringTest(){
        System.out.println("Performing toString on a long word " + new Longword().toString());
    }

    public static void getUnsignedTest(){
        Longword testLongword =  new Longword();
        testLongword.setBit(23, new bit(true));
        System.out.println(testLongword.getUnsigned());
    }
    
    public static void getSignedTest(){
        Longword testLongword =  new Longword();
        Longword testLongwordTwo =  new Longword();
        testLongword.setBit(23, new bit(true));
        System.out.println("Value should be 256 " + testLongword.getSigned());
    }

    public static void copyTest(){
        Longword falseLongword =  new Longword();
        Longword trueLongword =  new Longword();
        for(int i = 31; i > 31; i--){
            trueLongword.setBit(i, new bit(true));
        }
        System.out.println("False longword pre copy: " + falseLongword.toString());
        falseLongword.copy(trueLongword);
        System.out.println("False longword post copy: " + falseLongword.toString());
        for(int i = 31; i > 0; i--){
            if(falseLongword.getBit(0).getValue() == trueLongword.getBit(0).getValue()){
                if(i == 0){
                    System.out.println("All values equal.");
                }
            }
            
        }
    }
    
    public static void longwordSetTest(){
        Longword setTestWord = new Longword();
        // System.out.println("Longword pre set. Represents 0:  " + setTestWord.toString(true));
        setTestWord.set(-16);
        System.out.println("Longword post set. Represents 1:  " + setTestWord.debugToString());
        System.out.println(setTestWord.getSigned());
    }

    public static void runLongwordTests(){
        System.out.println("                                                                                                                           ");
        getBitTest();      
        System.out.println("                                                                                                                           ");
        setBitTest();
        System.out.println("                                                                                                                           ");
        andTest();
        System.out.println("                                                                                                                           ");
        orTest();
        System.out.println("                                                                                                                           ");
        xorTest();
        System.out.println("                                                                                                                           ");
        getUnsignedTest();
        System.out.println("                                                                                                                           ");
        getSignedTest();
        System.out.println("                                                                                                                           ");
        notTest();
        System.out.println("                                                                                                                           ");
        rightShiftTest();
        System.out.println("                                                                                                                           ");
        leftShiftTest();
        System.out.println("                                                                                                                           ");
        longwordSetTest();
        System.out.println("                                                                                                                           ");
        copyTest();
        System.out.println("                                                                                                                           ");
    }
    public static void main(String[] args){
        // bit runTests
        //runTests();
        System.out.println("                                                                                                                           ");
        System.out.println("                                                                                                                           ");
        System.out.println("                                                                                                                           ");
        System.out.println("                                                                                                                           ");
        System.out.println("                                                                                                                           ");
        System.out.println("                                                                                                                           ");
        System.out.println("                                                                                                                           ");
        System.out.println("                                              Longword Tests Below                                                         ");
        //runLongwordTests();
        Longword testLongword = new Longword(255);
        System.out.println(testLongword.toString());
    }

}
