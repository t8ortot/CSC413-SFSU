package interpreter;
import java.util.ArrayList;
import interpreter.ByteCode.*;
import java.util.HashMap;

/**
* The Program object is used to hold all ByteCodes in order of execution
*
* @author James Clark
*/
public class Program {

    // the program is an ArrayList of ByteCodes
    private ArrayList<ByteCode> program;

    /**
     * Constructs new Program
     */
    public Program() {
        program = new ArrayList<>();
    }

    /**
     * Gets the bytecode on the line with the same value as PC
     *
     * @param pc line
     * @return bytecode
     */
    protected ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    /**
     * Gets size of the program
     *
     * @return size
     */
    public int getSize() {
        return this.program.size();
    }

    /**
     * Adds bytecode to the program
     *
     * @param code
     */
    public void add(ByteCode code) {
        program.add(code);
    }

    //Holds all labels and their locations
    private static HashMap<String, Integer> labels = new HashMap<>();

    /**
     * Resolves all label addresses so the virtual machine knows where to jump to
     *
     * @param program Program object that holds a list of ByteCodes
     */
    public void resolveAddrs(Program program) {

        //gathers all labels and their locations in the program
        for (int i = 0; i < program.getSize(); i++) {

            if (this.program.get(i) instanceof LabelCode) {

                LabelCode label = (LabelCode) this.program.get(i);
                labels.put(label.getLabel(), i);
            }
        }

        //finds all bytecodes that may have labels on them and gives them the address
        //of the label
        for (int i = 0; i < program.getSize(); i++) {

            if (this.program.get(i) instanceof GotoCode) {

                Integer jump;
                GotoCode goToCode = (GotoCode) this.program.get(i);
                jump = labels.get(goToCode.getLabel());
                goToCode.setLabel(jump);

            } else if (this.program.get(i) instanceof FalseBranchCode) {

                Integer jump;
                FalseBranchCode falseCode = (FalseBranchCode) this.program.get(i);
                jump = labels.get(falseCode.getLabel());
                falseCode.setLabel(jump);

            } else if (this.program.get(i) instanceof CallCode) {

                Integer jump;
                CallCode callCode = (CallCode) this.program.get(i);
                jump = labels.get(callCode.getLabel());
                callCode.setLabel(jump);

            }
        }

    }

}
