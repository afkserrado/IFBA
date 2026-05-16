package ifba.inf011.factory_method.classic.interfaces;

// Criador
public abstract class Creator {
    // Factory Method
    public abstract IProduct create();

    public void someOperation() {
        IProduct product = create();
        product.doSomething();
    }
}
