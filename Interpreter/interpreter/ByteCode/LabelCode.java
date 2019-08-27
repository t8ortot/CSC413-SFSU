package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode to specify a LABEL location
 *
 * @author James Clark
 */
public class LabelCode extends ByteCode {

    // holds arguments following the ARGS ByteCode
    private ArrayList<String> args = new ArrayList<>();

    /**
    * Initializes LABEL ByteCode with argument values that follow
    * @param id arguments that follow LABEL ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }
    }

    /**
     * This ByteCode has no action
     *
     * @param virMac
     */
    @Override
    public void execute(VirtualMachine virMac) {
        //no actions

        // if dumping
        if (virMac.dumpState()) {
            System.out.println("LABEL " + args.get(0));
        }
    }

    /**
     * Gets label
     *
     * @return label
     */
    public String getLabel() {
        return args.get(0);
    }
}
