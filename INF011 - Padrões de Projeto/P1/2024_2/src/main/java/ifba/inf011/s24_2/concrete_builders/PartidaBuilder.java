package ifba.inf011.s24_2.concrete_builders;

import java.time.LocalDate;

import ifba.inf011.s24_2.interfaces.IPartidaBuilder;
import ifba.inf011.s24_2.products.Partida;

// Builder concreto
public class PartidaBuilder implements IPartidaBuilder {

    private Partida partida;

    public PartidaBuilder() {
        this.partida = new Partida();
    }

    public Partida build() {
        return this.partida;
    }

    @Override
    // Retorno covariante
    public PartidaBuilder reset() {
        this.partida = new Partida();
        return this;
    }

    @Override
    // Retorno covariante
    public PartidaBuilder setDescricao(String descricao) {
        partida.setDescricao(descricao);
        return this;
    }

    @Override
    // Retorno covariante
    public PartidaBuilder setInicio(LocalDate inicio) {
        return this;
    }

    @Override
    // Retorno covariante
    public PartidaBuilder setFim(LocalDate fim) {
        return this;
    }
    
    @Override
    // Retorno covariante
    public PartidaBuilder setPrioridade(Integer prioridade) {
        return this;
    }
    
    @Override
    // Retorno covariante
    public PartidaBuilder setConcluido(Boolean concluido) {
        return this;
    }
    
    @Override
    // Retorno covariante
    public PartidaBuilder setLocalizacao(String localizacao) {
        return this;
    }
    
    @Override
    // Retorno covariante
    public PartidaBuilder setEquipeA(String equipe) {
        partida.setEquipeA(equipe);
        return this;
    }
    
    @Override
    // Retorno covariante
    public PartidaBuilder setEquipeB(String equipe) {
        partida.setEquipeB(equipe);
        return this;
    }
    
    @Override
    // Retorno covariante
    public PartidaBuilder setPontuacaoA(Integer pontuacao) {
        return this;
    }
    
    @Override
    // Retorno covariante
    public PartidaBuilder setPontuacaoB(Integer pontuacao) {
        return this;
    }
}
