package br.edu.ifba.inf008.dtos.Vehicles;

// DTO (Data Transfer Object) para implementação da TableView
public class VehiclesReadDTO {
    
    private int vehicleId;
    private String make;
    private String model;
    private int year;
    private FuelType fuelType;
    private Transmission transmission;
    private double mileage;

    public VehiclesReadDTO(
        int vehicleId,
        String make, 
        String model, 
        int year, 
        FuelType fuelType, 
        Transmission transmission, 
        double mileage
    ) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.mileage = mileage;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public double getMileage() {
        return mileage;
    }
}
