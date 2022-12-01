Through ICSI 404 - Assembly Computer Organization, we've created a working computer through different projects that build upon each other.
Main is the home of project 10 - or the entire computer within itself in its finished state.

Writing Instructions:
- The CPU test files give some good examples of how to create instructions, but should someone see this README first...
  Make sure all instructions include a space at the end of their string after the last word to be assembled. 
    "ADD R1 R2 R3", is incorrect syntax. "ADD R1 R2 R3 ", is correct syntax for readable instructions. For binary instructions, this is unnecessary.
  In order to type instructions in a readable format, create a string array with your instructions, and then use the Assembler to convert them into a machine readable format before passing them into an instance of a computer object to be preloaded and ran. 
  For example:
    String [] exReadable = {
      "MOVE R1 5 ",
      "MOVE R7 10 ",
      "ADD R1 R7 R10 ",
      "CALL 9 ",
      "HALT ",
      "INTERRUPT0 ",
    };
    Computer Test = new Computer();
    Test.preLoad(Assembler.assemble(exReadable));
    Test.run();
    
    It's not recommended, but you can also pass in binary instructions,
      For example:
        String [] exMachineLang = {
          "0001 0011 0000 1010", //Move R3 10
          "0001 0111 0000 1111", //Move R7 15
          "0010 0000 0000 0000", //Memory printing
        }
     Test.preLoad(exMachineLang);
     Test.run();
    
Commands that can be used:
- MOVE. In order to use the arithmetic operations that will be mentioned below, there has to be a value put into a register first.
  To use Move: "MOVE <register from 0 to 15> < Integer from -255 to 255 "
  For ex: "MOVE R1 13 ", 

- 4 main arithmetic calculations can be performed with the following syntax:
  <Operation> <First operand - Register from 0 to 15> <Second operand - Register from 0 to 15> <Storing Register from 0 to 15>
  For ex: "ADD R1 R2 R3 ", "SUBTRACT R5 R7 R9 ", "MULTIPLY R9 R15 R3 ",

- COMPARE and BRANCH instructions.

  
