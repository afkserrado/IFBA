package ifba.inf011.p3_2026_1.model.canva;

public class Canva4K implements Canva {
	
    private final CanvaProfile type = CanvaProfile.CINEMA_4K;

    @Override
    public int getwidth() {
        return this.type.getWidth();
    }

    @Override
    public int getHeight() {
        return this.type.getHeight(); 
    }

    @Override
    public String getResolution() {
        return this.type.getResolution(); 
    }

    @Override
    public String getColorspace() {
        return this.type.getColorSpace(); 
    }

    @Override
    public boolean isHDRSupported() {
        return this.type.isHDRSupported(); 
    }
}