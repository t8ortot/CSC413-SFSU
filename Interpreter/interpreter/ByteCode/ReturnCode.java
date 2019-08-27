package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode used to RETURN to a function call
 *
 * @author James Clark
 */
public class ReturnCode extends ByteCode {

    // holds arguments following the ARGS ByteCode
    private ArrayList<String> args = new ArrayList<>();

    /**
    * Initializes RETURN ByteCode with argument values that follow
    * @param id arguments that follow RETURN ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }
    }

    /**
     * Pops frame and returns to where the function was called from
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        virMac.setPC(virMac.popRetAdd());
        virMac.popFrame();

        //if dumping
        if (virMac.dumpState()) {

            if (args.size() == 1) {
                String[] split = args.get(0).split("<");
                String function = split[0];
                System.out.println("RETURN " + function + "  " + function + " exit: " + virMac.peekStack());
            } else {
                System.out.println("RETURN");
            }
        }
    }
}
