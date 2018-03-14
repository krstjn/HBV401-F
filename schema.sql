CREATE TABLE flights
  ( id INTEGER PRIMARY KEY
  , flightID VARCHAR(5) NOT NULL
  , fromAirport VARCHAR(64) NOT NULL
  , toAirport VARCHAR(64) NOT NULL
  , departure DATE NOT NULL
  , arrival DATE NOT NULL
  , ecoCapacity INTEGER NOT NULL
  , busCapacity INTEGER NOT NULL
  , airline VARCHAR(64) NOT NULL
  , UNIQUE(flightID)
  );
INSERT INTO flights(flightID, fromAirport, toAirport, departure, arrival, ecoCapacity, busCapacity, airline) VALUES ('WW154', 'Reykjavik', 'London', '14-03-2018 07:00:00','14-03-2018 09:00:00', 180, 20, 'WOW air');

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


CREATE TABLE queries
  ( id INTEGER PRIMARY KEY
  , flightID VARCHAR(5) NOT NULL
  , fromAirport VARCHAR(64) NOT NULL
  , toAirport VARCHAR(64) NOT NULL
  , departure DATE NOT NULL
  , arrival DATE NOT NULL
  , availableSeats INTEGER NOT NULL
  , searchDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , UNIQUE(flightID)
);

CREATE TABLE users
  ( username VARCHAR(32) PRIMARY KEY
  , password VARCHAR(32)
  );
INSERT INTO users(username, password) VALUES ('admin', '123');