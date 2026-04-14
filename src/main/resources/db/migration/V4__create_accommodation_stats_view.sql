CREATE MATERIALIZED VIEW accommodation_stats_view AS
SELECT
    a.category,
    COUNT(a.id) AS total_accommodations,
    SUM(a.num_rooms) AS total_rooms,
    AVG(a.num_rooms) AS avg_rooms
FROM accommodations a
GROUP BY a.category;