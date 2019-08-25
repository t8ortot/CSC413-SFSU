package lexer;

import java.util.HashMap;

/**
 * The Symbol class is used to store all user strings along with an indication
 * of the kind of strings they are; e.g. the id "abc" will store the "abc" in
 * name and Sym.Tokens.Identifier in kind
 */
public class Symbol {

    private final String name;
    // token kind of symbol
    private final Tokens kind;

    private Symbol(String n, Tokens kind) {
        name = n;
        this.kind = kind;
    }

    // symbols contains all strings in the source program
    private static final HashMap<String, Symbol> symbols = new HashMap<String, Symbol>();

    @Override
    public String toString() {
        return name;
    }

    public Tokens getKind() {
        return kind;
    }

    /**
     * Return the unique symbol associated with a string. Repeated calls to
     * <tt>symbol("abc")</tt> will return the same Symbol.
     *
     * @param newTokenString
     * @param kind
     * @return
     */
    public static Symbol symbol(String newTokenString, Tokens kind) {
        Symbol s = symbols.get(newTokenString);

        if (s == null) {
            // bogus string so don't enter into symbols
            if (kind == Tokens.BogusToken) {
                return null;
            }

            //System.out.println("new symbol: "+u+" Kind: "+kind);
            s = new Symbol(newTokenString, kind);
            symbols.put(newTokenString, s);
        }

        return s;
    }
}
