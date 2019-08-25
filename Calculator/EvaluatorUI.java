
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple calculator GUI
 * @author James Clark
 */
public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
        "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        for (int i = 0; i < 20; i++) {
            buttons[i] = new Button(bText[i]);
        }

        //add buttons to button panel
        for (int i = 0; i < 20; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < 20; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        // If the "C" or "CE" buttons are pressed, empty the text field.
        if (arg0.getSource() == buttons[19] || arg0.getSource() == buttons[18]) {
            txField.setText("");

            // If the "=" button is pressed, evaluate the given expression in the text field
        } else if (arg0.getSource() == buttons[14]) {
            Evaluator temp = new Evaluator();
            txField.setText(Integer.toString(temp.eval(txField.getText())));

            // If any operator or operand button is pressed, add it to what already 
            // exists in the text field.
        } else {
            for (int i = 0; i < buttons.length; i++) {
                if (arg0.getSource() == buttons[i]) {
                    txField.setText(txField.getText() + bText[i]);
                }
            }
        }
    }
}
