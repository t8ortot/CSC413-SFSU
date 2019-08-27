package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode to STORE a value into the stack
 * 
 * @author James Clark
 */
public class StoreCode extends ByteCode {

    // holds arguments following the ARGS ByteCode
    private ArrayList<String> args = new ArrayList<>();

    /**
    * Initializes STORE ByteCode with argument values that follow
    * @param id arguments that follow STORE ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }

    }

    /**
     * Stores value into a location in the stack
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        int offset = Integer.parseInt(args.get(0));
        int value = virMac.setVar(offset);

        // if dumping
        if (virMac.dumpState()) {
            System.out.println("STORE " + args.get(0) + " " + args.get(1) + "  " + args.get(1) + "=" + value);
        }
    }

}
