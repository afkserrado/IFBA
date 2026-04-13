# Exercise 1: Shape Factory

**Implement Shape Factory Class**

**Problem:** Build a shape drawing system using the Factory Method pattern. Each shape has an `area()` method and a `describe()` method that prints the shape's name and its area.

**Requirements:**
- Product interface: `Shape` with `area()` and `describe()` methods.
- Three concrete shapes: `Circle` (radius=5), `Rectangle` (width=4, height=6), `Triangle` (base=3, height=8).
- Each creator constructs its shape with the dimensions above.