DROP TABLE flights CASCADE;
DROP TABLE planes CASCADE;
DROP TABLE users CASCADE;
DROP TABLE countries CASCADE;
DROP TABLE paymentData CASCADE;
DROP TABLE tickets CASCADE;

CREATE TABLE IF NOT EXISTS countries
(
    country_ID serial PRIMARY KEY,
    countryName varchar not NULL
);
INSERT INTO countries(countryname) VALUES ('Russia');
INSERT INTO countries(countryname) VALUES ('USA');
INSERT INTO countries(countryname) VALUES ('France');
INSERT INTO countries(countryname) VALUES ('Israel');
INSERT INTO countries(countryname) VALUES ('Italy');
INSERT INTO countries(countryname) VALUES ('Germany');
INSERT INTO countries(countryname) VALUES ('Great Britain');
INSERT INTO countries(countryname) VALUES ('Switzerland');
INSERT INTO countries(countryname) VALUES ('Spain');
INSERT INTO countries(countryname) VALUES ('Hungary');

CREATE TABLE IF NOT EXISTS planes
(
    plane_ID serial PRIMARY KEY,
    planeModel varchar not NULL,
    rowsNumber integer not NULL,
    columnsNumber integer not NULL
);

INSERT INTO planes(planeModel, rowsNumber, columnsNumber) VALUES ('A-320',30, 6);
INSERT INTO planes(planeModel, rowsNumber, columnsNumber) VALUES ('Boeing777-200',44, 8);


CREATE TABLE IF NOT EXISTS users
(
    user_ID serial PRIMARY KEY,
    login varchar not NULL,
    userpassword varchar not NULL,
    isAdmin boolean not NULL,
    passport integer not NULL
);

INSERT INTO users(login, userpassword, isAdmin, passport) VALUES ('admin','', TRUE, 0);
INSERT INTO users(login, userpassword, isAdmin, passport) VALUES ('Eduard',12345, TRUE, 0000000);
INSERT INTO users(login, userpassword, isAdmin, passport) VALUES ('Shimon','j1krd7', FALSE, 74596770);
INSERT INTO users(login, userpassword, isAdmin, passport) VALUES ('Andrey','9ztnga', FALSE, 28091427);
INSERT INTO users(login, userpassword, isAdmin, passport) VALUES ('Bicalel','bao5lx', FALSE, 38822156);
INSERT INTO users(login, userpassword, isAdmin, passport) VALUES ('Avigail','vp9m0i', FALSE, 9473938);
INSERT INTO users(login, userpassword, isAdmin, passport) VALUES ('David','9g3ai0', FALSE, 16864321);

CREATE TABLE IF NOT EXISTS flights
(
    flight_ID serial PRIMARY KEY,
    flightname varchar not NULL,
    departureDate timestamp not NULL,
    arrivalDate timestamp not NULL,
    departureCountry_ID integer REFERENCES countries (country_ID) not NULL,
    arrivalCountry_ID integer REFERENCES countries (country_ID) not NULL,
    price integer not NULL,
    fk_plane_ID integer REFERENCES planes (plane_ID) not NULL
    );
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-428','10-02-2024 09:30:00', '10-02-2024 11:45:00', 4, 1, 800, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-429','11-02-2023 13:30:00', '11-02-2024 18:00:00', 1, 4, 900, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-433','15-02-2024 06:00:00', '15-02-2024 17:00:00', 4, 2, 3000, 2);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-434','15-02-2024 20:00:00', '16-02-2024 07:00:00', 2, 4, 2999, 2);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-467','17-02-2024 12:45:00', '17-02-2024 18:00:00', 4, 3, 600, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-468','17-02-2024 21:50:00', '18-02-2024 02:30:00', 3, 4, 600, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-473','20-02-2024 11:00:00', '20-02-2024 14:35:00' , 4, 5, 700, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-474','21-02-2024 17:20:00', '21-02-2024 21:00:00', 5, 4, 699, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-481','22-02-2024 10:40:00', '22-02-2024 15:00:00', 4, 6, 700, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-482','23-02-2024 17:35:00', '23-02-2024 22:15:00', 6, 4, 700, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-491','27-02-2024 05:45:00', '27-02-2024 11:20:00', 4, 7, 600, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-492','28-02-2024 13:15:00', '28-02-2024 19:00:00', 7, 4, 600, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-401','01-03-2024 21:45:00', '02-03-2024 01:20:00', 4, 8, 650, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-402','02-03-2024 04:30:00', '02-03-2024 08:45:00', 8, 4, 650, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-407','05-03-2024 01:00:00', '05-03-2024 06:30:00', 4, 9, 830, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-408','06-03-2024 17:35:00', '06-03-2024 21:50:00', 9, 4, 830, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-411','09-03-2024 14:25:00', '09-03-2024 17:05:00', 4, 10, 1200, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-412','10-03-2024 19:10:00', '10-03-2024 22:00:00',10, 4, 999, 1);


CREATE TABLE IF NOT EXISTS tickets
(
    fk_flight_ID integer REFERENCES flights (flight_ID)  not NULL,
    fk_user_ID integer REFERENCES users (user_ID) not NULL,
    seatRow integer not NULL,
    seatColumn integer not NULL,
    isPayed boolean not NULL,
    passengerFirstName varchar not NULL,
    passengerSecondName varchar not NULL,
    passengerBirthDate date not NULL,
    passengerSex varchar not NULL,
    PRIMARY KEY (fk_flight_ID, fk_user_ID),
    CONSTRAINT ticketToFlight FOREIGN KEY (fk_flight_ID)  REFERENCES flights (flight_ID) ON DELETE CASCADE
    );
INSERT INTO tickets VALUES (1,  2, 10, 1, TRUE, 'Shimon', 'Roitman', '11-01-2002', 'M');
INSERT INTO tickets VALUES (2,  2, 8, 2, TRUE, 'Shimon', 'Roitman', '11-01-2002', 'M');
INSERT INTO tickets VALUES (1,  3, 7, 6, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M');
INSERT INTO tickets VALUES (4,  3, 15, 4, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M');
INSERT INTO tickets VALUES (5,  3, 20, 2, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M');
INSERT INTO tickets VALUES (6,  3, 20, 2, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M');
INSERT INTO tickets VALUES (1,  4, 9, 5, TRUE, 'Bizalel', 'Basin', '08-03-2001', 'M');
INSERT INTO tickets VALUES (8,  4, 6, 1, TRUE, 'Bizalel', 'Basin', '08-03-2001', 'M');
INSERT INTO tickets VALUES (1,  5, 5, 3, TRUE, 'Avigail', 'Jacobson', '08-07-1999', 'F');
INSERT INTO tickets VALUES (10, 5, 6, 6, TRUE, 'Avigail', 'Jacobson', '08-07-1999', 'F');
INSERT INTO tickets VALUES (1,  6, 6, 6, FALSE, 'David', 'Mikhelson', '11-09-1990', 'M');

CREATE TABLE IF NOT EXISTS paymentData
(
    fk_user_ID integer REFERENCES users (user_ID) not NULL,
    cardNumber varchar not NULL,
    expireDate varchar not NULL,
    cardName varchar not NULL,
    cvv varchar not NULL,
    PRIMARY KEY (fk_user_ID, cardNumber)
    );
INSERT INTO  paymentData VALUES (2, '4580010554876947', '02/27', 'Andrey_Savvteev', '123');
