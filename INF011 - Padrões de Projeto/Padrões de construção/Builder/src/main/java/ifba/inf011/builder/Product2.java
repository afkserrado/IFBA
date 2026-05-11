package ifba.inf011.builder;

public class Product2 {
    
    private boolean stepA;
    private boolean stepB;
    private boolean stepZ;
    private boolean featureB;

    public void setStepA(boolean stepA) {
        this.stepA = stepA;
    }

    public void setStepB(boolean stepB) {
        this.stepB = stepB;
    }

    public void setStepZ(boolean stepZ) {
        this.stepZ = stepZ;
    }

    public void setFeatureB(boolean feature) {
        this.featureB = feature;
    }

    @Override
    public String toString() {
        return "Product2{stepA=" + stepA + ", stepB=" + stepB + ", stepZ=" + stepZ + "featureB=" + featureB + "}";
    }
}
