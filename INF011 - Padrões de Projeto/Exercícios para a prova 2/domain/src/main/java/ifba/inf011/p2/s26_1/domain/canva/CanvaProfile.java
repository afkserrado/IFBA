package ifba.inf011.p2.s26_1.domain.canva;

public enum CanvaProfile {

    CINEMA_4K(3840, 2160, "4K UHD", "DCI-P3", true),
    NETWORKS_VERTICAL(1080, 1920, "1080p Vertical", "sRGB", false),
    FULL_HD(1920, 1080, "1080p Standard", "Rec. 709", false);

    private final int width;
    private final int height;
    private final String resolution;
    private final String colorSpace;
    private final boolean hdrSupported;

    CanvaProfile(
            int width,
            int height,
            String resolution,
            String colorSpace,
            boolean hdrSupported
    ) {
        this.width = width;
        this.height = height;
        this.resolution = resolution;
        this.colorSpace = colorSpace;
        this.hdrSupported = hdrSupported;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getResolution() {
        return this.resolution;
    }

    public String getColorSpace() {
        return this.colorSpace;
    }

    public boolean isHDRSupported() {
        return this.hdrSupported;
    }
}