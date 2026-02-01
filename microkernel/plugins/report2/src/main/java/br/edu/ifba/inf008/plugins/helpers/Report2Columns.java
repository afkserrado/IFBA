package br.edu.ifba.inf008.plugins.helpers;

import br.edu.ifba.inf008.interfaces.ITableColumns;

public enum Report2Columns implements ITableColumns {
    
    RENTAL_ID("ID", "rentalId"),
    CUSTOMER_NAME("Cliente", "customerName"),
    CUSTOMER_TYPE("Tipo cliente", "customerType"),
    VEHICLE("Veículo", "vehicle"),
    VEHICLE_TYPE("Tipo veículo", "vehicleType"),
    START_DATE("Início", "startDate"),
    TOTAL_AMOUNT("Total", "totalAmount"),
    RENTAL_STATUS("Status locação", "rentalStatus"),
    PAYMENT_STATUS("Pagamento", "paymentStatus");

    private final String headerText;
    private final String propertyName;

    Report2Columns(String headerText, String propertyName) {
        this.headerText = headerText;
        this.propertyName = propertyName;
    }

    @Override public String getHeaderText() { return headerText; }
    @Override public String getPropertyName() { return propertyName; }
}
