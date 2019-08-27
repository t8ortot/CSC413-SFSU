package interpreter;
import java.io.*;

/**
* Holds the main function to run the program. Initializes a program object and then runs it in a virtual machine.
*
* @author James Clark
*/
public class Interpreter {

    // initialized with code and has bytecodes extracted
    private ByteCodeLoader bcl;

    public Interpreter(String codeFile) {
        try {
            CodeTable.init();
            bcl = new ByteCodeLoader(codeFile);
        } catch (IOException e) {
            System.out.println("**** " + e);
        }
    }

    void run() {
        // extract program from input
        Program program = bcl.loadCodes();
        // initialize virtual machine with program
        VirtualMachine vm = new VirtualMachine(program);
        // execute the program
        vm.executeProgram();
    }

    public static void main(String args[]) {

        if (args.length == 0) {
            System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
            System.exit(1);
        }
        (new Interpreter(args[0])).run();
    }
}
