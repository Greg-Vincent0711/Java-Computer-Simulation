public class Memory_test {
    public static void MemoryTest() {
        //Reading and Writing 32 at address 0
        Longword writeAddress = new Longword(0);
        Longword writeValue = new Longword(32);
        Memory memoryInstance = new Memory();
        memoryInstance.write(writeAddress, writeValue);
        Longword readAddress = new Longword(0);
        Longword testRead = memoryInstance.read(readAddress);
        System.out.println(testRead.getUnsigned() == 32 ? "Passed first test. 32 at index 0." : "Failed first read test.");
    
        //Reading and Writing at an adjacent address
        Longword writeAddressAdj = new Longword(1);
        Longword writeValueAdj = new Longword(69);
        memoryInstance.write(writeAddressAdj, writeValueAdj);
        Longword readAddressAdj = new Longword(1);
        Longword testReadAdj = memoryInstance.read(readAddressAdj);
        System.out.println(testReadAdj.getUnsigned() == 69 ? "Passed first test. 33 at index 1." : "Failed second read test.");
    
        //Reading and Writing 1050 at address 950
        Longword writeAddressTwo = new Longword(950);
        Longword writeValueTwo = new Longword(1050);
        memoryInstance.write(writeAddressTwo, writeValueTwo);
        Longword readAddressTwo = new Longword(950);
        Longword testReadTwo = memoryInstance.read(readAddressTwo);
        System.out.println(testReadTwo.getUnsigned() == 1050 ? "Passed second test. 1050 at index 950." : "Failed second test. 1050 at index 950.");

        //Testing an illegal exception - address past 1020
        System.out.println("The following should throw an IllegalArgumentException. It is intentional for an address at 1021.");
        Longword illegalWriteAddress = new Longword(1021);
        memoryInstance.write(illegalWriteAddress, new Longword(10));
    }

    public static void main(String [] args){
        bit_test.runTests();
        Longword_Test.runTests();
        rippleAdder_test.rippleAdderTests();
        Multiplier_test.run_tests();
        ALU_test.runTests();
        MemoryTest();
    }
}
