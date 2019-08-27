package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode to perform Binary Operation (BOP)
 *
 * @author James Clark
 */
public class BopCode extends ByteCode {

    // holds arguments following the BOP ByteCode
    private ArrayList<String> args = new ArrayList<>();

    /**
    * Initializes BOP ByteCode with argument values that follow
    * @param id arguments that follow BOP ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }
    }

    /**
     * Pops two values from the top of the stack and performs given operation
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        // pop top two values off of stack
        int val2 = virMac.popStack();
        int val1 = virMac.popStack();
        int result = 0;

        // get and perform operation
        String op = args.get(0);
        switch (op) {

            case "+":
                result = val1 + val2;
                break;

            case "-":
                result = val1 - val2;
                break;

            case "/":
                result = val1 / val2;
                break;

            case "*":
                result = val1 * val2;
                break;

            case "==":
                if (val1 == val2) {
                    result = 1;
                }
                break;

            case "!=":
                if (val1 != val2) {
                    result = 1;
                }
                break;

            case "<=":
                if (val1 <= val2) {
                    result = 1;
                }
                break;

            case ">":
                if (val1 > val2) {
                    result = 1;
                }
                break;

            case ">=":
                if (val1 >= val2) {
                    result = 1;
                }
                break;

            case "<":
                if (val1 < val2) {
                    result = 1;
                }
                break;

            case "|":
                if (val1 != 0 || val2 != 0) {
                    result = 1;
                }
                break;

            case "&":
                if (val1 != 0 && val2 != 0) {
                    result = 1;
                }
                break;
        }

        // push result back onto the stack
        virMac.pushStack(result);

        // if dumping
        if (virMac.dumpState()) {
            System.out.println("BOP" + args.get(0));
        }

    }

}
