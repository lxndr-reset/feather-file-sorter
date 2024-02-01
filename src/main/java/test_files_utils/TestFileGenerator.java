package test_files_utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestFileGenerator {
    public static File generate(String filename) {
        final long targetSize = 1024
//                * 1024
//                * 1024
                / 80;
        if (filename.contains(".")) {
            throw new IllegalArgumentException("filename shouldn't contain extension");
        }

        File file = new File(STR."\{filename}.txt");
        BufferedWriter writer;
        try {
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            } else {
                if (file.length() >= targetSize) {
                    return file;
                }
            }
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<CompletableFuture<String>> futures = new ArrayList<>(((int) targetSize));

        for (long l = 0; l < targetSize; l++) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(RandomStringGenerator::run);
            futures.addLast(future);
        }

        try (writer) {
            for (CompletableFuture<String> future : futures) {
                writer.write(future.get());
            }
            futures.clear();
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }

    public static class RandomStringGenerator {
        private static final char[] CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                .toCharArray();
        private static final int CHARACTERS_LENGTH = CHARACTERS.length;
        private static final Random random = new Random();

        public static String run() {
            StringBuilder sb = new StringBuilder(CHARACTERS_LENGTH);

            for (int i = 0; i < CHARACTERS_LENGTH; i++) {
                int randomIndex = random.nextInt(CHARACTERS_LENGTH);
                sb.append(CHARACTERS[randomIndex]);
            }
            sb.append("\n");

            return sb.toString();
        }
    }
}
