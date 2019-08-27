package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ByteCode used to READ a user's input
 * 
 * @author James Clark
 */
public class ReadCode extends ByteCode {

    /**
    * This ByteCode has nothing to initialize
    *
    * @param id 
    */
    @Override
    public void init(ArrayList<String> id) {
        //no args
    }

    /**
     * Asks for input from the user and stores the value at the top of the stack
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        // prompts and retreives user input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        while (!input.hasNextInt()) {
            input.next();
            System.out.print("Invalid entry. Please enter an integer: ");
        }

        // pushes new value onto stack
        int inputVal = input.nextInt();
        virMac.pushStack(inputVal);

        // if dumping
        if (virMac.dumpState()) {
            System.out.println("READ " + inputVal);
        }
    }

}
