package reader;

public enum SortDirection {

    ASC("ASC"), DESC("DESC");
    final String type;

    SortDirection(String type) {
        this.type = type;
    }
}
