package ifba.inf011.p2.s24_2.model;

import java.time.LocalDateTime;

public class Aula {

    private String codDisciplina;
    private String descricaoDisciplina;
    private String professor;
    private Geolocalizacao sala;
    private LocalDateTime[] horario;

    public Aula(String codDisciplina, String descricaoDisciplina, String professor,
                Geolocalizacao sala, LocalDateTime inicio, LocalDateTime fim) {
        this.codDisciplina = codDisciplina;
        this.descricaoDisciplina = descricaoDisciplina;
        this.professor = professor;
        this.sala = sala;
        this.horario = new LocalDateTime[] { inicio, fim };
    }

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public String getDescricaoDisciplina() {
        return descricaoDisciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public Geolocalizacao getSala() {
        return sala;
    }

    public LocalDateTime[] getHorario() {
        return horario;
    }
}