package interpreter.ByteCode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode used to WRITE to console
 * 
 * @author James Clark
 */
public class WriteCode extends ByteCode {

    /**
    * This ByteCode does not require initialization
    * @param id
    */
    @Override
    public void init(ArrayList<String> id) {
        //no args
    }

    /**
     * Prints the value at the top of the stack
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        int printVal = virMac.peekStack();
        System.out.println(printVal);

        // if dumping
        if (virMac.dumpState()) {
            System.out.println("WRITE " + printVal);
        }
    }

}
