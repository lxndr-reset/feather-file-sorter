package file_utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringFileManager {
    private final File file;

    public StringFileManager(File file) {
        this.file = file;
    }

    public List<String> getFileAsSortedListAsc() {
        return getSortedAs(Comparator.naturalOrder());
    }

    public List<String> getFileAsSortedListDesc() {
        return getSortedAs(Comparator.reverseOrder());
    }

    private List<String> getSortedAs(Comparator<String> comparator) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().parallel()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
