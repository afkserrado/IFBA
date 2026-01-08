package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.ITableColumns;

// Define o nome de cada coluna da tabela de veículos, além do nome do atributo correspondente no DTO
public enum VehicleColumns implements ITableColumns {

    MAKE("Marca", "make"),
    MODEL("Modelo", "model"),
    YEAR("Ano", "year"),
    FUEL_TYPE("Tipo de combustível", "fuelType"),
    TRANSMISSION("Tipo de câmbio", "transmission"),
    MILEAGE("Quilometragem", "mileage");

    private final String headerText;
    private final String propertyName;

    // Invocado automaticamente quando a classe é carregada
    VehicleColumns(String headerText, String propertyName) {
        this.headerText = headerText;
        this.propertyName = propertyName;
    }

    @Override
    public String getHeaderText() {
        return headerText;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }
}
