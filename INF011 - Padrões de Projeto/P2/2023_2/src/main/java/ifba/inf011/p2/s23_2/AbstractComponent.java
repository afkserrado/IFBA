package ifba.inf011.p2.s23_2;

import java.util.HashMap;
import java.util.Map;

// Abstract Component do Decorator
public abstract class AbstractComponent implements Component {

    protected Map<String, String> headers = new HashMap<>();
    protected byte[] content;

    public AbstractComponent(String content) {
        this.content = content.getBytes();
    }

    @Override
    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    @Override
    public String readHeader(String key) {
        return this.headers.get(key);
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }
}
