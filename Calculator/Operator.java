
import java.util.HashMap;

/**
 * @author James Clark
 */
public abstract class Operator {

    // Initializes and populates the HashMap
    private static final HashMap<String, Operator> OPERATORS;

    static {
        OPERATORS = new HashMap<>();

        OPERATORS.put("#", new PoundOperator());
        OPERATORS.put("!", new ExclamationOperator());
        OPERATORS.put("+", new AdditionOperator());
        OPERATORS.put("-", new SubtractionOperator());
        OPERATORS.put("*", new MultiplicationOperator());
        OPERATORS.put("/", new DivisionOperator());
        OPERATORS.put("^", new ExponentOperator());
        OPERATORS.put("(", new OpenParentheses());
        OPERATORS.put(")", new CloseParentheses());

    }

    // Returns priority
    public abstract int priority();

    // Returns the result of the operator's execution method
    public abstract Operand execute(Operand op1, Operand op2);

    // Checks if given token is a valid key in the hashmap
    public static boolean check(String token) {
        boolean isOperator = OPERATORS.containsKey(token);
        return isOperator;
    }

    // Returns the operator corresponding to the given token
    public static Operator get(String token) {
        return OPERATORS.get(token);
    }
}

// Classes that extend the abstract Operator class for use in the HashMap
class OpenParentheses extends Operator {

    @Override
    public int priority() {
        return -1;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}

class PoundOperator extends Operator {

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}

class ExclamationOperator extends Operator {

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}

class AdditionOperator extends Operator {

    @Override
    public int priority() {

        return 2;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {

        Operand val = new Operand(op1.getValue() + op2.getValue());
        return val;
    }
}

class SubtractionOperator extends Operator {

    @Override
    public int priority() {

        return 2;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {

        Operand val = new Operand(op1.getValue() - op2.getValue());
        return val;
    }
}

class MultiplicationOperator extends Operator {

    @Override
    public int priority() {

        return 3;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {

        Operand val = new Operand(op1.getValue() * op2.getValue());
        return val;
    }
}

class DivisionOperator extends Operator {

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {

        Operand val = new Operand(op1.getValue() / op2.getValue());
        return val;

    }
}

class ExponentOperator extends Operator {

    @Override
    public int priority() {
        return 4;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {

        int temp = 1;
        for (int i = op2.getValue(); i > 0; i--) {
            temp *= op1.getValue();
        }

        Operand val = new Operand(temp);
        return val;
    }
}

class CloseParentheses extends Operator {

    @Override
    public int priority() {
        return 5;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}
