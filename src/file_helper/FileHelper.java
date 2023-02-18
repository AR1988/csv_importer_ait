package file_helper;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrej Reutow
 * created on 18.02.2023
 */
public class FileHelper {
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

    public static void writeToFile(List<String> strings, String path) {
        try {
            String filePrefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd_HH_mm_ss")) + "_";
            File file = new File(filePrefix + path + ".report");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
            for (String string : strings) {
                writer.write(string);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
