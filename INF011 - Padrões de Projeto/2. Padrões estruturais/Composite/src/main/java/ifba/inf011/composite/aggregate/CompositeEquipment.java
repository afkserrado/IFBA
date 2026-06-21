package ifba.inf011.composite.aggregate;

import java.util.ArrayList;
import java.util.List;

// Composite
public abstract class CompositeEquipment extends Equipment {

    private final List<Equipment> children = new ArrayList<>();

    protected CompositeEquipment(String name) {
        super(name);
    }

    public void add(Equipment equipment) {
        children.add(equipment);
    }

    protected List<Equipment> getChildren() {
        return children;
    }

    @Override
    public int power() {

        int total = 0;

        for (Equipment equipment : children) {
            total += equipment.power();
        }

        return total;
    }

    @Override
    public int netPrice() {

        int total = 0;

        for (Equipment equipment : children) {
            total += equipment.netPrice();
        }

        return total;
    }
}