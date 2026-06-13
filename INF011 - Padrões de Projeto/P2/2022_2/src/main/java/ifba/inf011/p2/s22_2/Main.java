package ifba.inf011.p2.s22_2;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p2.s22_2.abstraction.Relatorio;
import ifba.inf011.p2.s22_2.abstraction.RelatorioResumido;
import ifba.inf011.p2.s22_2.implementation.Formato;
import ifba.inf011.p2.s22_2.implementation.FormatoHTML;
import ifba.inf011.p2.s22_2.implementation.FormatoLatex;

public class Main {
    public static void main(String[] args) {
        
        Obra livro = new Livro(
            "Rinha de galos",
            2021,
            9,
            "Moinhos",
            "Português",
            "María Fernanda Ampuero",
            144
        );

        Obra album = new Album(
            "Acabou Chorare",
            1972,
            10,
            "Som Livre",
            "Estúdios Som Livre",
            "Novos Baianos",
            40
        );

        List<Obra> obras = new ArrayList<>();
        obras.addAll(List.of(livro, album));

        Formato html = new FormatoHTML();
        Formato latex = new FormatoLatex();
        
        Relatorio resumido = new RelatorioResumido(html);
        String relatorioHTML = resumido.gerarRelatorio(obras);
        System.out.println(relatorioHTML);

        System.out.println();

        String relatorioHTML2 = resumido.gerarRelatorio(obras);
        System.out.println(relatorioHTML2);

        System.out.println();

        resumido.setFormato(latex);
        String relatorioLatex = resumido.gerarRelatorio(obras);
        System.out.println(relatorioLatex);
    }
}
