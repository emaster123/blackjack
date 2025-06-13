package blackjack;

import java.io.*;

/**
 * Handles reading from and writing to a local file that stores the highest profit
 * achieved in a Blackjack game session.
 * <p>
 * This class creates a file named {@code blackjack.txt} if it does not already exist,
 * and provides methods to read the current high score, write a new high score,
 * and update it if a higher profit is achieved.
 * 
 * @author Eli L
 */
public class FileManager {

    /** The name of the file used to persist the highest profit value. */
    private final String fileName = "blackjack.txt";

    /**
     * Constructs a {@code FileManager} instance and ensures the profit file exists.
     * If the file doesn't exist, it is created and initialized with a default profit of 0.
     */
    public FileManager() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                // Initialize it with profit 0
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("0");
                }
            } catch (IOException e) {
                System.err.println("Failed to create blackjack.txt: " + e.getMessage());
            }
        }
    }

    /**
     * Reads the highest profit currently stored in the file.
     *
     * @return the highest profit as an integer. Returns 0 if the file is empty, missing,
     *         or contains invalid data.
     */
    public int readHighestProfit() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {
            // File might not exist yet or be empty/corrupt
            return 0;
        }
    }

    /**
     * Writes a new highest profit value to the file, overwriting any existing value.
     *
     * @param newProfit the new profit value to store.
     */
    public void writeHighestProfit(int newProfit) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.valueOf(newProfit));
        } catch (IOException e) {
            System.err.println("Error writing profit to file.");
        }
    }

    /**
     * Compares the current profit to the stored highest profit,
     * and updates the file if the current profit is higher.
     *
     * @param currentProfit the profit value from the current game session.
     */
    public void checkAndUpdateProfit(int currentProfit) {
        int highestProfit = readHighestProfit();

        if (currentProfit > highestProfit) {
            writeHighestProfit(currentProfit);
        }
    }
}
