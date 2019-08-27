package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode to POP a number a values off the stack
 * @author James Clark
 */
public class PopCode extends ByteCode {

    // holds arguments following the ARGS ByteCode
    private ArrayList<String> args = new ArrayList<>();

    /**
    * Initializes POP ByteCode with argument values that follow
    * @param id arguments that follow POP ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }
    }

    /**
     * Pops the value at the top of the stack
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        // get number of pops and perform
        int popping = Integer.parseInt(args.get(0));
        for (int i = 0; i < popping; i++) {
            if (virMac.stackSize() > 0) {
                virMac.popStack();
            }
        }

        // if dumping
        if (virMac.dumpState()) {
            System.out.println("POP" + args.get(0));
        }
    }

}
