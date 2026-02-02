package strategy;

public class Main {
    public static void main(String[] args) {
        Event sportsData = new Event(3, 0, 0);
        CoinAllocator sportsAllocator = new CoinAllocator(new SportsEventStrategy());
        System.out.println(sportsAllocator.allocateCoins(sportsData)); // 30

        // Fitness event
        Event fitnessData = new Event(0, 4500, 0);
        CoinAllocator fitnessAllocator = new CoinAllocator(new FitnessDataStrategy());
        System.out.println(fitnessAllocator.allocateCoins(fitnessData)); // 20
    }
}
