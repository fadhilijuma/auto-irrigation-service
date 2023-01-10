-- Description: Create table crops
CREATE TABLE IF NOT EXISTS crops (
     id   BIGINT NOT NULL,
     ideal_moisture_content         TEXT,
     crop_type         TEXT UNIQUE,
     created_at  TIMESTAMP,
     updated_at  TIMESTAMP,

     PRIMARY KEY (id),
     UNIQUE (crop_type)

);
-- Description: Create table sensors
CREATE TABLE IF NOT EXISTS sensors (
       id   BIGINT NOT NULL PRIMARY KEY,
       sensor_id         TEXT UNIQUE,
       sensor_type         TEXT,
       created_at  TIMESTAMP,
       updated_at  TIMESTAMP
);
-- Description: Create table plots
CREATE TABLE IF NOT EXISTS plots (
     id       BIGINT NOT NULL PRIMARY KEY,
     plot_number          TEXT UNIQUE,
     acreage         TEXT,
     crop_id BIGINT,
     sensor_id BIGINT,
     current_moisture_content         TEXT,
     evapotranspiration         TEXT,
     created_at  TIMESTAMP,
     updated_at  TIMESTAMP,

     FOREIGN KEY (crop_id) REFERENCES crops(id) ON DELETE NO ACTION,
     FOREIGN KEY (sensor_id) REFERENCES sensors(id) ON DELETE NO ACTION

);