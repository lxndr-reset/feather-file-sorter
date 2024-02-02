package writer;

import utils.UsedMemoryLogger;
import utils.custom_collections.MapOrientedPriorityQueue;
import writer.writer_interfaces.JSONWrite;
import writer.writer_interfaces.TXTWrite;
import writer.writer_interfaces.XMLWrite;

import java.io.*;
import java.util.Map;

@SuppressWarnings("StringBufferReplaceableByString")
public abstract class VariousFileWriter implements JSONWrite, XMLWrite, TXTWrite {
    @Override
    public void mergeAsJSON(MapOrientedPriorityQueue<BufferedReader, String> pq, File to) {
        Map<BufferedReader, String> orienteer = pq.getOrienteer();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(to))) {
            writer.write("{\"list\": [");

            while (!pq.isEmpty()) {
                BufferedReader reader = pq.poll();
                String lowestStr = orienteer.get(reader);
                String next = reader.readLine();

                StringBuilder json = new StringBuilder("\"").append(lowestStr).append("\",");

                if (next != null) {
                    orienteer.put(reader, next);
                    pq.add(reader);
                } else {
                    orienteer.remove(reader);
                    json.deleteCharAt(json.length() - 1);
                    reader.close();
                }
                writer.write(json.toString());
            }

            writer.write("]}");

            UsedMemoryLogger.log();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void mergeAsTXT(MapOrientedPriorityQueue<BufferedReader, String> pq, File to) {
        Map<BufferedReader, String> orienteer = pq.getOrienteer();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(to))) {

            while (!pq.isEmpty()) {
                BufferedReader reader = pq.poll();
                String lowestStr = orienteer.get(reader);
                String next = reader.readLine();

                //noinspection StringBufferReplaceableByString
                writer.write(new StringBuilder(lowestStr).append('\n').toString());

                if (next != null) {
                    orienteer.replace(reader, lowestStr, next);
                    pq.add(reader);
                } else {
                    reader.close();
                }
            }

            UsedMemoryLogger.log();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void mergeAsXML(MapOrientedPriorityQueue<BufferedReader, String> pq, File to) {
        Map<BufferedReader, String> orienteer = pq.getOrienteer();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(to))) {
            writer.write("<list>");

            while (!pq.isEmpty()) {
                BufferedReader reader = pq.poll();
                String lowestStr = orienteer.get(reader);
                String next = reader.readLine();

                StringBuilder xml = new StringBuilder("<item>").append(lowestStr).append("</item>");

                if (next != null) {
                    orienteer.replace(reader, lowestStr, next);
                    pq.add(reader);
                } else {
                    reader.close();
                }
                writer.write(xml.toString());
            }

            writer.write("</list>");

            UsedMemoryLogger.log();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
