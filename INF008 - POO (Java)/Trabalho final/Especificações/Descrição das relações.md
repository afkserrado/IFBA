vehicle_types (1,1) <----------> (0,n) vehicles
vehicles (1,1) <---------------> (0,n) maintenance_records
vehicles (1,1) <---------------> (0,n) rentals
rentals (1,1) <----------------> (0,n) rental_items
customers (1,1) <--------------> (0,n) rentals
customers (1,1) <--------------> (0,n) payments
rentals (1,1) <----------------> (0,n) payments
vehicle_types (0,1) <----------> (0,n) price_modifiers
