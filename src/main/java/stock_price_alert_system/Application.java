package stock_price_alert_system;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Stock apple = new Stock("APPLE");

        Observer investor1 = new Investor("PALRAJ", 100);
        Observer investor2 = new Investor("ABHIN", 103);

        apple.addObserver(investor1);
        apple.addObserver(investor2);

        for (int i = 90; i <= 105; i++) {
            apple.setPrice(i);
            Thread.sleep(1000);
        }
    }
}
