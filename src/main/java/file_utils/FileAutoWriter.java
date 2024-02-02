package file_utils;

import java.io.File;
import java.util.List;

public class FileAutoWriter extends VariousFileWriter {
    private final FileType fileType;

    public FileAutoWriter(List<String> sortedList, FileType fileType) {
        super(sortedList, new File(STR."output.\{fileType.type}"));
        this.fileType = fileType;
    }

    public void autoWrite() {
        switch (fileType) {
            case TXT:
                writeAsTXT();
                break;
            case JSON:
                writeAsJSON();
                break;
            case XML:
                writeAsXML();
                break;
        }
    }

    @Override
    public void writeAsJSON() {
        super.writeAsJSON();
    }


    @Override
    public void writeAsTXT() {
        super.writeAsTXT();
    }

    @Override
    public void writeAsXML() {
        super.writeAsXML();
    }


    public enum FileType {
        TXT("txt"),

        XML("xml"),

        JSON("json");

        final String type;

        FileType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return STR."FileType{type='\{type}\{'\''}\{'}'}";
        }
    }
}
