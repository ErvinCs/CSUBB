import exceptions.InvalidGrammarException;
import grammar.GrammarReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        GrammarReader grammarReader;
        try {
            grammarReader = new GrammarReader();
            grammarReader.run();
        } catch (InvalidGrammarException | IOException e) {
            e.printStackTrace();
        }
    }
}
