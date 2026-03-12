package strategy;

public class Application {
    public static void main(String[] args) {
        Context context = new Context();
        MathematicalOperations action = MathematicalOperations.ADD;
        if (action == MathematicalOperations.ADD) {
            context.setStrategy(new ConcreteStrategyAdd());
        } else if (action == MathematicalOperations.SUBTRACT) {
            context.setStrategy(new ConcreteStrategySubtract());
        }
        int a = 10, b = 20;
        System.out.println(context.executeStrategy(a, b));
    }
}
