package ifba.inf011.builder;

public class Product3 {
    
    private boolean stepA;
    private boolean stepZ;
    private boolean featureB;

    public void setStepA(boolean stepA) {
        this.stepA = stepA;
    }

    public void setStepZ(boolean stepZ) {
        this.stepZ = stepZ;
    }

    public void setFeatureB(boolean feature) {
        this.featureB = feature;
    }

    @Override
    public String toString() {
        return "Product3{stepA=" + stepA + ", stepZ=" + stepZ + ", featureB=" + featureB + "}";
    }
}
