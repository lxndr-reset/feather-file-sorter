package writer.writer_interfaces;

import utils.custom_collections.MapOrientedPriorityQueue;

import java.io.BufferedReader;
import java.io.File;

public interface TXTWrite {
    void mergeAsTXT(MapOrientedPriorityQueue<BufferedReader, String> pq, File to);
}
