public class Multiplier_test {

    public static void run_tests(){
        /**Multiplying two positive numbers */
        System.out.println(Multiplier.multiply(new Longword(50), new Longword(35)).getSigned() == 1750 ? "Passed positive multiply. " : "Failed positive Test");
        /**Multiplying a negative number and positive number */
        System.out.println(Multiplier.multiply(new Longword(-29), new Longword(12)).getSigned() == -348 ? "Passed positive/negative multiply. " : "Failed positive/negative Test");
        /**Multiplying two negative numbers */
        System.out.println(Multiplier.multiply(new Longword(-79), new Longword(-31)).getSigned() == 2449 ? "Passed double negative multiply test." : "Failed double negative multiply test.");
        /**Multiplying a negative number with one */
        System.out.println(Multiplier.multiply(new Longword(-2), new Longword(1)).getSigned() == -2 ? "Passed negative by one test." : "Failed negative by one test.");
        /**Multiplying a positive number with one */
        System.out.println(Multiplier.multiply(new Longword(5), new Longword(1)).getSigned() == 5 ? "Passed positive by one test." : "Failed positive by one test.");
        /**Multiplying a positive number with zero */
        System.out.println(Multiplier.multiply(new Longword(1000), new Longword(0)).getSigned() == 0 ? "Passed positive by zero test." : "Failed positive by zero test.");
        /**Multiplying a negative number with zero */
        System.out.println(Multiplier.multiply(new Longword(-1230), new Longword(0)).getSigned() == 0 ? "Passed negative by zero test." : "Failed negatice by zero test.");
    }

    public static void main(String [] args){
        bit_test.runTests();
        Longword_Test.runTests();
        rippleAdder_test.rippleAdderTests();
        run_tests();
    }
}
