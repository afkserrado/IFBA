package br.edu.ifba.inf008.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentalsInsertDTO {

    private final int customerId;
    private final int vehicleId;
    private final RentalType rentalType = RentalType.DAILY; // A especificação do trabalho exige "DAILY"
    private final LocalDateTime startDate;
    private final LocalDateTime scheduledEndDate;
    private final String pickupLocation;
    private final BigDecimal initialMileage;
    private final BigDecimal baseRate;
    private final BigDecimal insuranceFee;
    private final BigDecimal totalAmount;

    public RentalsInsertDTO(
        int customerId, 
        int vehicleId, 
        //RentalType rentalType,                
        LocalDateTime startDate, 
        LocalDateTime scheduledEndDate,            
        String pickupLocation, 
        BigDecimal initialMileage,          
        BigDecimal baseRate, 
        BigDecimal insuranceFee, 
        BigDecimal totalAmount
    ) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        //this.rentalType = rentalType;
        this.startDate = startDate;
        this.scheduledEndDate = scheduledEndDate;
        this.pickupLocation = pickupLocation;
        this.initialMileage = initialMileage;
        this.baseRate = baseRate;
        this.insuranceFee = insuranceFee;
        this.totalAmount = totalAmount;
    }

    public int getCustomerId() { return customerId; }
    public int getVehicleId() { return vehicleId; }
    public RentalType getRentalType() { return rentalType; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getScheduledEndDate() { return scheduledEndDate; }
    public String getPickupLocation() { return pickupLocation; }
    public BigDecimal getInitialMileage() { return initialMileage; }
    public BigDecimal getBaseRate() { return baseRate; }
    public BigDecimal getInsuranceFee() { return insuranceFee; }
    public BigDecimal getTotalAmount() { return totalAmount; }
}