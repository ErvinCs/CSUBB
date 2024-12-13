package scanner.enums;

import java.util.Map;

/**
 * The Operators supported by the language:
 *  Logic
 *  Arithmetic
 *  Comparison
 */
public enum Operator {
    AND("&&", 20),
    OR("||", 21),
    NOT("!", 22),
    PLUS("+", 40),
    STAR("*", 41),
    MINUS("-", 42),
    DIV("/", 43),
    MOD("%", 44),
    ASSIGNMENT("=", 50),
    EQUALS("==", 55),
    NOT_EQUALS("!=", 56),
    SMALLER("<", 51),
    SMALLER_EQ("<=", 53),
    GREATER(">", 52),
    GREATER_EQ(">=", 54);

    private final String name;
    private final Integer code;

    private static Map<String, Integer> valueToTextMapping;

    private Operator(String name, Integer value){
        this.code = value;
        this.name = name;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static int isOperator(String string) {
        for (Operator op : Operator.values()) {
            if (op.name.equals(string))
                return op.code;
        }
        return -1;
    }
}
