package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode to specify where to HALT the execution of code
 *
 * @author James Clark
 */
public class HaltCode extends ByteCode {

    /**
    * This ByteCode does not require initialization
    */
    @Override
    public void init(ArrayList<String> id) {
        //no args
    }

    /**
     * HALTs the Virtual Machine
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        virMac.stopVM();

        // if dumping
        if (virMac.dumpState()) {
            System.out.println("HALT");
        }
    }

}
