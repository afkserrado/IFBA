package ifba.inf011.p2.s26_1.domain.encoder;

public class StandardH265Encoder extends AbstractEncoder {

    public StandardH265Encoder() {
        super(EncoderProfile.STANDARD_H265);
    }

    @Override
    public String getEncoderName() {
        return "StandardH265Encoder";
    }
}