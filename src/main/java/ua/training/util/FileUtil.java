package ua.training.util;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import sun.misc.IOUtils;
import ua.training.model.Wagon;
import ua.training.model.WagonComfortType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * First of all, you need to add apache-commons-csv dependency in your project.
 * A Comma-Separated Values (CSV) file is just a normal plain-text file,
 * store data in column by column, and split it by a separator (e.g normally it is a comma “,”).
 * <p>
 * created by Dibrova Serhii
 */
public class FileUtil {


    /**
     * from ArrayList to generating a CSV file
     *
     * @param FILE_PATH
     * @param wagon
     * @throws IOException
     */
    public void writeFile(String FILE_PATH, ArrayList<Wagon> wagon) throws IOException {

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "Type", "NumberOfPassenger", "NumberOfLuggage"));
        ) {
            for (Wagon wagons : wagon) {
                csvPrinter.printRecord(wagons.getId(), wagons.getType().name(),
                        wagons.getNumberOfPassengers(), wagons.getAmountOfLuggage());

            }

            csvPrinter.flush();
        }
    }

    /**
     * Reading a CSV file and add all elements into list
     *
     * @param FILE_PATH
     * @return
     * @throws IOException
     */
    public List<Wagon> readFile(String FILE_PATH) throws IOException {
        List<Wagon> wagon = new ArrayList<Wagon>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by the names assigned to each column
                int id = Integer.parseInt(csvRecord.get("ID"));
                WagonComfortType type = WagonComfortType.valueOf(csvRecord.get("Type"));
                int numberOfPassenger = Integer.parseInt(csvRecord.get("NumberOfPassenger"));
                int numberOfLuggage = Integer.parseInt(csvRecord.get("NumberOfLuggage"));

                wagon.add(new Wagon(id, type, numberOfPassenger, numberOfLuggage));
            }
        }
        return wagon;
    }

}
