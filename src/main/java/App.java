import file_utils.StringFileManager;
import file_utils.VariousFileWriter;
import test_files_utils.TestFileGenerator;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class App {
    public static void main(String[] args) {
        VariousFileWriter.FileType fileType;

        try {
            String lowerCase = args[0].toUpperCase(Locale.ROOT);
            fileType = VariousFileWriter.FileType.valueOf(lowerCase);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("App.main args format: <xml/json/txt> <asc/desc>", e);
        }
        // generates 1 GB test file
        File generated = TestFileGenerator.generate("input");

        VariousFileWriter fileWriter = getVariousFileWriter(args, generated);
        fileWriter.writeAsFiletype(fileType);
    }

    private static VariousFileWriter getVariousFileWriter(String[] args, File generated) {
        StringFileManager fileManager = new StringFileManager(generated);
        List<String> sortedList;
        String dir = args[1];

        if (dir.equalsIgnoreCase("asc")) {
            sortedList = fileManager.getFileAsSortedListAsc();
        } else if (dir.equalsIgnoreCase("desc")) {
            sortedList = fileManager.getFileAsSortedListDesc();
        } else {
            throw new IllegalArgumentException("App.main args format: <xml/json/txt> <asc/desc>");
        }

        return new VariousFileWriter(sortedList);
    }
}