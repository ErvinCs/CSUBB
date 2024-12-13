package Util;

enum XmlElement {
    FIELD("field"), NAME("name"), TYPE("type"), VALUE("value"), ENTITY("entity"), CLASS("class");

    private final String value;

    XmlElement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}