
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FileOperations {

    /**
     * Reads the content of a file and returns it as a list of strings.
     * 
     * @param filePath The path of the file to read.
     * @return A list of strings, where each string is a line from the file.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public static List<String> readFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    /**
     * Writes a list of strings to a file.
     * 
     * @param filePath The path of the file to write to.
     * @param lines    A list of strings to write to the file.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public static void writeFile(String filePath, List<String> lines) throws IOException {
        Files.write(Paths.get(filePath), lines);
    }

    /**
     * Modifies the content of a file by replacing occurrences of oldString with
     * newString.
     * 
     * @param filePath  The path of the file to modify.
     * @param oldString The string to be replaced.
     * @param newString The string to replace oldString with.
     * @throws IOException If an I/O error occurs reading from or writing to the
     *                     file.
     */
    public static void modifyFile(String filePath, String oldString, String newString) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<String> modifiedLines = lines.stream()
                .map(line -> line.replace(oldString, newString))
                .collect(Collectors.toList());
        Files.write(Paths.get(filePath), modifiedLines);
    }

    public static void main(String[] args) {
        String filePath = "example.txt";
        List<String> lines = Arrays.asList("Hello, World!", "Welcome to Java file operations.");

        try {
            // Write data to file
            writeFile(filePath, lines);

            // Read data from file
            List<String> fileContent = readFile(filePath);
            System.out.println("File content before modification:");
            for (String line : fileContent) {
                System.out.println(line);
            }

            // Modify data in file
            modifyFile(filePath, "World", "Java");

            // Read modified data from file
            fileContent = readFile(filePath);
            System.out.println("\nFile content after modification:");
            for (String line : fileContent) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}