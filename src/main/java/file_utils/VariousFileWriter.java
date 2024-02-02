package file_utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import file_utils.file_writer_interfaces.JSONWrite;
import file_utils.file_writer_interfaces.TXTWrite;
import file_utils.file_writer_interfaces.XMLWrite;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class VariousFileWriter implements JSONWrite, XMLWrite, TXTWrite {
    private final File file;
    private final List<String> list;

    public VariousFileWriter(List<String> list, File file) {
        this.list = list;
        this.file = file;
    }

    @Override
    public void writeAsJSON() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            new JSONObject()
                    .put("ArrayList", new JSONArray(list))
                    .write(bw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void writeAsTXT() {
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
        try {
            new XmlMapper().writeValue(file, list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
