package ifba.inf011.p2.s26_1.domain.encoder;

public class NetworksAACEncoder extends AbstractEncoder {

    public NetworksAACEncoder() {
        super(EncoderProfile.NETWORKS_AAC);
    }

    @Override
    public String getEncoderName() {
        return "NetworksAACEncoder";
    }
}