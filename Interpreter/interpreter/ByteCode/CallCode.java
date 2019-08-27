package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode used to CALL functions
 *
 * @author James Clark
 */
public class CallCode extends ByteCode {

    // holds arguments following the CALL ByteCode
    private ArrayList<String> args = new ArrayList<>();
    // new address where program counter should jump to
    private int newAddress;

    /**
    * Initializes CALL ByteCode with argument values that follow
    * @param id arguments that follow CALL ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }
    }

    /**
     * Pushes return address and jumps to label
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        // push return address, which is the current address
        virMac.pushRetAdd(virMac.getPC());
        // jumps virtual machine to new address where called function is located
        virMac.setPC(newAddress - 1);

        // if dumping
        if (virMac.dumpState()) {
            String[] split = args.get(0).split("<");
            String function = split[0];
            System.out.println("CALL " + function + "  " + function + "(" + virMac.getArgs() + ")");
        }
    }

    /**
     * Gets label of function call
     *
     * @return label
     */
    public String getLabel() {
        return args.get(0);
    }

    /**
     * Stores address of label
     *
     * @param jump address of where to jump for function
     */
    public void setLabel(Integer jump) {

        newAddress = jump;
    }
}
