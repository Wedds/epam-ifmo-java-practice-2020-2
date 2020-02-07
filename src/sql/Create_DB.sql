BEGIN;

CREATE TABLE User (
    Id serial PRIMARY KEY,
    Email text NOT NULL,
    Password_hash text NOT NULL,
    Birth_date date NOT NULL,
    Signup_date date NOT NULL
);

CREATE TABLE Client (
    Id int PRIMARY KEY
        REFERENCES User(Id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    Pass_id int
        REFERENCES Passport(Id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    Contact_phone text,
    Address text
);

CREATE TABLE Admin (
    Id int PRIMARY KEY
        REFERENCES User(Id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

/* TODO: other tables here */

/* TODO: data creation procedures here */

COMMIT;