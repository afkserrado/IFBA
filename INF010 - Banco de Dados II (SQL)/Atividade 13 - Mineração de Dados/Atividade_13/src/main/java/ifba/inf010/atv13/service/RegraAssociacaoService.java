package ifba.inf010.atv13.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ifba.inf010.atv13.model.ConjuntoFrequente;
import ifba.inf010.atv13.model.RegraAssociacao;
import ifba.inf010.atv13.model.Transacao;

// Serviço responsável por gerar regras de associação a partir dos conjuntos frequentes gerados pelo Apriori
public class RegraAssociacaoService {

    private final AprioriService aprioriService = new AprioriService();

    // Gera regras de associação a partir dos conjuntos frequentes, filtrando pela confiança mínima
    public List<RegraAssociacao> gerarRegras(List<ConjuntoFrequente> conjuntosFrequentes, List<Transacao> transacoes, double confiancaMinima) {

        List<RegraAssociacao> regras = new ArrayList<>();

        for(ConjuntoFrequente conjunto : conjuntosFrequentes) {

            // Por exemplo: itens = {pão, leite, café}
            Set<String> itens = conjunto.getItens();

            // Regras só existem para conjuntos com pelo menos 2 itens
            if(itens.size() < 2) {
                continue;
            }

            // Gerar antecedentes possíveis
            // Por exemplo: subconjuntos = [ {}, {Pão}, {Leite}, {Café}, {Pão, Leite}, {Pão, Café}, {Leite, Café}, {Pão, Leite, Café} ]
            List<Set<String>> subconjuntos = gerarSubconjuntos(itens);

            // Por exemplo, {Pão}
            for(Set<String> antecedente : subconjuntos) {

                // Ignora conjunto vazio
                if(antecedente.isEmpty()) {
                    continue;
                }

                // Ignora o próprio conjunto
                if(antecedente.equals(itens)) {
                    continue;
                }

                // Por exemplo: consequente inicial = {Pão, Leite, Café}
                Set<String> consequente = new HashSet<>(itens);

                // Por exemplo, remove pão: consequente final {Leite, Café}
                consequente.removeAll(antecedente);

                double confianca = calcularConfianca(antecedente, itens, transacoes);

                if(confianca >= confiancaMinima) {
                    regras.add(
                            new RegraAssociacao(
                                    antecedente,
                                    consequente,
                                    conjunto.getSuporte(),
                                    confianca
                            )
                    );
                }
            }
        }

        return regras;
    }

    // Gera todos os subconjuntos possíveis de um conjunto de itens
    // Por exemplo: se lista = [Pão, Leite, Café], subconjuntos = [ {}, {Pão}, {Leite}, {Café}, {Pão, Leite}, {Pão, Café}, {Leite, Café}, {Pão, Leite, Café} ]
    private List<Set<String>> gerarSubconjuntos(Set<String> itens) {

        List<String> lista = new ArrayList<>(itens);
        List<Set<String>> subconjuntos = new ArrayList<>();

        gerarSubconjuntosRecursivo(lista, 0, new HashSet<>(), subconjuntos);

        return subconjuntos;
    }

    // Gera subconjuntos recursivamente utilizando as possibilidades de incluir ou não cada item
    private void gerarSubconjuntosRecursivo(List<String> itens, int indice, Set<String> atual, List<Set<String>> resultado) {

        // Chegou ao fim da lista
        if(indice == itens.size()) {
            resultado.add(new HashSet<>(atual));
            return;
        }

        // Caso 1: não adiciona o item atual
        gerarSubconjuntosRecursivo(itens, indice + 1, atual, resultado);

        // Caso 2: adiciona o item atual
        atual.add(itens.get(indice));

        gerarSubconjuntosRecursivo(itens, indice + 1, atual, resultado);

        // Remove para voltar ao estado anterior
        atual.remove(itens.get(indice));
    }

    // Calcula a confiança de uma regra de associação X => Y
    private double calcularConfianca(Set<String> antecedente, Set<String> conjuntoCompleto, List<Transacao> transacoes) {
        
        // Sup = Num Reg X e Y / Total Reg
        // Por exemplo: conjuntoCompleto = {Pão, Leite, Café}
        double suporteConjunto = aprioriService.calcularSuporte(conjuntoCompleto, transacoes);

        // Sup = Num Reg X / Total Reg
        // Por exemplo: antecedente = {Pão}
        double suporteAntecedente = aprioriService.calcularSuporte(antecedente,transacoes);

        // Conf = Num Reg X e Y / Num Reg X
        return suporteConjunto / suporteAntecedente;
    }
}