CREATE TABLE users
(
    id          SERIAL primary key,
    user_name   VARCHAR(50) NOT NULL,
    password    VARCHAR(50) NOT NULL,
    email       VARCHAR(50) NOT NULL,
    create_date timestamp   NOT NULL,
    user_role   VARCHAR(30) NOT NULL,
    company_id  BIGINT
);
CREATE TABLE companies
(
    id           SERIAL primary key,
    company_name VARCHAR(255) NOT NULL,
    email        VARCHAR(50)  NOT NULL,
    create_date  timestamp    NOT NULL,
    nip          VARCHAR(15)
);
ALTER TABLE users
    ADD CONSTRAINT fk_users_comanpies FOREIGN KEY (company_id) REFERENCES companies (id);
CREATE SEQUENCE hibernate_sequence START 1;