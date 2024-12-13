package scanner.enums;

import java.util.Map;

/**
 * Defines the format of valid:
 *  Identifiers - A sequence of letters and digits <= 8, such that the first character is a letter or '_' associated with a data type.
 *  Constants - Integers
 *  Char - one character
 *  String - multiple characters
 */
public enum Pattern {
    Identifier("[a-zA-z_]\\w{0,7}"),
    Constant("(\\+|\\-)?([1-9])([0-9]*)"),
    Char("\'[a-z0-9]\'"),
    String("\"[a-z0-9]*\"");

    private final String regex;

    private static Map<String, Integer> valueToTextMapping;

    private Pattern(String regex){
        this.regex = regex;
    }

    public String getRegex(){
        return regex;
    }

}
