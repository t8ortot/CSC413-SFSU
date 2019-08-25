/**
 * Used to test evaluator class
 * @author James Clark
 */
public class EvaluatorTester {

    public static void main(String[] args) {
        Evaluator evaluator = new Evaluator();

        for (String arg : args) {
            System.out.format("%s = %d\n", arg, evaluator.eval(arg));
        }
    }
}
