-- Create database
CREATE DATABASE IF NOT EXISTS car_rental_system;
USE car_rental_system;

-- Customers table
CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_type ENUM('INDIVIDUAL', 'COMPANY', 'VIP') NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    company_name VARCHAR(100),
    tax_id VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    loyalty_points INT DEFAULT 0,
    discount_rate DECIMAL(5,2) DEFAULT 0.00
);

-- Vehicle types (will map to Java classes)
CREATE TABLE vehicle_types (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(50) UNIQUE NOT NULL,
    daily_rate DECIMAL(10,2) NOT NULL,
    weekly_rate DECIMAL(10,2) NOT NULL,
    monthly_rate DECIMAL(10,2) NOT NULL,
    security_deposit DECIMAL(10,2) NOT NULL,
    insurance_rate DECIMAL(5,2) NOT NULL,
    late_fee_per_hour DECIMAL(10,2) NOT NULL,
    max_passengers INT,
    max_luggage INT,
    special_features TEXT,
    additional_fees JSON -- Stores type-specific fees as JSON
);

-- Vehicles table
CREATE TABLE vehicles (
    vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
    license_plate VARCHAR(20) UNIQUE NOT NULL,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year YEAR NOT NULL,
    type_id INT NOT NULL,
    color VARCHAR(30),
    fuel_type ENUM('GASOLINE', 'DIESEL', 'ELECTRIC', 'HYBRID', 'CNG') NOT NULL,
    transmission ENUM('MANUAL', 'AUTOMATIC') NOT NULL,
    mileage DECIMAL(10,2) DEFAULT 0,
    status ENUM('AVAILABLE', 'RENTED', 'MAINTENANCE', 'CLEANING', 'RESERVED') DEFAULT 'AVAILABLE',
    current_location VARCHAR(100),
    last_maintenance DATE,
    next_maintenance DATE,
    special_features JSON,
    FOREIGN KEY (type_id) REFERENCES vehicle_types(type_id) ON DELETE RESTRICT
);

-- Rentals table (main table for polymorphism exercise)
CREATE TABLE rentals (
    rental_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    rental_type ENUM('DAILY', 'WEEKLY', 'MONTHLY', 'HOURLY') NOT NULL,
    start_date TIMESTAMP NOT NULL,
    scheduled_end_date TIMESTAMP NOT NULL,
    actual_end_date TIMESTAMP NULL,
    pickup_location VARCHAR(100) NOT NULL,
    dropoff_location VARCHAR(100),
    initial_mileage DECIMAL(10,2) NOT NULL,
    final_mileage DECIMAL(10,2),
    base_rate DECIMAL(10,2) NOT NULL,
    insurance_fee DECIMAL(10,2) NOT NULL,
    additional_fees DECIMAL(10,2) DEFAULT 0,
    discount_amount DECIMAL(10,2) DEFAULT 0,
    late_fee DECIMAL(10,2) DEFAULT 0,
    damage_fee DECIMAL(10,2) DEFAULT 0,
    cleaning_fee DECIMAL(10,2) DEFAULT 0,
    total_amount DECIMAL(10,2) NOT NULL,
    paid_amount DECIMAL(10,2) DEFAULT 0,
    rental_status ENUM('ACTIVE', 'COMPLETED', 'CANCELLED', 'OVERDUE') DEFAULT 'ACTIVE',
    payment_status ENUM('PENDING', 'PAID', 'PARTIAL', 'REFUNDED') DEFAULT 'PENDING',
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id),
    INDEX idx_rental_dates (start_date, scheduled_end_date),
    INDEX idx_rental_status (rental_status)
);

-- Rental items (for additional services - polymorphism example)
CREATE TABLE rental_items (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    rental_id INT NOT NULL,
    item_type VARCHAR(50) NOT NULL,
    item_name VARCHAR(100) NOT NULL,
    quantity INT DEFAULT 1,
    unit_price DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    applicable_days VARCHAR(100), -- When this item applies (e.g., "weekend", "all")
    FOREIGN KEY (rental_id) REFERENCES rentals(rental_id) ON DELETE CASCADE
);

-- Payments table
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    rental_id INT NOT NULL,
    customer_id INT NOT NULL,
    payment_method ENUM('CREDIT_CARD', 'DEBIT_CARD', 'CASH', 'BANK_TRANSFER', 'DIGITAL_WALLET') NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    transaction_id VARCHAR(100),
    status ENUM('SUCCESS', 'FAILED', 'PENDING', 'REFUNDED') DEFAULT 'SUCCESS',
    FOREIGN KEY (rental_id) REFERENCES rentals(rental_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Maintenance records
CREATE TABLE maintenance_records (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    maintenance_type ENUM('ROUTINE', 'REPAIR', 'ACCIDENT', 'CLEANING') NOT NULL,
    description TEXT NOT NULL,
    cost DECIMAL(10,2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    performed_by VARCHAR(100),
    notes TEXT,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

-- Price modifiers table (for dynamic pricing rules)
CREATE TABLE price_modifiers (
    modifier_id INT PRIMARY KEY AUTO_INCREMENT,
    modifier_type VARCHAR(50) NOT NULL,
    vehicle_type_id INT NULL,
    customer_type VARCHAR(50) NULL,
    day_of_week ENUM('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY') NULL,
    season VARCHAR(20) NULL,
    modifier_value DECIMAL(5,2) NOT NULL, -- Percentage or fixed amount
    modifier_operation ENUM('PERCENTAGE_INCREASE','PERCENTAGE_DECREASE','FIXED_ADD','FIXED_SUBTRACT') NOT NULL,
    start_date DATE,
    end_date DATE,
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (vehicle_type_id) REFERENCES vehicle_types(type_id)
);

-- Insert vehicle types (each will map to different Java class)
INSERT INTO vehicle_types (type_name, daily_rate, weekly_rate, monthly_rate,
                          security_deposit, insurance_rate, late_fee_per_hour,
                          max_passengers, max_luggage, special_features, additional_fees) VALUES
('ECONOMY', 45.00, 250.00, 900.00, 200.00, 15.00, 5.00, 4, 2, 'AC, Radio', '{"mileage_limit": 200, "extra_mileage_fee": 0.25}'),
('COMPACT', 55.00, 300.00, 1100.00, 250.00, 18.00, 6.00, 5, 3, 'AC, Radio, Bluetooth', '{"mileage_limit": 250, "extra_mileage_fee": 0.30}'),
('SUV', 85.00, 500.00, 1800.00, 500.00, 25.00, 8.00, 7, 5, '4WD, AC, GPS, Roof Rack', '{"mileage_limit": 300, "extra_mileage_fee": 0.35, "offroad_fee": 50.00}'),
('LUXURY', 150.00, 900.00, 3200.00, 1000.00, 40.00, 15.00, 4, 3, 'Leather Seats, Premium Sound, Heated Seats', '{"concierge_fee": 100.00, "chauffeur_fee": 200.00}'),
('VAN', 120.00, 700.00, 2500.00, 600.00, 30.00, 10.00, 12, 8, 'AC, DVD Player, Extra Seats', '{"extra_passenger_fee": 10.00, "driver_fee": 50.00}'),
('ELECTRIC', 70.00, 400.00, 1400.00, 300.00, 20.00, 7.00, 5, 3, 'Fast Charging, Touch Screen', '{"charging_fee": 25.00, "battery_depletion_fee": 50.00}');

-- Insert customers
INSERT INTO customers (customer_type, first_name, last_name, email, phone, address, loyalty_points, discount_rate) VALUES
('INDIVIDUAL', 'John', 'Doe', 'john.doe@email.com', '+1-555-0101', '123 Main St, Anytown', 1250, 5.00),
('COMPANY', NULL, NULL, 'acme@company.com', '+1-555-0102', '456 Corporate Blvd, Business City', 5000, 15.00),
('VIP', 'Sarah', 'Smith', 'sarah.smith@email.com', '+1-555-0103', '789 Luxury Ave, Prestige Town', 10000, 20.00),
('INDIVIDUAL', 'Mike', 'Johnson', 'mike.j@email.com', '+1-555-0104', '321 Oak St, Smallville', 500, 0.00),
('COMPANY', NULL, NULL, 'tech@startup.com', '+1-555-0105', '654 Innovation Dr, Tech Park', 2500, 10.00);

-- Insert vehicles
INSERT INTO vehicles (license_plate, make, model, year, type_id, color, fuel_type, transmission,
                      mileage, status, current_location) VALUES
('ABC123', 'Toyota', 'Corolla', 2022, 1, 'White', 'GASOLINE', 'AUTOMATIC', 15234.5, 'AVAILABLE', 'Downtown Branch'),
('XYZ789', 'Honda', 'Civic', 2023, 2, 'Blue', 'GASOLINE', 'MANUAL', 8921.0, 'AVAILABLE', 'Airport Branch'),
('SUV456', 'Ford', 'Explorer', 2022, 3, 'Black', 'DIESEL', 'AUTOMATIC', 23456.7, 'RENTED', 'In Transit'),
('LUX001', 'Mercedes', 'S-Class', 2023, 4, 'Silver', 'GASOLINE', 'AUTOMATIC', 1250.0, 'AVAILABLE', 'Premium Lounge'),
('VAN777', 'Chevrolet', 'Express', 2021, 5, 'White', 'GASOLINE', 'AUTOMATIC', 45678.9, 'MAINTENANCE', 'Service Center'),
('EV2023', 'Tesla', 'Model 3', 2023, 6, 'Red', 'ELECTRIC', 'AUTOMATIC', 8765.4, 'AVAILABLE', 'Downtown Branch'),
('DEF456', 'Nissan', 'Altima', 2022, 2, 'Gray', 'HYBRID', 'AUTOMATIC', 18900.0, 'RESERVED', 'City Center'),
('GHI789', 'BMW', 'X5', 2023, 3, 'Blue', 'GASOLINE', 'AUTOMATIC', 5600.0, 'AVAILABLE', 'Airport Branch');

-- Insert active rentals (for polymorphism demonstration)
INSERT INTO rentals (customer_id, vehicle_id, rental_type, start_date, scheduled_end_date,
                     pickup_location, initial_mileage, base_rate, insurance_fee, total_amount, rental_status) VALUES
(1, 3, 'WEEKLY', '2024-01-15 10:00:00', '2024-01-22 10:00:00', 'Airport Branch', 23456.7, 500.00, 25.00, 525.00, 'ACTIVE'),
(2, 5, 'MONTHLY', '2024-01-01 09:00:00', '2024-02-01 09:00:00', 'Downtown Branch', 45678.9, 2500.00, 30.00, 2530.00, 'ACTIVE'),
(3, 4, 'DAILY', '2024-01-20 14:00:00', '2024-01-21 14:00:00', 'Premium Lounge', 1250.0, 150.00, 40.00, 190.00, 'COMPLETED');

-- Insert completed rental with additional fees (for reports)
INSERT INTO rentals (customer_id, vehicle_id, rental_type, start_date, scheduled_end_date, actual_end_date,
                     pickup_location, dropoff_location, initial_mileage, final_mileage, base_rate, insurance_fee,
                     additional_fees, late_fee, total_amount, paid_amount, rental_status, payment_status) VALUES
(1, 1, 'DAILY', '2024-01-10 09:00:00', '2024-01-11 09:00:00', '2024-01-11 11:30:00',
 'Downtown Branch', 'Airport Branch', 15000.0, 15150.0, 45.00, 15.00, 25.00, 12.50, 97.50, 97.50, 'COMPLETED', 'PAID');

-- Insert rental items (for additional services polymorphism)
INSERT INTO rental_items (rental_id, item_type, item_name, quantity, unit_price, total_price, applicable_days) VALUES
(1, 'EXTRA_EQUIPMENT', 'GPS Navigation', 1, 15.00, 15.00, 'all'),
(1, 'INSURANCE', 'Additional Coverage', 1, 10.00, 10.00, 'all'),
(2, 'SERVICE', 'Driver Service', 7, 50.00, 350.00, 'weekend'),
(3, 'LUXURY', 'Chauffeur Service', 1, 200.00, 200.00, 'all');

-- Insert payments
INSERT INTO payments (rental_id, customer_id, payment_method, amount, payment_date, status) VALUES
(4, 1, 'CREDIT_CARD', 97.50, '2024-01-11 11:35:00', 'SUCCESS'),
(3, 3, 'CREDIT_CARD', 190.00, '2024-01-21 14:30:00', 'SUCCESS');

-- Insert maintenance records
INSERT INTO maintenance_records (vehicle_id, maintenance_type, description, cost, start_date, end_date, performed_by) VALUES
(5, 'REPAIR', 'Brake system replacement', 450.00, '2024-01-18', '2024-01-19', 'AutoCare Pro'),
(1, 'ROUTINE', 'Oil change and tire rotation', 120.00, '2024-01-05', '2024-01-05', 'Quick Lube'),
(6, 'CLEANING', 'Deep interior cleaning', 75.00, '2024-01-12', '2024-01-12', 'Sparkle Clean');

-- Insert dynamic pricing rules
INSERT INTO price_modifiers (modifier_type, vehicle_type_id, customer_type, day_of_week,
                             modifier_value, modifier_operation, start_date, end_date) VALUES
('WEEKEND_SURCHARGE', NULL, NULL, 'SATURDAY', 10.00, 'PERCENTAGE_INCREASE', NULL, NULL),
('WEEKEND_SURCHARGE', NULL, NULL, 'SUNDAY', 10.00, 'PERCENTAGE_INCREASE', NULL, NULL),
('VIP_DISCOUNT', NULL, 'VIP', NULL, 20.00, 'PERCENTAGE_DECREASE', NULL, NULL),
('COMPANY_DISCOUNT', NULL, 'COMPANY', NULL, 15.00, 'PERCENTAGE_DECREASE', NULL, NULL),
('LUXURY_WEEKEND', 4, NULL, 'SATURDAY', 25.00, 'PERCENTAGE_INCREASE', NULL, NULL),
('SUMMER_RATE', 3, NULL, NULL, 15.00, 'PERCENTAGE_INCREASE', '2024-06-01', '2024-08-31');

-- First, insert more sample data to get ~100 results
INSERT INTO rentals (customer_id, vehicle_id, rental_type, start_date, scheduled_end_date,
                     actual_end_date, pickup_location, initial_mileage, final_mileage,
                     base_rate, insurance_fee, total_amount, rental_status, payment_status)
SELECT
    FLOOR(1 + RAND() * 5) AS customer_id,
    FLOOR(1 + RAND() * 8) AS vehicle_id,
    ELT(FLOOR(1 + RAND() * 4), 'DAILY', 'WEEKLY', 'MONTHLY', 'DAILY') AS rental_type,
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY) AS start_date,
    DATE_ADD(DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY),
            INTERVAL FLOOR(1 + RAND() * 30) DAY) AS scheduled_end_date,
    CASE WHEN RAND() > 0.3 THEN
        DATE_ADD(DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY),
                INTERVAL FLOOR(1 + RAND() * 35) DAY)
    ELSE NULL END AS actual_end_date,
    ELT(FLOOR(1 + RAND() * 3), 'Downtown Branch', 'Airport Branch', 'City Center') AS pickup_location,
    FLOOR(RAND() * 50000) AS initial_mileage,
    CASE WHEN RAND() > 0.3 THEN
        FLOOR(RAND() * 60000)
    ELSE NULL END AS final_mileage,
    ROUND(30 + RAND() * 200, 2) AS base_rate,
    ROUND(10 + RAND() * 30, 2) AS insurance_fee,
    ROUND(40 + RAND() * 250, 2) AS total_amount,
    ELT(FLOOR(1 + RAND() * 4), 'ACTIVE', 'COMPLETED', 'COMPLETED', 'CANCELLED') AS rental_status,
    ELT(FLOOR(1 + RAND() * 3), 'PAID', 'PENDING', 'PARTIAL') AS payment_status
FROM
    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
     UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) t1,
    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
     UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) t2
LIMIT 100;
