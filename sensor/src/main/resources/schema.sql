-- Description: Create table sensors
CREATE TABLE IF NOT EXISTS irrigation_times (
    id   BIGINT NOT NULL PRIMARY KEY,
    plot_id         TEXT,
    irrigated_at         TEXT,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP
);