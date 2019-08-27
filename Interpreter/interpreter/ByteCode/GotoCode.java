package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode used to GOTO another address
 * 
 * @author James Clark
 */
public class GotoCode extends ByteCode {

    // holds arguments following the GOTO ByteCode
    private ArrayList<String> args = new ArrayList<>();
    // holds address to GOTO
    private int newAddress;

    /**
    * Initializes GOTO ByteCode with argument values that follow
    * @param id arguments that follow GOTO ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }
    }

    /**
     * Jumps to the label's address
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        virMac.setPC(newAddress);

        // if dumping
        if (virMac.dumpState()) {
            System.out.println("GOTO " + args.get(0));
        }
    }

    /**
     * Gets label
     *
     * @return label to GOTO
     */
    public String getLabel() {

        return args.get(0);
    }

    /**
     * Stores Label address
     *
     * @param jump address of label being jumped to
     */
    public void setLabel(Integer jump) {
        newAddress = jump;
    }
}
