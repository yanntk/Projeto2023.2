package projetodemidia;


import java.io.*;

public class ImageBannerDAO {
    private static final String FILE_PATH = "image_banner_state.txt";

    public void saveState(int imageIndex) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println(imageIndex);
        } catch (IOException e) {
            System.err.println("Error saving image banner state: " + e.getMessage());
        }
    }

    public int loadState() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading image banner state: " + e.getMessage());
        }
        // Return default value if state cannot be loaded
        return 0;
    }
}
