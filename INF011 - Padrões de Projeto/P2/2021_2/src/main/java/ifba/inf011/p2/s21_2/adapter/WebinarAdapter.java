package ifba.inf011.p2.s21_2.adapter;

import java.util.Collections;
import java.util.Set;

import ifba.inf011.p2.s21_2.composite.Component;
import ifba.inf011.p2.s21_2.model.Produto;

// Adapter
public class WebinarAdapter extends Produto implements Component {
    
    private WebinarIF service;

    public WebinarAdapter(WebinarIF service) {
        super(service.getId(), service.getTitle());
        this.service = service;
    }

    @Override
    public String getCodigo() {
        return this.service.getId();
    }

    @Override
    public String getNome() {
        return this.service.getTitle();
    }

    @Override
    public double getPreco() {
        return this.service.getPrice();
    }

    @Override
    public int getCHCumprida() {
        return this.service.wasWatched() ? getCHTotal() : 0;
    }

    @Override
    public int getCHTotal() {
        double chHoras = this.service.getMinutes() / 60.0;
        return (int) Math.ceil(chHoras);
    }

    @Override
    public Set<Component> getDisciplinas() {
        return Collections.singleton(this);
    }

    @Override
    public double getPctCHCumprida() { 
        return this.service.wasWatched() ? 1.0 : 0.0;
    }
}
