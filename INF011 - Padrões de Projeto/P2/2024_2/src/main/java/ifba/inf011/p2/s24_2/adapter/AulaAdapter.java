package ifba.inf011.p2.s24_2.adapter;

import java.sql.Timestamp;
import java.time.LocalDate;

import ifba.inf011.p2.s24_2.model.Aula;
import ifba.inf011.p2.s24_2.model.Evento;
import ifba.inf011.p2.s24_2.model.Geolocalizacao;

// Adapter do padrão Adapter
public class AulaAdapter implements Evento {
	
	private final Aula aula;
    private final Integer prioridade = 5;
    private final Boolean concluido = false;
	
	public AulaAdapter(Aula aula) {
		this.aula = aula;
	}
	
	@Override
    public Boolean sobreposto(Evento evento) {
        if (evento == null) {
            return false;
        }
        return this.getInicio().before(evento.getTermino()) &&
               this.getTermino().after(evento.getInicio());
    }

    @Override
    public String getDescricao() {
        return "Aula de " + aula.getCodDisciplina() + " - " + aula.getDescricaoDisciplina();
    }

    @Override
    public Timestamp getInicio() {
        return Timestamp.valueOf(this.aula.getHorario()[0]);
    }

    @Override
    public Timestamp getTermino() {
    	return Timestamp.valueOf(this.aula.getHorario()[1]);
    }

    @Override
    public Integer getPrioridade() {
        return this.prioridade;
    }

    public Boolean getConcluido() {
        return this.concluido;
    }

    @Override
    public Geolocalizacao getLocalizacao() {
        return this.aula.getSala() != null ? this.aula.getSala() : Geolocalizacao.here();
    }

    @Override
    public Boolean iniciaEm(LocalDate dia) {
        if (dia == null) {
            return false;
        }
        return this.getInicio().toLocalDateTime().toLocalDate().equals(dia);
    }

    @Override
    public Boolean iniciaEntre(Timestamp inicio, Timestamp fim) {
        if (inicio == null || fim == null) {
            return false;
        }
        return !this.getInicio().before(inicio) && !this.getInicio().after(fim);
    }
}
