package strategy;

public class FitnessDataStrategy implements CoinAllocationStrategy {

    @Override
    public int calculateCoins(Event data) {
        return (data.getSteps() / 1000) * 5;
    }
}
