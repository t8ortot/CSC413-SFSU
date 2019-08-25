import java.util.*;

/**
 * An evaluator class used to parse strings and calculate the equation entered.
 * @author James Clark
 */
public class Evaluator {

    // Stack to hold operand objects
    private Stack<Operand> operandStack;
    // Stack to hold operator objects
    private Stack<Operator> operatorStack;

    // Tokenizer to parse input
    private StringTokenizer tokenizer;
    // Delimiters to use when parsing
    private static final String DELIMITERS = "()+-*^/#! ";

    /**
    * Constructor
    */
    public Evaluator() {
        // creates new stacks
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    /**
    * Parses, tokenizes, and evaluates input.
    * @param expression input equation to be evaluated
    * @return evaluated value
    */
    public int eval(String expression) {
        // token holder
        String token;

        // initialize tokenizer with input and delimiters
        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

        // Initializes stacks and inserts the expression start operator (#) into the operatorStack
        operandStack.clear();
        operatorStack.clear();
        operatorStack.push(Operator.get("#"));

        // while there are more tokens
        while (this.tokenizer.hasMoreTokens()) {
            // filters out spaces
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // if token is an operand
                if (Operand.check(token)) {
                    // push operand into the stack
                    operandStack.push(new Operand(token));

                // if token is not a recognized operand
                } else {
                    // check if token is a valid operator
                    if (!Operator.check(token)) {
                        System.out.println("*****invalid token******");
                        System.exit(1);
                    }

                    // Uses the Operator class's HashMap to create the correct operator.
                    Operator newOperator = (Operator) Operator.get(token);

                    // If the operator is not an open parentheses (priority: -1)
                    if (newOperator.priority() != -1) {

                        // If the previous operator(s) are of greater priority than the new operator, evaluate the expression(s) in the stacks.
                        while (operatorStack.peek().priority() >= newOperator.priority()) {

                            Operator oldOpr = operatorStack.pop();
                            Operand op2 = operandStack.pop();
                            Operand op1 = operandStack.pop();
                            operandStack.push(oldOpr.execute(op1, op2));
                        }
                    }

                    // Push the new operator
                    operatorStack.push(newOperator);

                    // If the new operator was a ')', then evaluate until the next '(' (priority: -1) is found.
                    if (")".equals(token)) {
                        operatorStack.pop();
                        Operator operator = operatorStack.pop();

                        while (operator.priority() != -1) {
                            Operand op2 = operandStack.pop();
                            Operand op1 = operandStack.pop();

                            Operand temp = operator.execute(op1, op2);
                            operandStack.push(temp);

                            operator = operatorStack.pop();
                        }
                    }
                }
            }
        }

        // While-loop to evaluate the remainder of the expression (until the '#' operator (priority: 0) is reached)
        while (operatorStack.peek().priority() != 0) {
            Operator operator = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push(operator.execute(op1, op2));
        }

        // Returns last remaining operand as answer
        return operandStack.peek().getValue();
    }
}
