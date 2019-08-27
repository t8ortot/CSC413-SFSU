package interpreter;
import java.util.ArrayList;
import java.util.Stack;

/**
* The Runtime Stack for the program being executed. Responsible for maintaining scopes and variables
*
* @author James Clark
*/
public class RunTimeStack {

    // stack to store values
    private ArrayList runTimeStack;
    // stack to store where frames are
    private Stack<Integer> framePointer;

    /**
    * Contructor
    */
    public RunTimeStack() {

        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // main is the entry point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    /**
     * Prints out all the information about the runTimeStack formatted to see
     * the frames.
     */
    public void dump() {

        int currFrame = 0;

        while (currFrame != framePointer.size()) {

            //if there is a next frame
            if (currFrame < framePointer.size() - 1) {

                //if the frame is empty
                if (currFrame == framePointer.get(currFrame + 1)) {

                    System.out.print("[] ");

                    //if frame contains elements
                } else {

                    System.out.print("[");

                    //prints out values in the stack
                    for (int i = framePointer.get(currFrame); i < framePointer.get(currFrame + 1); i++) {

                        System.out.print(runTimeStack.get(i));

                        if (i < framePointer.get(currFrame + 1) - 1) {

                            System.out.print(",");
                        }
                    }

                    System.out.print("] ");

                }

                //if there is not a next frame
            } else {

                System.out.print("[");

                //print elements in stack
                for (int i = framePointer.get(currFrame); i < runTimeStack.size(); i++) {
                    System.out.print(runTimeStack.get(i));
                    if (i < runTimeStack.size() - 1) {
                        System.out.print(",");
                    }
                }

                System.out.print("] ");

            }
            currFrame++;
        }
        System.out.println();

    }

    /**
     * Gets the top value in the runTimeStack
     *
     * @return top value in RTS
     */
    public int peek() {

        return (int) runTimeStack.get(runTimeStack.size() - 1);
    }

    /**
     * Pops top value of runTimeStack
     *
     * @return popped value
     */
    public int pop() {

        return (int) runTimeStack.remove(runTimeStack.size() - 1);
    }

    /**
     * Adds a value to the top of the runTimeStack
     *
     * @param i pushed value
     * @return pushed value
     */
    public int push(int i) {

        runTimeStack.add(i);
        return i;
    }

    /**
     * Creates a new frame with an offset from the top of the stack
     *
     * @param offset
     */
    public void newFrameAt(int offset) {

        framePointer.add(runTimeStack.size() - offset);
    }

    /**
     * Holds onto top value, pops the rest and and the frame, pushes the held
     * value back on top
     */
    public void popFrame() {

        int popped = (int) runTimeStack.remove(runTimeStack.size() - 1);
        int frame = framePointer.pop();
        for (int i = runTimeStack.size() - 1; i >= frame; i--) {

            runTimeStack.remove(runTimeStack.size() - 1);
        }

        runTimeStack.add(popped);
    }

    /**
     * Sets a variable to the value on top of the stack
     *
     * @param offset
     * @return stored value
     */
    public int store(int offset) {

        int storing = (int) runTimeStack.remove(runTimeStack.size() - 1);
        runTimeStack.set(offset + framePointer.peek(), storing);
        return storing;

    }

    /**
     * Loads a value from the stack and places it on the top
     *
     * @param offset
     * @return loaded value
     */
    public int load(int offset) {

        int loading = (int) runTimeStack.get(offset + framePointer.peek());
        runTimeStack.add(loading);
        return loading;
    }

    /**
     * Pushes an Integer object into the stack
     *
     * @param i
     * @return pushed value
     */
    public Integer push(Integer i) {

        runTimeStack.add(i);
        return i;
    }

    /**
     * Gets size of the stack
     *
     * @return
     */
    public int size() {

        return runTimeStack.size();
    }

    /**
     * Gets elements in the top frame
     *
     * @return
     */
    public String args() {

        String args = "";
        for (int i = runTimeStack.size() - 1; i >= framePointer.peek(); i--) {

            args = args + runTimeStack.get(i);
            if (i != framePointer.peek()) {

                args = args + ",";
            }
        }
        return args;
    }

}
