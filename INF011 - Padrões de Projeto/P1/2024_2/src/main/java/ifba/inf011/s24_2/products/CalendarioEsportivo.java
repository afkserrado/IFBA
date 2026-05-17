package ifba.inf011.s24_2.products;

import java.time.LocalDate;

import ifba.inf011.s24_2.interfaces.Calendario;

// Product
public class CalendarioEsportivo extends Calendario {
    
    public CalendarioEsportivo(Short mes, Integer ano, Boolean not, String proprietario) {
        super(mes, ano, not, proprietario);
    }

    @Override
    public void sinalizarEventos(LocalDate data) {
        System.out.println("Sinalizando evento pessoal...");
    }

    @Override
    public void download(String string) {
        System.out.println("Fazendo download do calendário pessoal...");
    }
}
