package scanner.enums;

import java.util.Map;

/**
 *
 */
public enum Separator {
    SEMICOLON(";", 30),
    COMMA(",", 31),
    DOT(".", 32),
    OPEN_P("(", 33),
    CLOSED_P(")", 34),
    OPEN_B("[", 35),
    CLOSED_B("]", 36),
    OPEN_C("{", 37),
    CLOSED_C("}", 38);

    private final String name;
    private final Integer code;

    private static Map<String, Integer> valueToTextMapping;

    private Separator(String name, Integer value){
        this.code = value;
        this.name = name;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static int isSeparator(String string) {
        for (Separator sep : Separator.values()) {
            if (sep.name.equals(string))
                return sep.code;
        }
        return -1;
    }

    public static int isOpenBracket(String string) {
        if (string.equals("("))
            return OPEN_P.code;
        else if (string.equals("["))
            return OPEN_B.code;
        else if (string.equals("{"))
            return OPEN_C.code;
        else
            return -1;
    }

    public static int isClosedBracket(String string) {
        if (string.equals(")"))
            return CLOSED_P.code;
        else if (string.equals("]"))
            return CLOSED_B.code;
        else if (string.equals("}"))
            return CLOSED_C.code;
        else
            return -1;
    }
}
