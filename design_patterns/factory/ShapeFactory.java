package factory;

public class ShapeFactory {
    public static Shape getShape(Shapes shape) {
        if (shape == null) {
            return null;
        }
        switch (shape) {
            case SQUARE:
                return new Square();
            case RECTANGLE:
                return new Rectangle();
            case CIRCLE:
                return new Circle();
            default:
                throw new IllegalArgumentException("Unknown shape type.");
        }
    }
}
