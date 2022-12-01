public class rippleAdder_test {
    

    public static void rippleAdderTests(){
        /**Adding two positive Longwords and testing the result*/
        Longword firstLongword = new Longword(479);
        Longword secondLongword = new Longword(221);
        System.out.println(rippleAdder.add(firstLongword, secondLongword).getUnsigned() == 700 ? "Passed add test(two positives)." : "Failed test(two positives).");
        /**Adding a positive and negative Longword and testing the result*/
        Longword thirdLongword = new Longword(20);
        Longword fourthLongword = new Longword(-7);
        System.out.println(rippleAdder.add(thirdLongword, fourthLongword).getUnsigned() == 13 ? "Passed add test(one positive, one negative)." : "Failed add test(one positive, one negative).");
        // /**Adding two negative Longword and testing the result*/
        Longword fifthLongword = new Longword(-300);
        Longword sixthLongword = new Longword(-100);
        System.out.println(rippleAdder.add(fifthLongword, sixthLongword).getSigned() == -400 ? "Passed add test(two negatives)." : "Failed test(two negatives).");

        // /**Subtracting two positive Longwords and testing the result*/
        Longword longwordOne = new Longword(10);
        Longword longwordTwo = new Longword(6);
        System.out.println(rippleAdder.subtract(longwordOne, longwordTwo).getSigned() == 4 ? "Passed subtract test(two positives)." : "Failed subtract test(two positives).");
        
        /**Subtracting a positive and negative Longword and testing the result*/
        Longword longwordThree = new Longword(20);
        Longword longwordFour = new Longword(-7);
        System.out.println(rippleAdder.subtract(longwordThree, longwordFour).getSigned() == 27 ? "Passed subtract test(one positive, one negative)." : "Failed subtract test(one positive, one negative).");
        
        // /**Subtracting two negative Longword and testing the result*/
        Longword longwordFive = new Longword(-209);
        Longword longwordSix = new Longword(-10);
        System.out.println(rippleAdder.subtract(longwordFive, longwordSix).getSigned() == -199 ? "Passed test(two negatives)." : "Failed test(two negatives).");
        Longword test  = new Longword(10);
        Longword testOne  = new Longword(15);
        System.out.println("Original test value" + test);
        test.copy(testOne);
        System.out.println("New test value" + test);

    }
    

    
    public static void main(String []args){
        bit_test.runTests();
        Longword_Test.runTests();
        rippleAdderTests();
        
    }
}
