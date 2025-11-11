# ExportDataParser

## Description
`ExportDataParser` is a Java project that parses CSV files containing world export data.  
It uses the **edu.duke** library and **Apache Commons CSV** to read and process CSV files.  
The program provides various methods to analyze export data, including:

- Finding information about a specific country.
- Listing countries exporting multiple products.
- Counting the number of exporters for a specific item.
- Listing countries with export values above a certain threshold.
- Answering specific data analysis questions (e.g., third country exporting fish and nuts, number of countries exporting sugar, etc.).

---

## Project Structure
ExportDataParser/

├─ ExportDataParser.java

├─ exportdata.csv (full dataset)

└─ .gitignore (ignores *.class and *.jar files)


---

## Requirements
- Java 8 or higher
- Libraries:
  - [edu.duke.jar](https://www.dukelearntoprogram.com/course2/)
  - [commons-csv-1.10.0.jar](https://commons.apache.org/proper/commons-csv/)

---

## How to Compile and Run

1. Place `ExportDataParser.java` and `exportdata.csv` in the same folder.  
2. Make sure the JARs are in a `libs/` folder inside your project:  

libs/edu-duke.jar

libs/commons-csv-1.10.0.jar


3. Compile the program:
javac -cp ".;libs/edu-duke.jar;libs/commons-csv-1.10.0.jar" ExportDataParser.java

4. Run the program:
java -cp ".;libs/edu-duke.jar;libs/commons-csv-1.10.0.jar" ExportDataParser

Features / Methods

countryInfo(CSVParser parser, String country) – Returns info about a country.

listExportersTwoProducts(CSVParser parser, String item1, String item2) – Prints countries exporting both items.

numberOfExporters(CSVParser parser, String exportItem) – Counts countries exporting an item.

bigExporters(CSVParser parser, String amount) – Prints countries with exports larger than a threshold.

thirdCountryWithProducts(CSVParser parser, String item1, String item2) – Returns the third country exporting both items.

numberOfExportersForItem(CSVParser parser, String item) – Returns number of countries exporting a specific item.

exportsOfCountry(CSVParser parser, String country) – Returns the exports of a specific country.

countriesOverAmount(CSVParser parser, String amount) – Returns a list of countries exporting above a threshold.

**License:

This project is for educational purposes and based on the Java Programming: Solving Problems with Software course.
