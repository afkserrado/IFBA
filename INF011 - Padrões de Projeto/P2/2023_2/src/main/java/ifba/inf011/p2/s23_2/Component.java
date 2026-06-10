package ifba.inf011.p2.s23_2;

// Component do Decorator
public interface Component {
    public void addHeader(String str1, String str2);
    public String readHeader(String header);
    public byte[] getContent();
    public void setContent(byte[] content);
}
