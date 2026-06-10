package ifba.inf011.p2.s23_2;

// Base Decorator do Decorator
public abstract class BaseDecorator implements Component {
    
    protected Component inner;

    public BaseDecorator(Component inner) {
        this.inner = inner;
    }

    @Override
    public void addHeader(String key, String value) {
        this.inner.addHeader(key, value);
    }

    @Override
    public String readHeader(String key) {
        return this.inner.readHeader(key);
    }

    @Override
    public byte[] getContent() {
        return inner.getContent();
    }

    @Override
    public void setContent(byte[] content) {
        this.inner.setContent(content);
    }
}
