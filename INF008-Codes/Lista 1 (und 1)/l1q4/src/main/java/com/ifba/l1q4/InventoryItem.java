package com.ifba.l1q4;

class InventoryItem {
    private String itemCode, description;
    private double unitPrice = 0;
    private int quantityInStock = 0, minimumStockLevel = 0;

    // Constructors
    public InventoryItem(String itemCode, String description, double unitPrice, int quantityInStock, int minimumStockLevel) {
        this(itemCode, description, quantityInStock);
        setUnitPrice(unitPrice);
        setMinimumStockLevel(minimumStockLevel);
    }

    public InventoryItem(String itemCode, String description, int quantityInStock) {
        this(itemCode, description);
        setQuantityInStock(quantityInStock);
    }

    public InventoryItem(String itemCode, String description) {
        setItemCode(itemCode);
        setDescription(description);
    }

    // Getters
    public String getItemCode() {
        return this.itemCode;
    }

    public String getDescription() {
        return this.description;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public int getQuantityInStock() {
        return this.quantityInStock;
    }

    public int getMinimumStockLevel() {
        return this.minimumStockLevel;
    }

    // Setters
    private void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setUnitPrice(double unitPrice) {
        if(unitPrice >= 0) {
            this.unitPrice = unitPrice;
        }
        else {
            printNegativeValue("unit price");
        }
    }

    private void setQuantityInStock(int quantityInStock) {
        if(quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
        }
        else {
            printNegativeValue("stock");
        }
    }

    private void setMinimumStockLevel(int minimumStockLevel) {
        if (minimumStockLevel >= 0) {
            this.minimumStockLevel = minimumStockLevel;
        }
        else {
            printNegativeValue("minimum stock level");
        }
    }

    private void printNegativeValue(String parameter) {
        System.out.println("The " + parameter + " can't be negative.");
    }

    // Public methods
    public void applyDiscount(double percentage) {
        if(this.unitPrice > 0 && percentage > 0) {
            this.unitPrice *= (100 - percentage) / 100;
        }
    }

    public void updateStock(int quantity) {
        // Prevent negative stock
        if((this.quantityInStock + quantity) >= 0) {
                this.quantityInStock += quantity;
        }
        else {
            System.out.println("Cannot update stock. Resulting stock would be negative.");
        }
    }

    public void updateStock(double quantity) {
        // Prevent negative stock
        if(quantity < 0) {
            if((this.quantityInStock + quantity) >= 0) {
                this.quantityInStock += quantity;
            }
        }
        else {
            this.quantityInStock += quantity;
        }
    }

    public void isBelowMinimumStock() {
        if(this.quantityInStock < this.minimumStockLevel) {
            System.out.println("Quantity in stock is below the minimum stock level.");
        }
        else {
            System.out.println("Quantity in stock is above the minimum stock level.");
        }
    }
}