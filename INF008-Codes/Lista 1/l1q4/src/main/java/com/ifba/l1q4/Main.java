package com.ifba.l1q4;

public class Main {
    public static void main(String[] args) {
        // Criando um novo item de inventário com todos os parâmetros
        InventoryItem item1 = new InventoryItem("A123", "Product X", 100.0, 50, 10);
        
        // Exibindo as informações iniciais do item
        System.out.println("Item Code: " + item1.getItemCode());
        System.out.println("Description: " + item1.getDescription());
        System.out.println("Unit Price: " + item1.getUnitPrice());
        System.out.println("Quantity in Stock: " + item1.getQuantityInStock());
        System.out.println("Minimum Stock Level: " + item1.getMinimumStockLevel());

        // Aplicando um desconto de 20%
        System.out.println("\nApplying 50% discount...");
        item1.applyDiscount(50);
        System.out.println("New Unit Price after discount: " + item1.getUnitPrice());

        // Atualizando o estoque
        System.out.println("\nUpdating stock...");
        item1.updateStock(-5);  // Subtrai 5 unidades
        System.out.println("Quantity in Stock after removing 5 items: " + item1.getQuantityInStock());
        
        item1.updateStock(10);   // Adiciona 10 unidades
        System.out.println("Quantity in Stock after adding 10 items: " + item1.getQuantityInStock());
        
        // Verificando se o estoque está abaixo do nível mínimo
        System.out.println("\nChecking if the stock is below minimum...");
        item1.isBelowMinimumStock();

        // Criando outro item com parâmetros diferentes
        InventoryItem item2 = new InventoryItem("B456", "Product Y",  5);
        System.out.println("\nCreated another item: " + item2.getItemCode());

        // Tentando remover mais unidades do estoque do que o disponível
        item2.updateStock(-6);  // Tenta remover 6 unidades
        System.out.println("Quantity in Stock after removing 6 items: " + item2.getQuantityInStock());

        // Verificando novamente se o estoque de item2 está abaixo do nível mínimo
        item2.isBelowMinimumStock();
    }
}