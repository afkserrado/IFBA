package ifba.inf011.p2.s26_1.q7.flyweight;

import java.util.HashMap;
import java.util.Map;

// Flyweight Factory
public class AssetVisualFactory {

    private Map<String, AssetVisual> assets;

    public AssetVisualFactory() {
        this.assets = new HashMap<String, AssetVisual>();
    }

    public AssetVisual getAsset(String nome, String caminhoArquivo, String resolucao, byte[] dadosBinarios) {
        String chave = this.getChave(nome, caminhoArquivo, resolucao);

        if (!this.assets.containsKey(chave)) {
            this.assets.put(
                    chave,
                    new AssetVisualCompartilhado(nome, caminhoArquivo, resolucao, dadosBinarios)
            );
        }

        return this.assets.get(chave);
    }

    private String getChave(String nome, String caminhoArquivo, String resolucao) {
        return nome + "::" + caminhoArquivo + "::" + resolucao;
    }

    public int getTotalAssetsCompartilhados() {
        return this.assets.size();
    }
}