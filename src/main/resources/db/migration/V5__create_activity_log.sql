CREATE TABLE IF NOT EXISTS activity_log (
                                            id BIGSERIAL PRIMARY KEY,
                                            accommodation_name VARCHAR(255),
    event_time TIMESTAMP,
    event_type VARCHAR(100)
    );