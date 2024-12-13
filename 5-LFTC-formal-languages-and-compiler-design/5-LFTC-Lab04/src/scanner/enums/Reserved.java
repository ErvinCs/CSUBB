package scanner.enums;

/**
 * The keywords supported by the language:
 *  Instructions
 *  Data types
 *  Control structures
 */
public enum Reserved {
    ENDP("beginp", 10),
    BEGINP("endp", 11),
    INT("int", 3),
    CHAR("char", 4),
    STRUCT("struct", 9),
    READ("read", 12),
    WRITE("write", 13),
    IF("if", 14),
    THEN("then", 15),
    ELSE("else", 16),
    WHILE("while", 17),
    DO("do", 18),
    DONE("done", 19);

    private final String name;
    private final Integer code;


    private Reserved(String name, Integer value){
        this.code = value;
        this.name = name;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static int isReserved(String string) {
        for (Reserved keyword : Reserved.values()) {
            if (keyword.name.equals(string))
                return keyword.code;
        }
        return -1;
    }

}
