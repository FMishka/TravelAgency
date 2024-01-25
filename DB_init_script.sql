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
INSERT INTO countries(countryname) VALUES ('Moscow (SVO)');
INSERT INTO countries(countryname) VALUES ('New York (JFK)');
INSERT INTO countries(countryname) VALUES ('Paris (CDG)');
INSERT INTO countries(countryname) VALUES ('Tel Aviv (TLV)');
INSERT INTO countries(countryname) VALUES ('Milan (MXP)');
INSERT INTO countries(countryname) VALUES ('Berlin (BER)');
INSERT INTO countries(countryname) VALUES ('London (LHR)');
INSERT INTO countries(countryname) VALUES ('Zurich (ZRH)');
INSERT INTO countries(countryname) VALUES ('Madrid (MAD)');
INSERT INTO countries(countryname) VALUES ('Budapest (BUD)');

CREATE TABLE IF NOT EXISTS planes
(
    plane_ID serial PRIMARY KEY,
    planeModel varchar not NULL,
    rowsNumber integer not NULL,
    columnsNumber integer not NULL
);

INSERT INTO planes(planeModel, rowsNumber, columnsNumber) VALUES ('A-320',30, 6);
INSERT INTO planes(planeModel, rowsNumber, columnsNumber) VALUES ('Sukhoi Superjet 100',20, 5);


CREATE TABLE IF NOT EXISTS users
(
    user_ID serial PRIMARY KEY,
    login varchar not NULL,
    userpassword varchar not NULL,
    isAdmin boolean not NULL
);

INSERT INTO users(login, userpassword, isAdmin) VALUES ('admin','', TRUE);
INSERT INTO users(login, userpassword, isAdmin) VALUES ('Eduard',12345, TRUE);
INSERT INTO users(login, userpassword, isAdmin) VALUES ('Shimon','j1krd7', FALSE);
INSERT INTO users(login, userpassword, isAdmin) VALUES ('Andrey','9ztnga', FALSE);
INSERT INTO users(login, userpassword, isAdmin) VALUES ('Bicalel','bao5lx', FALSE);
INSERT INTO users(login, userpassword, isAdmin) VALUES ('Avigail','vp9m0i', FALSE);
INSERT INTO users(login, userpassword, isAdmin) VALUES ('David','9g3ai0', FALSE);

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
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-428','10-02-2024 09:30:00', '10-02-2024 11:45:00', 4, 1, 800, 2);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-429','11-02-2023 13:30:00', '11-02-2024 18:00:00', 1, 4, 900, 2);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-433','15-02-2024 06:00:00', '15-02-2024 17:00:00', 4, 2, 3000, 1);
INSERT INTO flights(flightname, departureDate, arrivalDate, departureCountry_ID, arrivalCountry_ID, price, fk_plane_ID) VALUES ('AZ-434','15-02-2024 20:00:00', '16-02-2024 07:00:00', 2, 4, 2999, 1);
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
    id serial PRIMARY KEY,
    fk_flight_ID integer REFERENCES flights (flight_ID)  not NULL,
    fk_user_ID integer REFERENCES users (user_ID) not NULL,
    seatRow integer not NULL,
    seatColumn integer not NULL,
    isPayed boolean not NULL,
    passengerFirstName varchar not NULL,
    passengerSecondName varchar not NULL,
    passengerBirthDate date not NULL,
    passengerSex varchar not NULL,
    passengerPassport integer not NULL,
    CONSTRAINT ticketToFlight FOREIGN KEY (fk_flight_ID)  REFERENCES flights (flight_ID) ON DELETE CASCADE
    );
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (1,  2, 10, 1, TRUE, 'Shimon', 'Roitman', '11-01-2002', 'M', 34678912);
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (2,  2, 8, 2, TRUE, 'Shimon', 'Roitman', '11-01-2002', 'M', 34678912);
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (1,  3, 7, 5, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M', 34666999);
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (4,  3, 15, 4, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M', 34666999);
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (5,  3, 20, 2, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M', 34666999);
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (6,  3, 20, 2, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M', 34666999);
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (1,  4, 9, 5, TRUE, 'Bizalel', 'Basin', '08-03-2001', 'M', 34777777);
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (8,  4, 6, 1, TRUE, 'Bizalel', 'Basin', '08-03-2001', 'M', 34777777);
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (1,  5, 5, 3, TRUE, 'Avigail', 'Jacobson', '08-07-1999', 'F', 34234567);
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (10, 5, 6, 6, TRUE, 'Avigail', 'Jacobson', '08-07-1999', 'F', 34234567);
INSERT INTO tickets(fk_flight_ID, fk_user_ID, seatRow, seatColumn, isPayed, passengerFirstName, passengerSecondName, passengerBirthDate, passengerSex, passengerPassport) VALUES (1,  6, 6, 5, FALSE, 'David', 'Mikhelson', '11-09-1990', 'M', 34456789);

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