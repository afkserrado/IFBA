package ifba.inf011.s24_2.interfaces;

import java.time.LocalDate;

// Abstract Product
public abstract class Calendario {
    
    private Short mes;
    private Integer ano;
    private Boolean notificacaoHabilitada;
    private String proprietario;

    public Calendario(Short mes, Integer ano, Boolean not, String proprietario) {
        this.mes = mes;
        this.ano = ano;
        this.notificacaoHabilitada = not;
        this.proprietario = proprietario;
    }

    public abstract void sinalizarEventos(LocalDate data);
    public abstract void download(String string);
}
