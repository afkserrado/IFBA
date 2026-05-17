package ifba.inf011.s24_1.interfaces.products;

public abstract class Arma {
    
    private double adicionalRapido = 15;
    private Boolean habilitada = false;
    // demais atributos...

    // Construtor padrão explícito
    public Arma() {}

    // Métodos para a construção do objeto via builder
    public void setAdicionalRapido(double adicional) {
        this.adicionalRapido = adicional;
    }

    public void setHabilitada(Boolean hab) {
        this.habilitada = hab;
    }
    // demais métodos construtivos (setters)...

    public double getAdicionalRapido() {
        return this.adicionalRapido;
    }

    public Boolean habilitada() {
        return this.habilitada;
    }
    // demais getters da interface...
}
