package ifba.inf010.atv13.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ifba.inf010.atv13.model.ConjuntoFrequente;
import ifba.inf010.atv13.model.Transacao;

// Serviço responsável pela implementação do algoritmo Apriori para encontrar conjuntos frequentes de itens
public class AprioriService {

    // Executa todas as etapas do algoritmo Apriori até que não existam novos conjuntos frequentes
    // Exemplo: L1 -> C2 -> L2 -> C3 -> L3 ... até que nenhum candidato atinja o suporte mínimo
    public List<ConjuntoFrequente> executarApriori(List<Transacao> transacoes, double suporteMinimo) {

        List<ConjuntoFrequente> todosFrequentes = new ArrayList<>();

        // Primeiro nível: conjuntos contendo apenas um item
        List<ConjuntoFrequente> nivelAtual = gerarL1(transacoes, suporteMinimo);

        todosFrequentes.addAll(nivelAtual);

        // Continua gerando novos níveis enquanto existirem conjuntos frequentes
        while(!nivelAtual.isEmpty()) {

            // Gera candidatos a partir do nível frequente atual
            Set<Set<String>> candidatos = gerarCandidatos(nivelAtual);

            // Filtra os candidatos pelo suporte mínimo
            nivelAtual = gerarProximoNivel(candidatos, transacoes, suporteMinimo);

            todosFrequentes.addAll(nivelAtual);
        }

        return todosFrequentes;
    }
 
    // Obtém todos os itens distintos presentes nas transações
    // Por exemplo: transações contendo {café, pão}, {pão, manteiga} retornam {café, pão, manteiga}
    public Set<String> obterItensDistintos(List<Transacao> transacoes) {

        Set<String> itens = new HashSet<>();

        for(Transacao t : transacoes) {
            itens.addAll(t.getItens());
        }

        return itens;
    }

    // Gera o primeiro nível do Apriori (L1), contendo conjuntos frequentes com apenas um item
    // Por exemplo: {café}, {pão}, {manteiga}
    public List<ConjuntoFrequente> gerarL1(List<Transacao> transacoes, double suporteMinimo) {

        Set<Set<String>> candidatos = new HashSet<>();
        Set<String> itensDistintos = obterItensDistintos(transacoes);

        for(String item : itensDistintos) {
            candidatos.add(Set.of(item));
        }

        return filtrarCandidatos(candidatos, transacoes, suporteMinimo);
    }

    // Gera um novo nível de conjuntos frequentes a partir dos candidatos gerados
    // Por exemplo: recebe candidatos de 2 itens e retorna L2; recebe candidatos de 3 itens e retorna L3
    public List<ConjuntoFrequente> gerarProximoNivel(Set<Set<String>> candidatos, List<Transacao> transacoes, double suporteMinimo) {
        return filtrarCandidatos(candidatos, transacoes, suporteMinimo);
    }

    // Gera candidatos combinando conjuntos frequentes do nível anterior
    // Por exemplo: {café} + {pão} gera o candidato {café, pão}
    public Set<Set<String>> gerarCandidatos(List<ConjuntoFrequente> conjFrequentes) {

        Set<Set<String>> candidatos = new HashSet<>();

        for(int i = 0; i < conjFrequentes.size(); i++) {
            for(int j = i + 1; j < conjFrequentes.size(); j++) {

                Set<String> candidato = new HashSet<>();

                candidato.addAll(conjFrequentes.get(i).getItens());
                candidato.addAll(conjFrequentes.get(j).getItens());

                candidatos.add(candidato);
            }
        }

        return candidatos;
    }

    // Calcula o suporte de um conjunto de itens
    // Sup = Num Reg X e Y / Total Reg
    // Por exemplo: se {pão, manteiga} aparece em 4 de 10 transações, o suporte é 40%
    public double calcularSuporte(Set<String> itens, List<Transacao> transacoes) {

        int contador = 0;

        for(Transacao t : transacoes) {

            if(t.getItens().containsAll(itens)) {
                contador++;
            }
        }

        return (double) contador / transacoes.size();
    }

    // Remove candidatos que não atingem o suporte mínimo informado
    // Por exemplo: com suporte mínimo de 30%, um conjunto com suporte de 20% é descartado
    private List<ConjuntoFrequente> filtrarCandidatos(Set<Set<String>> candidatos, List<Transacao> transacoes, double suporteMinimo) {

        List<ConjuntoFrequente> frequentes = new ArrayList<>();

        for(Set<String> candidato : candidatos) {

            double suporte = calcularSuporte(candidato,transacoes);

            if(suporte >= suporteMinimo) {
                frequentes.add(
                        new ConjuntoFrequente(
                                candidato,
                                suporte
                        )
                );
            }
        }

        return frequentes;
    }
}