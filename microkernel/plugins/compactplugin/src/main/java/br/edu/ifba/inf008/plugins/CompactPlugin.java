package br.edu.ifba.inf008.plugins;

import java.time.LocalDate;

import br.edu.ifba.inf008.interfaces.IVehicleTypes;

public class CompactPlugin extends IVehicleTypes {
    
    @Override
    public boolean init() {
        
        // Apenas retorna true, porque a classe fornece apenas cálculos, não elementos visuais a serem carregados
        return true;
    }

    // Calcula o valor total da locação
    @Override
    public double calculateTotalAmount(double baseRate, double insuranceFee, LocalDate startDate, LocalDate endDate, String additionalFees) {
        
        // Apenas para implementar o requisito de método polimórfico exigido no trabalho, mas sem precisar repetir as mesmas linhas de código em todos os veículos, já que todos calcularam o valor total da alocação da mesma forma
        return super.calculateTotalAmount(baseRate, insuranceFee, startDate, endDate, additionalFees);
    }
}
