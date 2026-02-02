package br.edu.ifba.inf008.interfaces;

public interface ITableColumns {
    
    // Texto que aparece no header da coluna (ex.: "Marca")
    String getHeaderText();

    // Nome da propriedade/getter no DTO (ex.: "make" -> getMake())
    String getPropertyName();
}
