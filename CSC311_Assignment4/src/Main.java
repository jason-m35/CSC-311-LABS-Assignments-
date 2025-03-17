import java.io.IOException;
import java.util.List;


/**
 * main class to process and analyze data.
 */


public class Main {
    public static void main(String[] args) {
        String filePath = "weatherdata.csv";


        try {
            List<WeatherData> weatherData = WeatherDataParser.parseCSV(filePath);

            // print formatted  data
            System.out.println("""
                --------------------------
                weather data analysis
                --------------------------
                """);

            System.out.println("weather records:");

            weatherData.forEach(data ->
                    System.out.println(String.format("date: %s | temp: %.1f°C | humidity: %.1f%% | rain: %.1fmm",
                            data.date(), data.temperature(), data.humidity(), data.precipitation()))
            );

            System.out.println("""
                --------------------------
                weather statistics:
                --------------------------
                """);

            System.out.println(String.format("average temp in august: %.2f°C",
                    WeatherDataParser.averageTemperatureForMonth(weatherData, 8)));

            System.out.println("\ndays with temp above 30°C:");

            WeatherDataParser.daysAboveThreshold(weatherData, 30)
                    .forEach(date -> System.out.println(" - " + date));

            System.out.println(String.format("\ntotal rainy days: %d",
                    WeatherDataParser.countRainyDays(weatherData)));

            System.out.println("""
                --------------------------
                weather categories:
                --------------------------
                """);

            weatherData.forEach(data ->
                    System.out.println(String.format("date: %s | category: %s",
                            data.date(), WeatherDataParser.categorizeTemperature(data.temperature())))
            );

            System.out.println("\nweather analysis completed !");

        } catch (IOException e) {
            System.err.println(" Error reading file: " + e.getMessage());
        }
    }
}
