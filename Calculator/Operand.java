import java.util.regex.*;

/**
 * Operand class to hold values as objects
 * @author James Clark
 */
public class Operand {

    //holds value
    private int val;

    public Operand(String token) {
        //parses token, converts to int
        this.val = Integer.parseInt(token); 
    }

    public Operand(int value) {
        //sets val to given value
        this.val = value; 
    }

    public int getValue() {
        //returns val
        return this.val; 
    }

    //Uses the regular expression package to check if the token contains a
    //numeric value. If so, isNum equals true, and the function returns
    //isNum.
    public static boolean check(String token) {
        boolean isNum = Pattern.matches(".*\\d.*", token);
        return isNum;
    }
}
