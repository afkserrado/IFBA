SELECT 
    v.fuel_type,
    COUNT(*) AS vehicle_count,
    SUM(CASE WHEN v.status = 'AVAILABLE' THEN 1 ELSE 0 END) AS available_count,
    SUM(CASE WHEN v.status = 'RENTED' THEN 1 ELSE 0 END) AS rented_count,
    ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS fleet_percentage,
    CASE v.fuel_type
        WHEN 'GASOLINE' THEN '#FF6B6B'
        WHEN 'DIESEL' THEN '#4ECDC4'
        WHEN 'ELECTRIC' THEN '#45B7D1'
        WHEN 'HYBRID' THEN '#96CEB4'
        WHEN 'CNG' THEN '#FFEAA7'
        ELSE '#D9D9D9'
    END AS chart_color
FROM vehicles v
GROUP BY v.fuel_type
ORDER BY vehicle_count DESC;
