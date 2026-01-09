package br.edu.ifba.inf008.interfaces;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class IVehicleTypes {
    
    // Instancia o parser, que permitirá a "conversão" do JSON em um objeto Java
    private final ObjectMapper mapper = new ObjectMapper();

    // Calcula o valor total da locação
    public double calculateTotalAmount(double baseRate, double insuranceFee, LocalDate startDate, LocalDate endDate, String additionalFees) throws Exception {
        return (baseRate * calculateNumberOfDays(startDate, endDate)) + insuranceFee + sumAdditionalFees(additionalFees);
    }

    // Calcula a quantidade de diárias
    public long calculateNumberOfDays(LocalDate startDate, LocalDate endDate) {

        if(endDate.isBefore(startDate)) throw new IllegalArgumentException("Data de devolução não pode ser anterior à data de coleta.");

        if(startDate.equals(endDate)) return 1; // Conta pelo menos 1 diária
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    // Transforma uma String JSON em um Map e retorna a soma de todas as taxas adicionais da locação
    // Formato: {"concierge_fee": 100.00, "chauffeur_fee": 200.00}
    public double sumAdditionalFees(String additionalFeesJson) {
        if(additionalFeesJson == null || additionalFeesJson.isBlank()) return 0.0;

        // Lê o JSON e devolve um objeto Java, neste caso um Map<String, Object>
        try {
            Map<String, Object> map = mapper.readValue(
            additionalFeesJson, 
            new TypeReference<Map<String, Object>>() {}
            );

            // Inicializa o acumulador
            double total = 0.0;

            // Percorre o mapa e soma as taxas adicionais
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();              

                if(key.endsWith("_fee")) {
                    Object value = entry.getValue();

                    // Validação necessária, pois JSON não tem tipagem estática
                    if(value instanceof Number) {
                        Number n = (Number) value;
                        total += n.doubleValue();
                    }
                    else {
                        throw new IllegalArgumentException("Fee não numérico: " + key + "=" + value);
                    }                    
                }
            }
            
            return total;
        } 

        catch (JsonProcessingException e) {
            throw new IllegalArgumentException("additional_fees inválido: " + additionalFeesJson, e);
        }
    }
}
