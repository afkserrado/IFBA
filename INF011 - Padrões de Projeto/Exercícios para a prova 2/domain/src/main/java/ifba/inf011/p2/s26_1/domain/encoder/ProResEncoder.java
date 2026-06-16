package ifba.inf011.p2.s26_1.domain.encoder;

public class ProResEncoder extends AbstractEncoder {

    public ProResEncoder() {
        super(EncoderProfile.CINEMA_PRORES);
    }

    @Override
    public String getEncoderName() {
        return "ProResEncoder";
    }
}