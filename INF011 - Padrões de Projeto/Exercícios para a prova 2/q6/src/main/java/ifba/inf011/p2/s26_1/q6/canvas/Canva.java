package ifba.inf011.p2.s26_1.q6.canvas;

// Implementor do Bridge
public interface Canva {

    public int getWidth();
    public int getHeight();
    public String getResolution();
    public String getAspectRatio();
    public String getColorSpace();
    public boolean isHDRSupported();
}