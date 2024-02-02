import file_utils.FileAutoWriter;
import file_utils.StringFileManager;
import test_files_utils.TestFileGenerator;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class App {
    static final String WRONG_ARGS_FORMAT_MSG = "App.main args format: <xml/json/txt> <asc/desc>";

    public static void main(String[] args) {
        FileAutoWriter.FileType fileType;

        try {
            String lowerCase = args[0].toUpperCase(Locale.ROOT);
            fileType = FileAutoWriter.FileType.valueOf(lowerCase);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(WRONG_ARGS_FORMAT_MSG, e);
        }
        // generates 1 GB test file
        File generated = TestFileGenerator.generate("input");

        FileAutoWriter autoWriter = getAutoFileWriter(args, generated, fileType);
        autoWriter.autoWrite();
    }

    private static FileAutoWriter getAutoFileWriter(String[] args, File generated,
                                                    FileAutoWriter.FileType fileType) {
        StringFileManager fileManager = new StringFileManager(generated);
        List<String> sortedList;
        String dir = args[1];

        if (dir.equalsIgnoreCase("asc")) {
            sortedList = fileManager.getFileAsSortedListAsc();
        } else if (dir.equalsIgnoreCase("desc")) {
            sortedList = fileManager.getFileAsSortedListDesc();
        } else {
            throw new IllegalArgumentException(WRONG_ARGS_FORMAT_MSG);
        }

        return new FileAutoWriter(sortedList, fileType);
    }
}