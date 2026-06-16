package ifba.inf011.p2.s26_1.domain.canva;

public interface Canva {

    public int getWidth();
    public int getHeight();
    public String getResolution();
    public String getColorSpace();
    public boolean isHDRSupported();
}