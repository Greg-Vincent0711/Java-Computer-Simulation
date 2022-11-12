public class ALU_test {

    public static void runTests(){

        //1000 - and
        System.out.println("AND ALU Test.");
        bit [] andArray = new bit[]{new bit(true), new bit(), new bit(), new bit()};
        Longword andWordOne = new Longword(10);
        Longword andWordTwo = new Longword(10);
        Longword andResult = new Longword();
        andResult.copy(ALU.doOp(andArray, andWordOne, andWordTwo));
        System.out.println(andResult.getSigned() == 10 ? "Passed AND Test." : "Failed AND Test.");

        // //1001 - or
        System.out.println("OR ALU Test.");
        bit [] orArray = new bit[]{new bit(true), new bit(), new bit(), new bit(true)};
        Longword orWordOne = new Longword(1);
        Longword orWordTwo = new Longword(2);
        Longword orResult =  new Longword();
        orResult.copy(ALU.doOp(orArray, orWordOne, orWordTwo));
        System.out.println(orResult.getSigned() == 3 ? "Passed OR Test." : "Failed OR Test.");
        
        //1010 - xor
        System.out.println("XOR ALU Test.");
        bit [] xorArray = new bit[]{new bit(true), new bit(), new bit(true), new bit()};
        Longword xorWordOne = new Longword(7);
        Longword xorWordTwo = new Longword(5);
        Longword xorResult =  new Longword();
        xorResult.copy(ALU.doOp(xorArray, xorWordOne, xorWordTwo));
        System.out.println(xorResult.getSigned() == 2 ? "Passed XOR Test." : "Failed XOR Test.");
        
        //1011 - not
        bit [] notArray = new bit[]{new bit(true), new bit(), new bit(true), new bit(true)};
        Longword notWordOne = new Longword(100);
        Longword notWordTwo = new Longword(1); //ignore
        Longword notWord = ALU.doOp(notArray, notWordOne, notWordTwo);
        System.out.println();
        System.out.println("NOT Test.");
        System.out.println();
        System.out.println(notWord.debugToString());
        System.out.println(notWordOne.debugToString());
        System.out.println(notWord.getSigned());
        System.out.println(notWordOne.getSigned());
        System.out.println();
        
        

        //1100 - left shift
        bit [] leftshiftArray = new bit[]{new bit(true), new bit(true), new bit(), new bit()};
        Longword leftshiftWordOne = new Longword(10);
        Longword leftshiftWordTwo = new Longword(4);
        Longword leftshiftResult =  new Longword();
        leftshiftResult.copy(ALU.doOp(leftshiftArray, leftshiftWordOne, leftshiftWordTwo));
        System.out.println(leftshiftResult.getSigned() == 80 ? "Passed Left Shift test." : "Failed Left Shift test.");
        
        //1101 - right shift
        bit [] rightshiftArray = new bit[]{new bit(true), new bit(true), new bit(), new bit(true)};
        Longword rightshiftWordOne = new Longword(100);
        Longword rightshiftWordTwo = new Longword(5);
        Longword rightshiftResult =  new Longword();
        rightshiftResult.copy(ALU.doOp(rightshiftArray, rightshiftWordOne, rightshiftWordTwo));
        System.out.println(rightshiftResult.getSigned() == 12 ? "Passed Right Shift test." : "Failed Right Shift test.");
        
        //1110 - add
        bit [] addArray = new bit[]{new bit(true), new bit(true), new bit(true), new bit()};
        Longword addWordOne = new Longword(50);
        Longword addWordTwo = new Longword(42);
        Longword addResult = new Longword();
        addResult.copy(ALU.doOp(addArray, addWordOne, addWordTwo));
        System.out.println(addResult.getUnsigned() == 92 ? "Correct Add from doOp" : "Incorrect Add from doOp.");
        
        //1111 - subtract
        bit [] subtractArray = new bit[]{new bit(true), new bit(true), new bit(true), new bit(true)};
        Longword subtractWordOne = new Longword(12);
        Longword subtractWordTwo = new Longword(42);
        Longword subResult = new Longword();
        subResult.copy(ALU.doOp(subtractArray, subtractWordTwo, subtractWordOne));
        System.out.println(subResult.getSigned() == 30 ? "Correct Subtraction from doOp" : "Incorrect Subtraction from doOp.");

        //0111 - multiply
        bit [] multiplyArray = new bit[]{new bit(), new bit(true), new bit(true), new bit(true)};
        Longword multiplyWordOne = new Longword(-32);
        Longword multiplyWordTwo = new Longword(-90);
        Longword multResult =  new Longword();
        multResult.copy(ALU.doOp(multiplyArray, multiplyWordOne, multiplyWordTwo));
        System.out.println(multResult.getUnsigned() == 2880 ? "Correct Multiply from doOp" : "Incorrect Multiply from doOp.");
    }

    public static void main(String[] args){
        bit_test.runTests();
        Longword_Test.runTests();
        rippleAdder_test.rippleAdderTests();
        Multiplier_test.run_tests();
        runTests();
    }
}
