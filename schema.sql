CREATE TABLE flights
  ( id INTEGER PRIMARY KEY
  , flightID VARCHAR(5) NOT NULL
  , origin VARCHAR(64) NOT NULL
  , destination VARCHAR(64) NOT NULL
  , departure DATE NOT NULL
  , arrival DATE NOT NULL
  , ecoCapacity INTEGER NOT NULL
  , busCapacity INTEGER NOT NULL
  , airline VARCHAR(64) NOT NULL
  , ecoPrice NUMERIC CHECK(ecoPrice > 0)
  , busPrice NUMERIC CHECK(busPrice > 0)
  , UNIQUE(flightID)
  );
INSERT INTO flights(flightID, origin, destination, departure, arrival, ecoCapacity, busCapacity, airline, ecoPrice, busPrice) VALUES ('WW154', 'Reykjavik', 'London', '14-03-2018 07:00:00','14-03-2018 09:00:00', 180, 20, 'WOW air', 9000, 22000);
INSERT INTO flights(flightID, origin, destination, departure, arrival, ecoCapacity, busCapacity, airline, ecoPrice, busPrice) VALUES ('WW178', 'London', 'Reykjavik', '14-03-2018 15:00:00','14-03-2018 17:00:00', 180, 20, 'WOW air', 7000, 25000);

CREATE TABLE passengers
  ( id INTEGER PRIMARY KEY
  , firstName VARCHAR(64) NOT NULL
  , lastName VARCHAR(64) NOT NULL
  , gender CHARACTER NOT NULL
  , seat VARCHAR(3) NOT NULL
  , luggage BOOLEAN NOT NULL
  , class VARCHAR(64) NOT NULL
  , birthdate DATE NOT NULL
  , flightID VARCHAR(5) REFERENCES flights(flightID)
  , UNIQUE(flightID, seat)
  );

DROP TABLE IF EXISTS queries;
CREATE TABLE queries
  ( id INTEGER PRIMARY KEY
  , origin VARCHAR(64)
  , destination VARCHAR(64)
  , departureTime VARCHAR(64)
  , departureDate DATE
  , returnDate DATE
  , availableSeats INTEGER CHECK(availableSeats > 0)
  , seatingClass VARCHAR(64)
  , maxPrice INTEGER CHECK(maxPrice > 0)
  , searchDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE users
  ( username VARCHAR(32) PRIMARY KEY
  , password VARCHAR(32)
  );
INSERT INTO users(username, password) VALUES ('admin', '123');