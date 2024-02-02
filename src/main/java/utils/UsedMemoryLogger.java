package utils;

import static java.lang.Runtime.getRuntime;

public class UsedMemoryLogger {
    private UsedMemoryLogger() {
    }

    public static void log() {
        Runtime runtime = getRuntime();

        float diff = (float) runtime.totalMemory() - runtime.freeMemory();
        float v = diff / 1024 / 1024;

        System.out.println(STR."Used memory in GB: \{v / 1024} GB");
        System.out.println(STR."Used memory in MB: \{v} MB");
        System.out.println();
    }
}
