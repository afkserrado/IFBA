package ifba.inf010.atv13.model;

import java.util.Set;

// Representa os registros do banco de dados
public class Transacao {
    
    private Integer tid;
    private Set<String> itens;

    public Transacao(Integer tid, Set<String> itens) {
        this.tid = tid;
        this.itens = itens;
    }

    public Integer getTid() {
        return tid;
    }

    public Set<String> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return tid + " -> " + itens;
    }
}
