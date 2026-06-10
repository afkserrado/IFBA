package ifba.inf011.p2.s24_1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Composite do Composite
public class Pasta extends AbstractComponent implements Cacheavel {
    
    private String nome;
    private final LocalDateTime dataCriacao;
    private Long tamanho;
    private final List<Component> filhos;

    public Pasta(String nome) {
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
        this.filhos = new ArrayList<>();
    }

    @Override
    public Long getTamanho() {
        
        Long tam = Long.valueOf(0);

        if(this.tamanho != null) {
            return this.tamanho;
        }

        for(Component filho : this.filhos) {
            tam += filho.getTamanho();
        }

        this.tamanho = tam;
        return this.tamanho;
    }

    @Override
    public void adicionar(Component filho) {

        filhos.add(filho);

        if(filho instanceof AbstractComponent component) {
            component.setPai(this);
        }

        this.invalidarCache();
    }

    @Override 
    public void remover(Component filho) {

        filhos.remove(filho);

        if(filho instanceof AbstractComponent component) {
            component.setPai(null);
        }

        this.invalidarCache();
    }

    @Override
    public void invalidarCache() {

        // Apaga o cache
        this.tamanho = null;

        // Propaga para as pastas superiores
        if(this.getPai() instanceof Cacheavel cacheavel) {
            cacheavel.invalidarCache();
        }
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }
}
