/*
Questão 2: Sistema de Processamento de Pedidos para E-commerce com Múltiplas Estratégias

Desenvolva um sistema de processamento de pedidos que utilize hierarquias paralelas para estratégias de processamento e cálculo de frete. O sistema deve ser extensível para novos tipos de pedidos e regras de negócio sem requerer mudanças nos métodos processOrder() e processOrderBatch().
*/

package br.ifba.l3q2;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class OrderManagementApp {
    public static void main(String[] args) {
        
        OrderProcessor processor = new OrderProcessor();

        //
        // Criando produtos
        //
        Product p1 = new Product("P001", "Notebook", 1, 3500.00);
        Product p2 = new Product("P002", "Mouse", 2, 80.00);
        Product p3 = new Product("P003", "Headset", 1, 250.00);

        // Lista de produtos
        List<Product> items = Arrays.asList(p1, p2, p3);

        //
        // Criando consumidores
        //
        Customer customer = new Customer("11144455546", "Fulano de Tal");
        
        
        // Diferentes tipos de pedidos com estratégias específicas
        Order standardOrder = new StandardOrder(items, customer);
        Order expressOrder = new ExpressOrder(items, customer);
        Order internationalOrder = new InternationalOrder(items, customer);
        
        
        // Processamento polimórfico através de múltiplas hierarquias
        ProcessingResult result1 = processor.processOrder(
            standardOrder,
            new StandardProcessingStrategy(),
            new DomesticShippingCalculator()
        );
        
        ProcessingResult result2 = processor.processOrder(
            expressOrder,
            new ExpressProcessingStrategy(),
            new PriorityShippingCalculator()
        );
        
        ProcessingResult result3 = processor.processOrder(
            internationalOrder,
            new InternationalProcessingStrategy(),
            new InternationalShippingCalculator()
        );
        
        
        // Processamento em lote com diferentes combinações
        List<Order> orderBatch = Arrays.asList(standardOrder, expressOrder);
        Map<Order, IProcessingStrategy> strategyMap = new HashMap<>();
        strategyMap.put(standardOrder, new StandardProcessingStrategy());
        strategyMap.put(expressOrder, new ExpressProcessingStrategy());
        
        
        List<ProcessingResult> batchResults = processor.processOrderBatch(
            orderBatch,
            strategyMap,
            new DomesticShippingCalculator()
        );
    }
}
