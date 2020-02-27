BEGIN;

CREATE TYPE e_role_users AS ENUM (
    'client',
    'admin'
);

CREATE TABLE Passport (
    Id serial PRIMARY KEY,
    Issue_country text NOT NULL,
    Issuer text,
    Issue_date date NOT NULL,
    Expiration_date date,
    Serial_number text NOT NULL
);

CREATE TABLE Driving_license (
    Id serial PRIMARY KEY,
    Issue_date date NOT NULL,
    Expiration_date date,
    Serial_number text NOT NULL
);

CREATE TABLE Users (
    Id serial PRIMARY KEY,
    Email text NOT NULL,
    Password_hash text NOT NULL,
    Role e_role_users NOT NULL,
    Name text NOT NULL,
    Birth_date date NOT NULL,
    Signup_date date NOT NULL,
    Pass_id int
        REFERENCES Passport(Id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    Driving_license_id int
        REFERENCES Driving_license(Id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    Contact_phone text,
    Address text,
    Is_blocked boolean NOT NULL,
    Reputation numeric NOT NULL
);

CREATE TABLE Car_model (
    Id serial PRIMARY KEY,
    Brand_name text NOT NULL,
    Model_name text NOT NULL,
    Price_per_hour numeric NOT NULL
);

CREATE TYPE e_status_car as ENUM ( 'broken', 'taken', 'free' );

CREATE TABLE Car (
    Id serial PRIMARY KEY,
    Model_id int NOT NULL
        REFERENCES Car_model(Id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    Color text NOT NULL,
    REG_number text NOT NULL,
    VIN_number text NOT NULL,
    Status e_status_car NOT NULL
);

CREATE TYPE e_status_order AS ENUM (
    'open',
    'canceled',
    'denied',
    'approved',
    'waiting for payment',
    'close'
);

CREATE TABLE Orders (
    Id serial PRIMARY KEY,
    Car_id int NOT NULL
        REFERENCES Car (Id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    Client_id int NOT NULL
        REFERENCES Users (Id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    Admin_id int
        REFERENCES Users (Id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    Status e_status_order NOT NULL,
    Rent_start_date date NOT NULL,
    Rent_end_date date NOT NULL,
    Discount numeric NOT NULL
);

CREATE TYPE e_status_invoice AS ENUM ( 'open', 'close' );

CREATE TABLE Invoice (
    Id serial PRIMARY KEY,
    Order_id int NOT NULL
        REFERENCES Orders (Id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    Issue_date date NOT NULL,
    Payment_date date NOT NULL,
    Total_price numeric NOT NULL,
    Status e_status_invoice NOT NULL
);

CREATE TYPE e_status_damage AS ENUM (
    'fixed',
    'not fixed',
    'under repair'
);

CREATE TABLE Car_damage (
    Id serial PRIMARY KEY,
    Order_id int NOT NULL
        REFERENCES Orders (Id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    Description text NOT NULL,
    Accident_date date NOT NULL,
    Severity int NOT NULL,
    Repair_cost numeric NOT NULL,
    Status e_status_damage NOT NULL
);

COMMIT;
