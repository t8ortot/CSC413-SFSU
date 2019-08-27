package interpreter.ByteCode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * ByteCode to set DUMP status to allow program to print the current ByteCode being executed
 *
 * @author James Clark
 */
public class DumpCode extends ByteCode {

    // holds arguments following the DUMP ByteCode
    ArrayList<String> args = new ArrayList<>();

    /**
    * Initializes DUMP ByteCode with argument values that follow
    * @param id arguments that follow DUMP ByteCode
    */
    @Override
    public void init(ArrayList<String> id) {

        for (int i = 0; i < id.size(); i++) {
            args.add(id.get(i));
        }
    }

    /**
     * Toggles dumps state
     *
     * @param virMac virtual machine that drives program
     */
    @Override
    public void execute(VirtualMachine virMac) {

        // turn DUMP on
        if (args.get(0).equals("ON")) {
            virMac.dumpOn();

        // turn DUMP off
        } else if (args.get(0).equals("OFF")) {
            virMac.dumpOff();
        }

    }

}
