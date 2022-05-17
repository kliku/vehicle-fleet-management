CREATE TABLE repairs
(
    id           SERIAL primary key,
    create_time  timestamp NOT NULL,
    price        Decimal   NOT NULL,
    descriptions varchar(2000),
    car_id       BIGINT    NOT NULL
);
CREATE TABLE overviews
(
    id           SERIAL primary key,
    create_time  timestamp NOT NULL,
    price        Decimal   NOT NULL,
    descriptions varchar(2000),
    valid_date   timestamp,
    is_valid     boolean,
    car_id       BIGINT    NOT NULL
);
CREATE TABLE faults
(
    id           SERIAL primary key,
    create_time  timestamp NOT NULL,
    price        Decimal   NOT NULL,
    descriptions varchar(2000),
    is_repaired  boolean,
    car_id       BIGINT    NOT NULL
);

ALTER TABLE repairs
    ADD CONSTRAINT fk_faults_cars FOREIGN KEY (car_id) REFERENCES cars (id);
ALTER TABLE overviews
    ADD CONSTRAINT fk_overviews_cars FOREIGN KEY (car_id) REFERENCES cars (id);
ALTER TABLE faults
    ADD CONSTRAINT fk_faults_cars FOREIGN KEY (car_id) REFERENCES cars (id);
ALTER TABLE refuelings
    RENAME date_time TO create_time;