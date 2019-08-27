package interpreter.ByteCode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode to instantiate a LITeral value
 *
 * @author James Clark
 */
public class LitCode extends ByteCode {
    
    // holds arguments following the ARGS ByteCode
    private ArrayList<String> args = new ArrayList<>();

    /**
    * Initializes LIT ByteCode with argument values that follow
    * @param id arguments that follow LIT ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }
    }

    /**
     * Pushes the given value to the top of the stack
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        int pushing = Integer.parseInt(args.get(0));
        virMac.pushStack(pushing);

        // if dumping
        if (virMac.dumpState()) {

            // if no variable name given
            if (args.size() == 1) {
                System.out.println("LIT " + args.get(0));

            //if variable name given
            } else {
                System.out.println("LIT " + args.get(0) + " " + args.get(1) + "  " + args.get(1) + "=" + pushing);
            }
        }
    }

}
