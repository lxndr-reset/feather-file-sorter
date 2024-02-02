package reader;

import utils.custom_collections.MapOrientedPriorityQueue;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Chunks {

    private final int MAX_ITEMS_IN_MEMORY;
    private final File in;

    public Chunks(int MAX_ITEMS_IN_MEMORY, File in) {
        this.MAX_ITEMS_IN_MEMORY = MAX_ITEMS_IN_MEMORY;
        this.in = in;
    }

    private File sortAndSaveChunk(List<String> lines, SortDirection sortDirection) throws IOException {
        lines = lines.parallelStream().sorted(
                sortDirection == SortDirection.ASC ? Comparator.naturalOrder() : Comparator.reverseOrder()
        ).toList();

        File chunkFile = File.createTempFile("sorted_chunk_", ".txt", new File("D:/chunks"));
        chunkFile.deleteOnExit();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chunkFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        return chunkFile;
    }

    public MapOrientedPriorityQueue<BufferedReader, String> getOfChunks(SortDirection sortDirection) {
        List<File> sortedChunkFiles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String line;
            List<String> lines = new ArrayList<>(MAX_ITEMS_IN_MEMORY);

            while ((line = reader.readLine()) != null) {
                lines.add(line);
                if (lines.size() == MAX_ITEMS_IN_MEMORY) {
                    sortedChunkFiles.add(sortAndSaveChunk(lines, sortDirection));
                    lines.clear();
                }
            }

            if (!lines.isEmpty()) {
                sortedChunkFiles.add(sortAndSaveChunk(lines, sortDirection));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getAsQueue(sortedChunkFiles);
    }

    private MapOrientedPriorityQueue<BufferedReader, String> getAsQueue(List<File> chunks) {
        ConcurrentHashMap<BufferedReader, String> orienteer = new ConcurrentHashMap<>();
        Comparator<BufferedReader> pqComparator = Comparator.comparing(orienteer::get);

        MapOrientedPriorityQueue<BufferedReader, String> collect = chunks.parallelStream()
                .map(file -> {

                    BufferedReader reader;
                    try {
                        reader = new BufferedReader(new FileReader(file));
                        orienteer.put(reader, reader.readLine());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return reader;

                }).collect(Collectors.toCollection(() -> new MapOrientedPriorityQueue<>(pqComparator)));

        collect.setOrienteer(orienteer);

        return collect;
    }
}
