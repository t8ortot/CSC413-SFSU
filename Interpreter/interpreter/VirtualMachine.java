package interpreter;

import interpreter.ByteCode.*;
import java.util.Stack;

/**
* Virtual Machine to drive the execution of the input program
* 
* @author James Clark
*/
public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack returnAddrs;
    private Program program;
    private int pc;
    private boolean isRunning;
    private boolean dump;

    /**
     * Constructs the VirtualMachine
     *
     * @param program
     */
    protected VirtualMachine(Program program) {

        this.program = program;
        dump = false;
    }

    /**
     * Executes program by incrementing the PC and executing the code on the
     * line, lso handles dumping if necessary
     */
    public void executeProgram() {

        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack();
        isRunning = true;

        while (isRunning) {

            ByteCode code = program.getCode(pc);
            code.execute(this);
            if (dump && !(code instanceof DumpCode)) {

                runStack.dump();
            }

            pc++;
        }

    }

    /**
     * Used by DUMP to turn dump on
     */
    public void dumpOn() {

        dump = true;
    }

    /**
     * Used by DUMP to turn dump off
     */
    public void dumpOff() {

        dump = false;
    }

    /**
     * Used by bytecodes for dump's state
     *
     * @return if dump is true of false
     */
    public boolean dumpState() {

        return dump;
    }

    /**
     * Used by HALT to stop the VM
     */
    public void stopVM() {

        isRunning = false;
    }

    /**
     * Used by BOP & POP & FALSEBRANCH & STORE to pop the top of the runStack
     *
     * @return popped value
     */
    public int popStack() {

        return runStack.pop();

    }

    /**
     * Used by FALSEBRANCH & GOTO & CALL & RETURN to change the program counter
     *
     * @param line
     */
    public void setPC(int line) {

        pc = line;
    }

    /**
     * Used by STORE to set variable
     *
     * @param offset element to be changed, starting from beginning of frame
     * @return 
     */
    public int setVar(int offset) {

        return runStack.store(offset);
    }

    /**
     * Used by LOAD to load a variable to the top of the stack
     *
     * @param offset
     */
    public void loadVar(int offset) {

        runStack.load(offset);
    }

    /**
     * Used by BOP & LIT & READ to push given value to top of stack
     *
     * @param value
     */
    public void pushStack(int value) {

        runStack.push(value);
    }

    /**
     * Used by ARGS to create new frame with the offset from the top of the
     * stack
     *
     * @param offset
     */
    public void pushFrame(int offset) {

        runStack.newFrameAt(offset);
    }

    /**
     * Used by CALL to push a return value for PC into returnAddrs
     *
     * @param address
     */
    public void pushRetAdd(int address) {

        returnAddrs.push(address);
    }

    /**
     * Used by CALL to get the current value of PC
     *
     * @return current value of PC
     */
    public int getPC() {

        return pc;
    }

    /**
     * Used by RETURN to pop a frame out of the frameStack
     */
    public void popFrame() {

        runStack.popFrame();
    }

    /**
     * Used by RETURN to get the next return address
     *
     * @return the top value of returnAddrs
     */
    public int popRetAdd() {

        return (int) returnAddrs.pop();
    }

    /**
     * Used by RETURN & WRITE to get the top value of the stack
     *
     * @return top value on the stack
     */
    public int peekStack() {

        return runStack.peek();
    }

    /**
     * Used by POP to get the size of the runStack
     *
     * @return size of the runStack
     */
    public int stackSize() {

        return runStack.size();
    }

    /**
     * Used by CALL to get parameters for a function
     *
     * @return string of parameter
     */
    public String getArgs() {

        return runStack.args();
    }
}
