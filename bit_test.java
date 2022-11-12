public class bit_test {
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
    
    public static void main(String[] args){
       runTests();
    }
}
