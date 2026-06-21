package ifba.inf011.builder;

public class Product1 {
    
    private boolean stepA;
    private boolean stepB;
    private boolean stepZ;

    public void setStepA(boolean stepA) {
        this.stepA = stepA;
    }

    public void setStepB(boolean stepB) {
        this.stepB = stepB;
    }

    public void setStepZ(boolean stepZ) {
        this.stepZ = stepZ;
    }

    @Override
    public String toString() {
        return "Product1{stepA=" + stepA + ", stepB=" + stepB + ", stepZ=" + stepZ + "}";
    }
}
