import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  interface for parsing and analyzing weather data.
 */


public interface WeatherDataParser {
    /**
     * parses csv file containing weather data.
     *
     * @param filepath the path to the CSV file
     * @return a list of weatherdata records
     */


    static List<WeatherData> parseCSV(String filepath) throws IOException {
        var lines = Files.readAllLines(Path.of(filepath));

        // skips header and process each data line

        return lines.stream()
                .skip(1) // Skip header
                .map(WeatherDataParser::parseLine)
                .collect(Collectors.toList());
    }

    /**
     * parses line of CSV data into a weatherdata record.
     *
     * @param line a line from the CSV file
     * @return a weatherdata record
     */


    static WeatherData parseLine(String line) {
        var parts = line.split(",");
        return new WeatherData(
                parts[0],
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3])
        );
    }



    /**
     * calculates the average temp3 for a specific month.
     *
     * @param weatherData List of weather records
     * @param month the month to filter (1 = January, 12 = December)
     * @return The average temo for that month
     */


    static double averageTemperatureForMonth(List<WeatherData> weatherData, int month) {
        return weatherData.stream()
                .filter(data -> LocalDate.parse(data.date(), DateTimeFormatter.ISO_DATE).getMonthValue() == month)
                .mapToDouble(WeatherData::temperature)
                .average()
                .orElse(Double.NaN);
    }

    /**
     * finds days with temp above a given threshold.
     *
     * @param weatherData list of weather records
     * @param threshold the temp threshold
     * @return a list of dates where the temp was above threshold
     */
    static List<String> daysAboveThreshold(List<WeatherData> weatherData, double threshold) {
        return weatherData.stream()
                .filter(data -> data.temperature() > threshold)
                .map(WeatherData::date)
                .collect(Collectors.toList());
    }


    /**
     * counts the number of rainy days.
     *
     * @param weatherData list of weather records
     * @return the count of rainy days
     */


    static long countRainyDays(List<WeatherData> weatherData) {
        return weatherData.stream()
                .filter(data -> data.precipitation() > 0)
                .count();
    }

    /**
     * categorizes the temp into "Hot", "Warm", or "Cold" using a switch .
     *
     * @param temperature the temp to categorize
     * @return the weather category as a string
     */


    static String categorizeTemperature(double temperature) {
        int temp = (int) temperature;

        return switch ("") {
            default -> {
                if (temp > 30) yield "Hot";
                else if (temp >= 20) yield "Warm";
                else yield "Cold";
            }
        };
    }
}

