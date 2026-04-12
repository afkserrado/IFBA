Exercise 1: TaxCalculator

Refactor TaxCalculator

Problem: You have three region-specific order processor (USOrderProcessor, EUOrderProcessor, UKOrderProcessor) that each duplicate the same tax calculation logic. Your task is to extract a TaxCalculator interface with region-specific implementations, then refactor the order processors to use it.

Requirements:

- Create a TaxCalculator interface with a calculateTax(amount) method.
- Implement USTaxCalculator (10% tax), EUTaxCalculator (20% tax), and UKTaxCalculator (15% tax).
- Refactor the order processors to accept a TaxCalculator instead of duplicating tax logic.
- Each order processor should print the subtotal, tax amount, and total.

```java
// Before: Each processor duplicates tax calculation
class USOrderProcessor {
    public void processOrder(double amount) {
        double tax = amount * 0.10; // Duplicated tax logic
        double total = amount + tax;
        System.out.println("US Order - Subtotal: $" + amount
            + ", Tax: $" + tax + ", Total: $" + total);
    }
}

class EUOrderProcessor {
    public void processOrder(double amount) {
        double tax = amount * 0.20; // Duplicated tax logic
        double total = amount + tax;
        System.out.println("EU Order - Subtotal: $" + amount
            + ", Tax: $" + tax + ", Total: $" + total);
    }
}

class UKOrderProcessor {
    public void processOrder(double amount) {
        double tax = amount * 0.15; // Duplicated tax logic
        double total = amount + tax;
        System.out.println("UK Order - Subtotal: $" + amount
            + ", Tax: $" + tax + ", Total: $" + total);
    }
}

// TODO: Extract a TaxCalculator interface and region implementations.
// TODO: Refactor OrderProcessor to accept a TaxCalculator.

public class Main {
    public static void main(String[] args) {
        // After refactoring, usage should look like:
        // OrderProcessor usProcessor = new OrderProcessor(new USTaxCalculator());
        // usProcessor.processOrder(100.0);
    }
}
```
