Through ICSI 404 - Assembly Computer Organization, we've created a working computer through different projects that build upon each other.
This main branch is the home of the computer in it's completed state.

Writing Instructions:
- The CPU test files give some good examples of how to create instructions, but should someone see this README first...
  Make sure all instructions are in uppercase and include a space at the end of their string after the last word to be assembled.
    "ADD R1 R2 R3", is incorrect syntax. "ADD R1 R2 R3 ", is correct syntax for readable instructions. For binary instructions, this is unnecessary.
  In order to type instructions in a readable format, create a string array with your instructions, and then use the Assembler to convert them into a machine readable format before passing them into an instance of a computer to be preloaded and ran. 
  For example:
    String [] exReadable = {
      "MOVE R1 5 ",
      "MOVE R7 10 ",
      "ADD R1 R7 R10 ",
      "JUMP 1 ",
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

- JUMP, COMPARE and BRANCH instructions.
JUMP by itself simply changes wheere the program counter points in the program. It is an absolute point in the program instead of an offset from where the program counter currently is. It also cannot be a negative value. 
In order to develop the needed components for conditionals found in all programming languages, there are 5 different commands to be used in 
conjunction with COMPARE.
COMPARE is the boolean operation comparing the values stored in two registers. Then, it can be used in conjuction with 
BRANCH If Equal, If Greater Than, If Not Equal, If Greater Than or Equal.

  
