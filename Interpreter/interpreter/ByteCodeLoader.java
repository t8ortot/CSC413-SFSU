package interpreter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import interpreter.ByteCode.*;

/**
* Reads the input and translates it into ByteCode objects
* 
* @author James Clark
*/
public class ByteCodeLoader extends Object {

    // reads input
    private BufferedReader byteSource;
    // program object to run in VM
    private Program program;

    /**
     * Constructor for ByteCodeLoader
     *
     * @param file input file
     * @throws IOException
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * Constructs a program with the input bytecode objects
     *
     * @return the program
     */
    public Program loadCodes() {

        try {

            program = new Program();

            // reads each line from input
            String line = byteSource.readLine();
            while (line != null) {

                ArrayList<String> args = new ArrayList<>();
                //tokenize input
                StringTokenizer tokenizer = new StringTokenizer(line);
                String code = tokenizer.nextToken();

                // if code exists
                if (!code.isEmpty()) {
                    //get class name of the code to fetch
                    String codeClass = CodeTable.getClassName(code);

                    // add arguments
                    while (tokenizer.hasMoreTokens()) {
                        args.add(tokenizer.nextToken());
                    }

                    //makes the bytecode object
                    ByteCode byteCode = (ByteCode) Class.forName("interpreter.ByteCode." + codeClass).newInstance();
                    byteCode.init(args);

                    // adds bytecode to program
                    program.add(byteCode);

                    // gets next line
                    line = byteSource.readLine();
                }
            }

            // resolves addresses for labels and jumps
            program.resolveAddrs(program);

        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | IOException e) {
            System.out.println(e);
        }

        // return constructed program
        return program;
    }

}
