package ifba.inf010.atv13;

import java.util.List;
import java.util.Scanner;

import ifba.inf010.atv13.model.ConjuntoFrequente;
import ifba.inf010.atv13.model.RegraAssociacao;
import ifba.inf010.atv13.model.Transacao;
import ifba.inf010.atv13.repository.TransacaoRepository;
import ifba.inf010.atv13.service.AprioriService;
import ifba.inf010.atv13.service.RegraAssociacaoService;

public class Main {
    public static void main(String[] args) {
        Main.apriori();
    }

    public static void apriori() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Suporte mínimo (%): ");
        double suporteMinimo = scanner.nextDouble() / 100;

        System.out.print("Confiança mínima (%): ");
        double confiancaMinima = scanner.nextDouble() / 100;

        TransacaoRepository repository = new TransacaoRepository();
        List<Transacao> transacoes = repository.buscarTodas();
        
        AprioriService aprioriService = new AprioriService();

        // Obtém as combinações mais frequentes
        List<ConjuntoFrequente> frequentes = aprioriService.executarApriori(transacoes, suporteMinimo);

        RegraAssociacaoService regraService = new RegraAssociacaoService();

        List<RegraAssociacao> regras = regraService.gerarRegras(frequentes, transacoes, confiancaMinima);
        
        // Por exemplo: [pao] => [manteiga] (sup: 40,00%, conf: 80,00%)
        // 40,00% de todas as transações possuem pão e manteiga simultaneamente.
        // Se uma pessoa compra pão, existe uma probabilidade de 80% de também comprar manteiga.
        regras.forEach(System.out::println);

        scanner.close();
    }
}
