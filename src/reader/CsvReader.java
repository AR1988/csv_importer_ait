package reader;

import csv.MockCsv;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public class CsvReader {

    public static ArrayList<String> readFile() {
        return new ArrayList<>(MockCsv.CSV_STR_1);
    }

    public static List<List<String>> readFiles() {
        List<List<String>> files = new ArrayList<>();
        files.add(MockCsv.CSV_STR_1);
        files.add(MockCsv.CSV_STR_2);
        return files;
    }
}
