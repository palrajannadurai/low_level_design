package strategy;

public class SportsEventStrategy implements CoinAllocationStrategy{
    @Override
    public int calculateCoins(Event data) {
        return data.getMatchesPlayed() * 10;
    }
}
