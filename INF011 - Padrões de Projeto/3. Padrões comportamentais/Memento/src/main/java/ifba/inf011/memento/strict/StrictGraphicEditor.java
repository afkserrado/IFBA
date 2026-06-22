package ifba.inf011.memento.strict;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ifba.inf011.memento.domain.Shape;

// Originator
public class StrictGraphicEditor {

    private List<Shape> shapes;

    public StrictGraphicEditor() {
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

    public RestorableMemento save(String name) {
        return new Snapshot(this, name, copyShapes(this.shapes));
    }

    private void restoreShapes(List<Shape> savedState) {
        this.shapes = copyShapes(savedState);
    }

    public void printState() {
        System.out.println("Estado atual do editor estrito:");
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
    private static class Snapshot implements RestorableMemento {

        private final StrictGraphicEditor editor;
        private final String name;
        private final LocalDateTime date;
        private final List<Shape> state;

        private Snapshot(StrictGraphicEditor editor, String name, List<Shape> state) {
            this.editor = editor;
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

        @Override
        public void restore() {
            editor.restoreShapes(state);
        }
    }
}
