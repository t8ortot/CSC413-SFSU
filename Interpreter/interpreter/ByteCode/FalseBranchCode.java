package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode to establish a branch to a new address if the argument is false (FALSEBRANCH)
 *
 * @author James Clark
 */
public class FalseBranchCode extends ByteCode {

    // holds arguments following the FALSEBRANCH ByteCode
    private ArrayList<String> args = new ArrayList<>();
    // hold address to jump to
    private int newAddress;

    /**
    * Initializes FALSEBRANCH ByteCode with argument values that follow
    * @param id arguments that follow FALSEBRANCH ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }
    }

    /**
     * If the top value is 0, jump to label's address
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        int popped = virMac.popStack();
        // if false, branch
        if (popped == 0) {
            virMac.setPC(newAddress - 1);
        }

        // if dumping
        if (virMac.dumpState()) {
            System.out.println("FALSEBRANCH " + args.get(0));
        }
    }

    /**
     * Gives label
     *
     * @return label to branch to
     */
    public String getLabel() {

        return args.get(0);
    }

    /**
     * Stores the labels address
     *
     * @param jump label's address to branch to
     */
    public void setLabel(Integer jump) {
        newAddress = jump;
    }

}
