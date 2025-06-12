package blackjack;

import java.io.*;

public class FileManager {
    private final String fileName = "blackjack.txt";
 
    public FileManager() {
    	File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                //initialize it with profit 0
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("0");
                }
            } catch (IOException e) {
                System.err.println("Failed to create profit.txt: " + e.getMessage());
            }
        }
    }

    // Read the current highest profit from the file
    public int readHighestProfit() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {
            // File might not exist yet or be empty/corrupt
            return 0;
        }
    }

    // Write a new highest profit to the file
    public void writeHighestProfit(int newProfit) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.valueOf(newProfit));
        } catch (IOException e) {
            System.err.println("Error writing profit to file.");
        }
    }

    // Compare and update high profit
    public void checkAndUpdateProfit(int currentProfit) {
        int highestProfit = readHighestProfit();

        if (currentProfit > highestProfit) {
            writeHighestProfit(currentProfit);
        }
    }
}