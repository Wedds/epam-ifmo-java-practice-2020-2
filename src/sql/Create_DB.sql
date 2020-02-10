BEGIN;

CREATE TYPE e_role_users AS ENUM (
    'client',
    'admin'
);

CREATE TABLE Users (
    Id serial PRIMARY KEY,
    Email text NOT NULL,
    Password_hash text NOT NULL,
    Role e_role_users NOT NULL,
    Name text NOT NULL,
    Birth_date  date NOT NULL,
    Signup_date date NOT NULL,
    Pass_id int
        REFERENCES Passport(Id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    Contact_phone text,
    Address text,
    Is_blocked boolean NOT NULL
);

----------------------------------------
CREATE TABLE Car_model (
    Id int PRIMARY KEY,
    Brand_name text NOT NULL,
    Model_name text NOT NULL,
    Price_per_hour double precision NOT NULL
);

CREATE TYPE car_status as ENUM ('broken','taken','free');

CREATE TABLE Car (
    Id int PRIMARY KEY,
    Model_id int
        REFERENCES Car_model(Id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    Color text NOT NULL,
    REG_number text NOT NULL,
    VIN_number text NOT NULL,
    Status car_status NOT NULL
);
----------------------------------------
CREATE TYPE e_status_order AS ENUM (
    'open',
    'canceled',
    'approved',
    'waiting for payment',
    'close');

CREATE TYPE e_status_invoice AS ENUM (
    'open',
    'close');

CREATE TABLE Ord
(
    Id              serial PRIMARY KEY,
    Car_id          int
        REFERENCES Car (Id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    Client_id       int
        REFERENCES Client (Id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    Admin_id        int
        REFERENCES Admin (Id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    Status          e_status_order NOT NULL,
    Rent_start_date date           NOT NULL,
    Rent_end_date   date           NOT NULL
);

CREATE TABLE Invoice
(
    Id           serial PRIMARY KEY,
    Order_id     int
        REFERENCES Ord (Id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    Issuer_id    int
        REFERENCES Client (Id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    Issue_date   date             NOT NULL,
    Payment_date date             NOT NULL,
    Total_price  int              NOT NULL,
    Status       e_status_invoice NOT NULL
);
----------------------------------------
CREATE TYPE damage_status AS ENUM (
    'fixed',
    'not fixed',
    'in process');
CREATE TABLE Passport
(
    Id int PRIMARY KEY,
    Issue_country text NOT NULL ,
    Issuer text ,
    Issue_date date NOT NULL ,
    Expiration_date date NOT NULL ,
    Serial_number int NOT NULL
);

CREATE TABLE Car_damage
(
    Id int PRIMARY KEY,
    Order_id int
        REFERENCES Ord (Id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    Type text,
    Description text NOT NULL,
    Date date NOT NULL,
    Severity int NOT NULL ,
    Repair_cost int NOT NULL,
    Status text NOT NULL
);



/* TODO: other tables here */

/* TODO: data creation procedures here */

COMMIT;