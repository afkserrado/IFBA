package ifba.inf011.s23_1.classes;

public class Nutriente {
    private String nome;
    private String unidade;
    private Double caloriaPorUnidade;

    public Nutriente(String nome, String unidade, Double caloriaPorUnidade) {
        this.nome = nome;
        this.unidade = unidade;
        this.caloriaPorUnidade = caloriaPorUnidade;
    }

    public Nutriente(String nome) {
        this(nome, null, 0.0);
    }

    public String getNome() {
		return nome;
	}

	public String getUnidade() {
		return unidade;
	}

	public Double getCaloriaPorUnidade() {
		return caloriaPorUnidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public void setCaloriaPorUnidade(Double caloriaPorUnidade) {
		this.caloriaPorUnidade = caloriaPorUnidade;
	}
}
