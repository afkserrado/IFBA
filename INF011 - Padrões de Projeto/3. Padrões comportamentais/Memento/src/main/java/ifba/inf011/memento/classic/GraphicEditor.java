package ifba.inf011.memento.classic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ifba.inf011.memento.domain.Shape;

// Originator
public class GraphicEditor {

    private List<Shape> shapes;

    public GraphicEditor() {
        this.shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public void moveShape(int index, int dx, int dy) {
        this.shapes.get(index).moveBy(dx, dy);
    }

    public void changeColor(int index, String color) {
        this.shapes.get(index).setColor(color);
    }

    public EditorMemento save(String name) {
        return new Snapshot(name, copyShapes(this.shapes));
    }

    public void restore(EditorMemento memento) {
        if (!(memento instanceof Snapshot)) {
            throw new IllegalArgumentException("Memento incompatível com GraphicEditor.");
        }

        Snapshot snapshot = (Snapshot) memento;
        this.shapes = copyShapes(snapshot.state);
    }

    public void printState() {
        System.out.println("Estado atual do editor:");
        for (int i = 0; i < shapes.size(); i++) {
            System.out.println("  [" + i + "] " + shapes.get(i).describe());
        }
    }

    private List<Shape> copyShapes(List<Shape> source) {
        List<Shape> copy = new ArrayList<>();

        for (Shape shape : source) {
            copy.add(shape.copy());
        }

        return copy;
    }

    // Memento - Concrete Memento
    private static class Snapshot implements EditorMemento {

        private final String name;
        private final LocalDateTime date;
        private final List<Shape> state;

        private Snapshot(String name, List<Shape> state) {
            this.name = name;
            this.date = LocalDateTime.now();
            this.state = state;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public LocalDateTime getDate() {
            return date;
        }
    }
}
