package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) continue;

                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if ("supply".equals(operation)) {
                    supplySum += amount;
                } else if ("buy".equals(operation)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from " + fromFileName, e);
        }

        int result = supplySum - buySum;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplySum);
            writer.newLine();
            writer.write("buy," + buySum);
            writer.newLine();
            writer.write("result," + result);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file" + e);
        }
    }

}
