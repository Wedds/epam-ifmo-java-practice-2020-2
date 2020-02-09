BEGIN;

CREATE TABLE Users
(
    Id            serial PRIMARY KEY,
    Email         text NOT NULL,
    Password_hash text NOT NULL,
    Birth_date    date NOT NULL,
    Signup_date   date NOT NULL
);



CREATE TABLE Client
(
    Id            int PRIMARY KEY
        REFERENCES Users (Id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    Pass_id       int
        REFERENCES Passport (Id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    Contact_phone text,
    Address       text
);

CREATE TABLE Admin
(
    Id int PRIMARY KEY
        REFERENCES Users (Id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
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
CREATE TABLE Passport
(
    Id int PRIMARY KEY
);

CREATE TABLE Car_damage
(
    Id int PRIMARY KEY
);



/* TODO: other tables here */

/* TODO: data creation procedures here */

COMMIT;