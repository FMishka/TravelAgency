DROP TABLE flights CASCADE;
DROP TABLE planes CASCADE;
DROP TABLE users CASCADE;
DROP TABLE countries CASCADE;
DROP TABLE paymentData CASCADE;
DROP TABLE tickets CASCADE;

CREATE TABLE IF NOT EXISTS countries
(
    country_ID integer PRIMARY KEY,
    countryName varchar not NULL
);
INSERT INTO countries VALUES (1, 'Russia');
INSERT INTO countries VALUES (2, 'USA');
INSERT INTO countries VALUES (3, 'France');
INSERT INTO countries VALUES (4, 'Israel');
INSERT INTO countries VALUES (5, 'Italy');
INSERT INTO countries VALUES (6, 'Germany');
INSERT INTO countries VALUES (7, 'Great Britain');
INSERT INTO countries VALUES (8, 'Switzerland');
INSERT INTO countries VALUES (9, 'Spain');
INSERT INTO countries VALUES (10, 'Hungary');

CREATE TABLE IF NOT EXISTS planes
(
    plane_ID integer PRIMARY KEY,
    planeModel varchar not NULL,
    rowsNumber integer not NULL,
    columnsNumber integer not NULL,
    layoutType varchar not NULL
);
INSERT INTO planes VALUES (1001, 'A-320',30, 6, 0);
INSERT INTO planes VALUES (1002, 'A-321',30, 6, 0);
INSERT INTO planes VALUES (1003, 'Boeing737-800',30, 6, 0);
INSERT INTO planes VALUES (1004, 'A-321',30, 6, 0);
INSERT INTO planes VALUES (1005, 'Boeing777-200',44, 8, 0);

CREATE TABLE IF NOT EXISTS users
(
    user_ID integer PRIMARY KEY,
    login varchar not NULL,
    userpassword varchar not NULL,
    isAdmin boolean not NULL,
    passport integer not NULL
);

INSERT INTO users VALUES (0, 'admin','', TRUE, 0);
INSERT INTO users VALUES (0001, 'Eduard',12345, TRUE, 0000000);
INSERT INTO users VALUES (0002, 'Shimon','j1krd7', FALSE, 74596770);
INSERT INTO users VALUES (0003, 'Andrey','9ztnga', FALSE, 28091427);
INSERT INTO users VALUES (0004, 'Bicalel','bao5lx', FALSE, 38822156);
INSERT INTO users VALUES (0005, 'Avigail','vp9m0i', FALSE, 9473938);
INSERT INTO users VALUES (0006, 'David','9g3ai0', FALSE, 16864321);

CREATE TABLE IF NOT EXISTS flights
(
    flight_ID integer PRIMARY KEY,
    flightname varchar not NULL,
    departureDate date not NULL,
    arrivalDate date not NULL,
    departureCountry_ID integer REFERENCES countries (country_ID) not NULL,
    arrivalCountry_ID integer REFERENCES countries (country_ID) not NULL,
    price integer not NULL,
    fk_plane_ID integer REFERENCES planes (plane_ID) not NULL
    );
INSERT INTO flights VALUES (1, 'AZ-428','10-02-2024', '10-02-2024', 4, 1, 800, 1001);
INSERT INTO flights VALUES (2, 'AZ-429','11-02-2023', '11-02-2024', 1, 4, 900, 1001);
INSERT INTO flights VALUES (3, 'AZ-433','15-02-2024', '15-02-2024', 4, 2, 3000, 1005);
INSERT INTO flights VALUES (4, 'AZ-434','15-02-2024', '16-02-2024', 2, 4, 2999, 1005);
INSERT INTO flights VALUES (5, 'AZ-467','17-02-2024', '17-02-2024', 4, 3, 600, 1002);
INSERT INTO flights VALUES (6, 'AZ-468','17-02-2024', '17-02-2024', 3, 4, 600, 1002);
INSERT INTO flights VALUES (7, 'AZ-473','20-02-2024', '21-02-2024', 4, 5, 700, 1003);
INSERT INTO flights VALUES (8, 'AZ-474','20-02-2024', '21-02-2024', 5, 4, 699, 1003);
INSERT INTO flights VALUES (9, 'AZ-481','22-02-2024', '22-02-2024', 4, 6, 700, 1003);
INSERT INTO flights VALUES (10, 'AZ-482','23-02-2024', '23-02-2024', 6, 4, 700, 1003);
INSERT INTO flights VALUES (11, 'AZ-491','27-02-2024', '27-02-2024', 4, 7, 600, 1001);
INSERT INTO flights VALUES (12, 'AZ-492','28-02-2024', '28-02-2024', 7, 4, 600, 1001);
INSERT INTO flights VALUES (13, 'AZ-401','01-03-2024', '01-03-2024', 4, 8, 650, 1004);
INSERT INTO flights VALUES (14, 'AZ-402','01-03-2024', '01-03-2024', 8, 4, 650, 1004);
INSERT INTO flights VALUES (15, 'AZ-407','05-03-2024', '05-03-2024', 4, 9, 830, 1002);
INSERT INTO flights VALUES (16, 'AZ-408','06-03-2024', '06-03-2024', 9, 4, 830, 1002);
INSERT INTO flights VALUES (17, 'AZ-411','09-03-2024', '09-03-2024', 4, 10, 1200, 1002);
INSERT INTO flights VALUES (18, 'AZ-412','10-03-2024', '10-03-2024',10, 4, 999, 1002);


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
    PRIMARY KEY (fk_flight_ID, fk_user_ID)
    );
INSERT INTO tickets VALUES (1, 0002, 10, 1, TRUE, 'Shimon', 'Roitman', '11-01-2002', 'M');
INSERT INTO tickets VALUES (2, 0002, 8, 2, TRUE, 'Shimon', 'Roitman', '11-01-2002', 'M');
INSERT INTO tickets VALUES (1, 0003, 7, 6, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M');
INSERT INTO tickets VALUES (4, 0003, 15, 4, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M');
INSERT INTO tickets VALUES (5, 0003, 20, 2, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M');
INSERT INTO tickets VALUES (6, 0003, 20, 2, TRUE, 'Andrey', 'Savvteev', '06-09-2000', 'M');
INSERT INTO tickets VALUES (1, 0004, 9, 5, TRUE, 'Bizalel', 'Basin', '08-03-2001', 'M');
INSERT INTO tickets VALUES (8, 0004, 6, 1, TRUE, 'Bizalel', 'Basin', '08-03-2001', 'M');
INSERT INTO tickets VALUES (1, 0005, 5, 3, TRUE, 'Avigail', 'Jacobson', '08-07-1999', 'F');
INSERT INTO tickets VALUES (10, 0005, 6, 6, TRUE, 'Avigail', 'Jacobson', '08-07-1999', 'F');
INSERT INTO tickets VALUES (1, 0006, 6, 6, FALSE, 'David', 'Mikhelson', '11-09-1990', 'M');


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

