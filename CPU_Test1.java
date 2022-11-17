public class CPU_Test1 {
    public CPU_Test1(){}
    public static void main(String[] args){
        /**Original Tests */
        // bit_test.runTests();
        // Longword_Test.runTests();
        // rippleAdder_test.rippleAdderTests();
        // Multiplier_test.run_tests();
        // ALU_test.runTests();
        // Memory_test.runTests();
        // 1000 - and
        // 1001 - or
        // 1010 - xor
        //1011 - not
        //1100 - left shift
        //1101 - right shift
        //1110 - add
        //1111 - subtract   

        Computer testComputerA = new Computer();
        String [] testA = {
            "0001 0011 0000 1010", //Move r3 10
            "0001 0111 0000 1111", //Move r7 10
            "0010 0000 0000 0000", //Memory printing
        };
        testComputerA.preLoad(testA);
        testComputerA.run();
    }
}

