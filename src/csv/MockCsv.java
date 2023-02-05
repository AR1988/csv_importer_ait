package csv;

import java.util.List;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public abstract class MockCsv {
    public static List<String> CSV_STR_1 =
            List.of(
                    "Max;Mustermann;HaupStr.17;10123;;50010094569875;652.12;C",
                    "Vasja;Pipkin;MainStr.1117;789456;;50010094569875;652.12;D",
                    "Maria;Schmidt;MainStr.1117;789456;+4917;50010094569875;652.12;D"
            );

    public static List<String> CSV_STR_2 =
            List.of(
                    "Andre;Reutow;BahnhofStr.17;10369;+49176123456;asdasdasd;652.12;123123123"
                    );
}
