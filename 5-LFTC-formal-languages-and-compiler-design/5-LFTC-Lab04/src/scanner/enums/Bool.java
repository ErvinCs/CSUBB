package scanner.enums;

/**
 * The boolean values supported by the language
 */
public enum Bool {
    TRUE("true", 23),
    FALSE("false", 24);

    private final String name;
    private final Integer code;


    private Bool(String name, Integer value){
        this.code = value;
        this.name = name;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static int isBool(String string) {
        for (Bool keyword : Bool.values()) {
            if (keyword.name.equals(string))
                return keyword.code;
        }
        return -1;
    }
}
