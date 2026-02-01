package br.edu.ifba.inf008.plugins.helpers;

import java.math.BigDecimal;

public class RentalsReportReadDTO {
    
    private final int rentalId;
    private final String customerName;
    private final String customerType;
    private final String vehicle;
    private final String vehicleType;
    private final String startDate;
    private final BigDecimal totalAmount;
    private final String rentalStatus;
    private final String paymentStatus;

    public RentalsReportReadDTO(
        int rentalId, 
        String customerName, 
        String customerType,         
        String vehicle, 
        String vehicleType, 
        String startDate,
        BigDecimal totalAmount, 
        String rentalStatus, 
        String paymentStatus) {
            
        this.rentalId = rentalId;
        this.customerName = customerName;
        this.customerType = customerType;
        this.vehicle = vehicle;
        this.vehicleType = vehicleType;
        this.startDate = startDate;
        this.totalAmount = totalAmount;
        this.rentalStatus = rentalStatus;
        this.paymentStatus = paymentStatus;
    }

    public int getRentalId() { return rentalId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerType() { return customerType; }
    public String getVehicle() { return vehicle; }
    public String getVehicleType() { return vehicleType; }
    public String getStartDate() { return startDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getRentalStatus() { return rentalStatus; }
    public String getPaymentStatus() { return paymentStatus; }
}
