SELECT 
    r.rental_id,
    COALESCE(c.company_name, CONCAT(c.first_name, ' ', c.last_name)) as customer_name,
    c.customer_type,
    CONCAT(v.make, ' ', v.model) as vehicle,
    vt.type_name as vehicle_type,
    DATE_FORMAT(r.start_date, '%Y-%m-%d') as start_date,
    r.total_amount,
    r.rental_status,
    r.payment_status
FROM rentals r
JOIN customers c ON r.customer_id = c.customer_id
JOIN vehicles v ON r.vehicle_id = v.vehicle_id
JOIN vehicle_types vt ON v.type_id = vt.type_id
ORDER BY r.start_date DESC
LIMIT 100;
