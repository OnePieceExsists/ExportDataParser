import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;

public class ExportDataParser {

    // ----------------- TESTER METHOD -----------------
    public void tester() {
        // Use the full dataset
        FileResource fr = new FileResource("exportdata.csv"); 
        CSVParser parser = fr.getCSVParser();

        // Test countryInfo
        System.out.println("Country info for Germany: " + countryInfo(parser, "Germany"));
        parser = fr.getCSVParser(); // Reset parser

        // Test listExportersTwoProducts
        System.out.println("\nCountries exporting both gold and diamonds:");
        listExportersTwoProducts(parser, "gold", "diamonds");
        parser = fr.getCSVParser(); // Reset parser

        // Test numberOfExporters
        int count = numberOfExporters(parser, "gold");
        System.out.println("\nNumber of countries exporting gold: " + count);
        parser = fr.getCSVParser(); // Reset parser

        // Test bigExporters
        System.out.println("\nCountries with export value over $999,999,999:");
        bigExporters(parser, "$999,999,999");
        parser = fr.getCSVParser(); // Reset parser

        // ----------------- NEW METHODS FOR QUESTIONS -----------------

        // Q3: Third country exporting fish and nuts
        parser = fr.getCSVParser();
        System.out.println("\nQ3: Third country exporting fish and nuts: " +
                thirdCountryWithProducts(parser, "fish", "nuts"));

        // Q4: Number of countries exporting sugar
        parser = fr.getCSVParser();
        System.out.println("Q4: Number of countries exporting sugar: " +
                numberOfExportersForItem(parser, "sugar"));

        // Q5: Exports of Nauru
        parser = fr.getCSVParser();
        System.out.println("Q5: Exports of Nauru: " +
                exportsOfCountry(parser, "Nauru"));

        // Q6: Second economy with exports >= 1 trillion
        parser = fr.getCSVParser();
        List<String> richCountries = countriesOverAmount(parser, "$1000000000000"); // 1 trillion
        if (richCountries.size() >= 2) {
            System.out.println("Q6: Second economy over 1 trillion: " + richCountries.get(1));
        } else {
            System.out.println("Q6: Less than 2 countries over 1 trillion");
        }
    }

    // ----------------- EXISTING METHODS -----------------

    // Return country info
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String countryName = record.get("Country").trim();
            if (countryName.equalsIgnoreCase(country.trim())) {
                String exports = record.get("Exports").trim();
                String value = record.get("Value (dollars)").trim();
                return countryName + ":" + exports + ":" + value;
            }
        }
        return "NOT FOUND";
    }

    // Print countries that export both items
    public void listExportersTwoProducts(CSVParser parser, String item1, String item2) {
        String search1 = item1.toLowerCase();
        String search2 = item2.toLowerCase();
        for (CSVRecord record : parser) {
            String exports = record.get("Exports").toLowerCase();
            if (exports.contains(search1) && exports.contains(search2)) {
                System.out.println(record.get("Country").trim());
            }
        }
    }

    // Count countries exporting an item
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        String search = exportItem.toLowerCase();
        for (CSVRecord record : parser) {
            String exports = record.get("Exports").toLowerCase();
            if (exports.contains(search)) {
                count++;
            }
        }
        return count;
    }

    // Print countries with export value bigger than amount
    public void bigExporters(CSVParser parser, String amount) {
        long threshold = parseDollarAmount(amount);
        for (CSVRecord record : parser) {
            long value = parseDollarAmount(record.get("Value (dollars)").trim());
            if (value > threshold) {
                System.out.println(record.get("Country").trim() + " " + record.get("Value (dollars)").trim());
            }
        }
    }

    // ----------------- NEW METHODS FOR QUESTIONS -----------------

    // Q3: Third country that exports both items
    public String thirdCountryWithProducts(CSVParser parser, String item1, String item2) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports").toLowerCase();
            if (exports.contains(item1.toLowerCase()) && exports.contains(item2.toLowerCase())) {
                count++;
                if (count == 3) { // third country
                    return record.get("Country").trim();
                }
            }
        }
        return "NOT FOUND";
    }

    // Q4: Count countries exporting a specific item
    public int numberOfExportersForItem(CSVParser parser, String item) {
        int count = 0;
        String search = item.toLowerCase();
        for (CSVRecord record : parser) {
            String exports = record.get("Exports").toLowerCase();
            if (exports.contains(search)) {
                count++;
            }
        }
        return count;
    }

    // Q5: Return exports of a specific country
    public String exportsOfCountry(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String countryName = record.get("Country").trim();
            if (countryName.equalsIgnoreCase(country.trim())) {
                return record.get("Exports").trim();
            }
        }
        return "NOT FOUND";
    }

    // Q6: Return list of countries with export value >= amount
    public List<String> countriesOverAmount(CSVParser parser, String amount) {
        long threshold = parseDollarAmount(amount);
        List<String> result = new ArrayList<>();
        for (CSVRecord record : parser) {
            long value = parseDollarAmount(record.get("Value (dollars)").trim());
            if (value >= threshold) {
                result.add(record.get("Country").trim());
            }
        }
        return result;
    }

    // ----------------- HELPER METHOD -----------------
    private long parseDollarAmount(String amount) {
        return Long.parseLong(amount.replace("$", "").replace(",", ""));
    }

    // ----------------- MAIN METHOD -----------------
    public static void main(String[] args) {
        ExportDataParser edp = new ExportDataParser();
        edp.tester();
    }
}
