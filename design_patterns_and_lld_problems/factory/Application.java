package factory;

public class Application {
    public static void main(String[] args) {
        Shape square = ShapeFactory.getShape(Shapes.SQUARE);
        square.draw();

        Shape rectangle = ShapeFactory.getShape(Shapes.RECTANGLE);
        rectangle.draw();

        Shape circle = ShapeFactory.getShape(Shapes.CIRCLE);
        circle.draw();
    }
}
