package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode to specify entered arguments
 *
 * @author James Clark
 */
public class ArgsCode extends ByteCode {

    // holds arguments following the ARGS ByteCode
    private ArrayList<String> args = new ArrayList<>();

    /**
    * Initializes ARGS ByteCode with argument values that follow
    * @param id arguments that follow ARGS ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }
    }

    /**
     * Creates a new frame with an amount of arguments
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        int offset = Integer.parseInt(args.get(0));
        virMac.pushFrame(offset);

        //if dumping
        if (virMac.dumpState()) {
            System.out.println("ARGS " + args.get(0));
        }
    }

}
