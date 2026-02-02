package strategy;

public class CoinAllocator {
    private final CoinAllocationStrategy strategy;

    public CoinAllocator(CoinAllocationStrategy strategy) {
        this.strategy = strategy;
    }

    public int allocateCoins(Event event) {
        return strategy.calculateCoins(event);
    }
}
