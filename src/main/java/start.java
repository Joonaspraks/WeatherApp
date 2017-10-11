import java.io.IOException;

public class start {
    public static void main(String[] args) throws IOException {
        WeatherController controller = new WeatherController();
        controller.getReports("forecast","helsinki");
        controller.getReportNow("weather","madrid");
        controller.getCityCoordinates("weather","tallinn");
    }
}
