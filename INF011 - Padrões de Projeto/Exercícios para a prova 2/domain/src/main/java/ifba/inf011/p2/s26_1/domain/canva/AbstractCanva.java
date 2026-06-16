package ifba.inf011.p2.s26_1.domain.canva;

public abstract class AbstractCanva implements Canva {

    private CanvaProfile profile;

    public AbstractCanva(CanvaProfile profile) {
        this.profile = profile;
    }

    @Override
    public int getWidth() {
        return this.profile.getWidth();
    }

    @Override
    public int getHeight() {
        return this.profile.getHeight();
    }

    @Override
    public String getResolution() {
        return this.profile.getResolution();
    }

    @Override
    public String getColorSpace() {
        return this.profile.getColorSpace();
    }

    @Override
    public boolean isHDRSupported() {
        return this.profile.isHDRSupported();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + " ["
                + this.getResolution()
                + ", "
                + this.getWidth()
                + "x"
                + this.getHeight()
                + ", "
                + this.getColorSpace()
                + ", HDR="
                + this.isHDRSupported()
                + "]";
    }
}