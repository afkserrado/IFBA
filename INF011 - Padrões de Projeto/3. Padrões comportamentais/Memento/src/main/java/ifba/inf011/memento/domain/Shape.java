package ifba.inf011.memento.domain;

// Domain Object
public interface Shape {
    void moveBy(int dx, int dy);
    void setColor(String color);
    Shape copy();
    String describe();
}
