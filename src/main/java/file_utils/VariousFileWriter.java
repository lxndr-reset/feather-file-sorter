package file_utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import file_utils.file_writer_interfaces.JSONWrite;
import file_utils.file_writer_interfaces.TXTWrite;
import file_utils.file_writer_interfaces.XMLWrite;
import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.WrongMethodTypeException;
import java.util.List;

public class VariousFileWriter implements JSONWrite, XMLWrite, TXTWrite {
    private final List<String> list;
    private File file = null;

    public VariousFileWriter(List<String> list) {
        this.list = list;
    }

    public void writeAsFiletype(FileType fileType) {
        file = new File(STR."output.\{fileType.type}");
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
        throwIfNoFile();
        JSONArray jsonArray = new JSONArray(list);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            jsonArray.write(bw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void writeAsTXT() {
        throwIfNoFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String string : list) {
                writer.write(string);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeAsXML() {
        throwIfNoFile();

        try {
            new XmlMapper().writeValue(file, list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void throwIfNoFile() {
        if (file == null) {
            throw new WrongMethodTypeException("You can't call this method directly. Use 'writeAsJson()' instead");
        }
    }

    public enum FileType {
        TXT("txt"),

        XML("xml"),

        JSON("json");

        final String type;

        FileType(String type) {
            this.type = type;
        }
    }
}
