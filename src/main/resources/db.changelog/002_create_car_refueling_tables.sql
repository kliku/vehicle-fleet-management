CREATE TABLE cars
(
    id        SERIAL primary key,
    car_brand VARCHAR(50) NOT NULL,
    car_model VARCHAR(50) NOT NULL,
    year      INT,
    user_id   BIGINT
);
CREATE TABLE refuelings
(
    id       SERIAL primary key,
    dateTime timestamp NOT NULL,
    price    Decimal   NOT NULL,
    liters   Decimal   NOT NULL,
    car_id   BIGINT    NOT NULL
);
ALTER TABLE cars
    ADD CONSTRAINT fk_cars_users FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE refuelings
    ADD CONSTRAINT fk_refuelings_cars FOREIGN KEY (car_id) REFERENCES cars (id);