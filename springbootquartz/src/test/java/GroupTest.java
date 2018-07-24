import au.com.bytecode.opencsv.CSVReader;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GroupTest {


    public static final int CREATE_DATE_COLUMN = 6;

    @Test
    public void test() throws Exception {
        CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("/Users/jun/Documents/starbucks/ORDER.csv")));
        final List<String[]> lines = csvReader.readAll();

        String file = lines.parallelStream()
            .map(line -> {
                LocalDateTime createTime = Timestamp.valueOf(line[CREATE_DATE_COLUMN]).toLocalDateTime();
                line[CREATE_DATE_COLUMN] = createTime.minusMinutes(createTime.getMinute() % 5)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00"));
                return line;
            })
            .collect(Collectors.groupingBy(line -> line[CREATE_DATE_COLUMN]))
            .entrySet()
            .stream()
//            .sorted(Comparator.comparing(t -> Timestamp.valueOf(t.getKey())))

            .map(entry -> new Object[]{entry.getKey(), entry.getValue().size()})
            .sorted(Comparator.comparing(it -> -(int) it[1]))
            .map(entry -> String.format("%s=%d", entry[0], entry[1]))
            .collect(Collectors.joining("\n"));

        System.out.println(file);


    }

}
