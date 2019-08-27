# Interpreter

This interpreter program shows how Java converts code into ByteCodes and then exeuctes them using a Virtual Machine to become a portable language across multiple operating systems. In this case, we use a fake coding language .x and perform the same idea upon it.

Learned how to manage a larger codebase and about how interpreters operate.

How to Run (assuming in Interpreter directory):
- javac interpreter/*.java interpreter/ByteCode/*.java
- java interpreter/Interpreter (name of input here ex: "factorial.x.cod")

How to Use:
- When starting the program, specify which .x.cod file you would like to use. The options are:
	- factorial.x.cod
	- fib.x.cod
- Both files have the DUMP code enabled at the beginning of their code, which allows you to see the internal execution of the program. If you would like to see the execution without the dump, please remove this line.
- The program will prompt for user input:
	- factorial will prompt the input for factorial operation to be applied to
	- fib will prompt for which number of the fibonacci sequence should be printed to the console
- After execution, the proram will write the result to the console and bring the program to a HALT.