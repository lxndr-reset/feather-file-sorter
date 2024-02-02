import reader.Chunks;
import reader.SortDirection;
import utils.custom_collections.MapOrientedPriorityQueue;
import writer.AutoWriter;
import writer.FileType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Run {
    public static void main(String[] args) { //args: <xml/txt/json> <asc/desc>
        File from = new File("input.txt");
//        TestFileGenerator.generate("input");
        FileType fileType;
        SortDirection sortDirection;

        try {
            fileType = FileType.valueOf(args[0].toUpperCase(Locale.ROOT));
            sortDirection = SortDirection.valueOf(args[1].toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new IllegalArgumentException("Run.java args format: <xml/txt/json> <asc/desc>", e);
        }

        final int MAX_ITEMS_IN_MEMORY = 50000;
        MapOrientedPriorityQueue<BufferedReader, String> readers = new Chunks(MAX_ITEMS_IN_MEMORY, from).getOfChunks(sortDirection);

        File to = new File(STR."output.\{fileType.getType()}");
        new AutoWriter(fileType, to).autoWrite(readers);
        System.out.println(isSorted(to));
    }

    private static boolean isSorted(File file) {
        if (!file.exists()) {
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String first = reader.readLine();
            if (first == null) {
                return true;
            }

            String second;
            while ((second = reader.readLine()) != null) {
                if (first.compareTo(second) >= 0) {
                    return false;
                }
                first = second;
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
