package ifba.inf011.s24_2.interfaces;

import java.time.LocalDate;

// Interface Builder
public interface IPartidaBuilder {
    public IPartidaBuilder reset();
    public IPartidaBuilder setDescricao(String descricao);
    public IPartidaBuilder setInicio(LocalDate inicio);
    public IPartidaBuilder setFim(LocalDate fim);
    public IPartidaBuilder setPrioridade(Integer prioridade);
    public IPartidaBuilder setConcluido(Boolean concluido);
    public IPartidaBuilder setLocalizacao(String localizacao);
    public IPartidaBuilder setEquipeA(String equipe);
    public IPartidaBuilder setEquipeB(String equipe);
    public IPartidaBuilder setPontuacaoA(Integer pontuacao);
    public IPartidaBuilder setPontuacaoB(Integer pontuacao);
    public Evento build();
}
