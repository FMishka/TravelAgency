DROP TABLE flights CASCADE;
DROP TABLE planes CASCADE;
DROP TABLE users CASCADE;
DROP TABLE countries CASCADE;
DROP TABLE paymentData CASCADE;
DROP TABLE tickets CASCADE;

CREATE TABLE countries
( 
  country_ID integer PRIMARY KEY,
  countryName varchar not NULL
);

CREATE TABLE planes 
(
  plane_ID integer PRIMARY KEY,
  planeModel varchar not NULL,
  rowsNumber integer not NULL,
  columnsNumber integer not NULL,
  layoutType varchar not NULL
);

CREATE TABLE users
(
  user_ID integer PRIMARY KEY,
  login varchar not NULL,
  userpassword varchar not NULL,
  isAdmin boolean not NULL,
  passport integer not NULL
);

CREATE TABLE flights 
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

CREATE TABLE tickets
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

CREATE TABLE paymentData
(
  fk_user_ID integer REFERENCES users (user_ID) not NULL,
  cardNumber varchar not NULL,
  expireDate date not NULL,
  cardName varchar not NULL,
  cvv integer not NULL,
  PRIMARY KEY (fk_user_ID, cardNumber)
);