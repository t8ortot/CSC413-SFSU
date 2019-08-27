package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode to LOAD a variable
 *
 * @author James Clark
 */
public class LoadCode extends ByteCode {

    // holds arguments following the ARGS ByteCode
    private ArrayList<String> args = new ArrayList<>();

    /**
    * Initializes LOAD ByteCode with argument values that follow
    * @param id arguments that follow LOAD ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }

    }

    /**
     * Loads a value in the stack to the top of the stack
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        int offset = Integer.parseInt(args.get(0));
        virMac.loadVar(offset);

        // if dumping
        if (virMac.dumpState()) {
            System.out.println("LOAD " + args.get(0) + " " + args.get(1));
        }
    }

}
