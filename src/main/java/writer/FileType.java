package writer;

public enum FileType {

    JSON("JSON"), XML("XML"), TXT("TXT");

    private final String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
