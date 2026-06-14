package ifba.inf011.p2.s21_2.adapter;

// Interface que representa o objeto Service/Adaptee
public interface WebinarIF {
    public String getId();
    public String getTitle();
    public double getPrice();
    public void play();
    public String getHoster();
    public long getMinutes();
    public boolean wasWatched();
}
