package file_utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringFileManager {
    private final File file;

    public StringFileManager(File file) {
        this.file = file;
    }

    public List<String> getFileAsSortedListAsc() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Stream<String> lines = reader.lines();

            return lines.parallel().sorted().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getFileAsSortedListDesc() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Stream<String> lines = reader.lines();

            return lines.parallel().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
