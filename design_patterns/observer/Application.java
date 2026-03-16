package observer;

public class Application {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        // define observers
        PhoneDisplay phoneDisplay = new PhoneDisplay();
        TVDisplay tvDisplay = new TVDisplay();

        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(tvDisplay);

        weatherStation.setData("SUNNY");
        weatherStation.setData("CLOUDY");
        weatherStation.setData("RAINY");

        weatherStation.removeObserver(tvDisplay);

        weatherStation.setData("WINDY");

    }
}
