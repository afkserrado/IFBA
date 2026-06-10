package ifba.inf011.p2.s24_1;

import java.util.HashMap;
import java.util.Map;

import ifba.inf011.p2.s24_1.model.Credencial;

// Proxy do Proxy
public class ArquivoProtegido extends AbstractComponent {
    
    private final Map<Credencial, Integer> credenciais;
    private final Arquivo arquivo;

    public ArquivoProtegido(String nome, Long tamanho) {
        super(nome);
        this.tamanho = tamanho;
        this.arquivo = new Arquivo(nome, tamanho);
        this.credenciais = new HashMap<>();
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
        this.arquivo.setNome(nome);
    }

    @Override
    public Long getTamanho() {
        return this.arquivo.getTamanho();
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
        this.arquivo.setTamanho(tamanho);
    }

    @Override
    public void ler(Credencial credencial) {
        
        if(!credenciais.containsKey(credencial)) {
            System.out.println("O usuário não possui permissão para acessar o arquivo.");
            return;
        }

        credenciais.put(credencial, credenciais.get(credencial) + 1);
        this.arquivo.ler(credencial);
    }
}
