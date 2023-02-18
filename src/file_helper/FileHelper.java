package file_helper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrej Reutow
 * created on 18.02.2023
 */
public class FileHelper {

    public static final String DEFAULT_ORDER_PATH = "import";

    public static File[] detectFiles() {
        File file = new File(DEFAULT_ORDER_PATH);
        if (file.isDirectory()) {
            return file.listFiles((dir, name) -> name.endsWith(".csv"));
        }
        return new File[0];
    }

    public static List<String> readFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
            List<String> rows = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }
            br.close();
            return rows;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readFile(String pathToFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));
            List<String> rows = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }
            br.close();
            return rows;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File writeToFile(List<String> strings, String path) {
        try {
            File file = new File(path + ".report");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
            for (String string : strings) {
                writer.write(string);
                writer.newLine();
            }
            writer.close();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void moveFile(File file) {
        try {
            String fileNameFormatter = fileNameFormater(file.getName());
            Files.copy(file.toPath(), Path.of(fileNameFormatter));
            Files.delete(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String fileNameFormater(String name) {
        String filePrefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        String delimiter = "_";
        String orderPath = "import/imported/";
        return orderPath + filePrefix + delimiter + name;
    }
}
