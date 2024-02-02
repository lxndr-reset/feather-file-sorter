package writer;

import utils.custom_collections.MapOrientedPriorityQueue;

import java.io.BufferedReader;
import java.io.File;

public class AutoWriter extends VariousFileWriter {
    private final FileType fileType;
    private final File to;

    public AutoWriter(FileType fileType, File to) {
        this.fileType = fileType;
        this.to = to;
    }

    public void autoWrite(MapOrientedPriorityQueue<BufferedReader, String> readers) {

        switch (fileType) {
            case TXT -> super.mergeAsTXT(readers, to);
            case XML -> super.mergeAsXML(readers, to);
            case JSON -> super.mergeAsJSON(readers, to);
        }
    }
}
