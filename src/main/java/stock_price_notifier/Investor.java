package stock_price_notifier;

public class Investor implements Observer {

    private final String name;
    private final double targetPrice;

    public Investor(String name, double targetPrice) {
        this.name = name;
        this.targetPrice = targetPrice;
    }

    @Override
    public void update(Stock stock) {
        if (stock.getPrice() >= targetPrice) {
            System.out.println(
                    name + " notified: " +
                            stock.getTicker() +
                            " reached " + stock.getPrice()
            );
        }
    }
}
