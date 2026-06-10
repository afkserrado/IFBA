package ifba.inf011.p2.s24_1;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import ifba.inf011.p2.s24_1.model.Credencial;

// Proxy do Proxy
public class ArquivoProtegido extends AbstractComponent {
    
    private final Map<String, Integer> log;
    private final Arquivo arquivo;

    public ArquivoProtegido(String nome, Long tamanho) {
        this.arquivo = new Arquivo(nome, tamanho);
        this.log = new HashMap<>();
    }

    @Override
    public Long getTamanho() {
        return this.arquivo.getTamanho();
    }

    @Override
    public void ler(Credencial credencial) {  
        log.put(credencial.getId(), log.getOrDefault(credencial.getId(), 0) + 1);
        this.arquivo.ler(credencial);
    }

    @Override
    public String getNome() {
        return this.arquivo.getNome();
    }

    @Override
    public void setNome(String nome) {
        this.arquivo.setNome(nome);
    }

    @Override
    public LocalDateTime getDataCriacao() {
        return this.arquivo.getDataCriacao();
    }

    public void setTamanho(Long tamanho) {
        this.arquivo.setTamanho(tamanho);
    }

    @Override
    public void setPai(Component pai) {
        this.pai = pai;
        this.arquivo.setPai(pai);
    }

    public Map<String, Integer> getLog() {
        return this.log;
    }
}
