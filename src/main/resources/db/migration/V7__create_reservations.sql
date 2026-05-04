CREATE TABLE IF NOT EXISTS reservations (
                                            id BIGSERIAL PRIMARY KEY,
                                            accommodation_id BIGINT REFERENCES accommodations(id),
    rented_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );