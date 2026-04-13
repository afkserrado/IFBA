package ifba.inf011;

import java.util.List;

class ReportExporter {
    public String exportCsv(List<String[]> rows) {
        StringBuilder record = new StringBuilder();
        int length = rows.size();
        
        for(int i = 0; i < length; i++) {
            record.append(String.join(",", rows.get(i)));

            if(i < length - 1) {
                record.append("\n");
            }
        }

        return record.toString();
    }
}

// Test
public class Main {
    public static void main(String[] args) {
        ReportExporter exporter = new ReportExporter();
        List<String[]> data = List.of(
            new String[]{"Name", "Age", "City"},
            new String[]{"Alice", "30", "New York"},
            new String[]{"Bob", "25", "London"}
        );
        System.out.println(exporter.exportCsv(data));
    }
}