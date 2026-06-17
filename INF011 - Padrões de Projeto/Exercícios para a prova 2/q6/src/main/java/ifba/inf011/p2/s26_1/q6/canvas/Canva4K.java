package ifba.inf011.p2.s26_1.q6.canvas;

// Concrete Implementor do Bridge
public class Canva4K implements Canva {

    @Override
    public int getWidth() {
        return 3840;
    }

    @Override
    public int getHeight() {
        return 2160;
    }

    @Override
    public String getResolution() {
        return "4K UHD";
    }

    @Override
    public String getAspectRatio() {
        return "16:9";
    }

    @Override
    public String getColorSpace() {
        return "DCI-P3";
    }

    @Override
    public boolean isHDRSupported() {
        return true;
    }
}