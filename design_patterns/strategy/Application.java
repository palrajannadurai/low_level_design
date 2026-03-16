package strategy;

import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Map<MathematicalOperations, Strategy> strategies = new HashMap<>();
        strategies.put(MathematicalOperations.ADD, new ConcreteStrategyAdd());
        strategies.put(MathematicalOperations.SUBTRACT, new ConcreteStrategySubtract());


        Context context = new Context();
        MathematicalOperations action = MathematicalOperations.SUBTRACT;
        context.setStrategy(strategies.get(action));
        int a = 10, b = 20;
        System.out.println(context.executeStrategy(a, b));
    }
}
