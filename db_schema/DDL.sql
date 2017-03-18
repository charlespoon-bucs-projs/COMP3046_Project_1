CREATE TABLE Customer
(
    UID INTEGER PRIMARY KEY NOT NULL,
    Name TEXT NOT NULL,
    Salutation TEXT NOT NULL,
    Username TEXT NOT NULL,
    Mobile INTEGER NOT NULL,
    Email TEXT NOT NULL,
    Password TEXT NOT NULL,
    Birthday TEXT NOT NULL
);
CREATE TABLE Movie
(
    MID INTEGER PRIMARY KEY NOT NULL,
    Movie_name TEXT NOT NULL,
    Movie_type TEXT NOT NULL,
    Movie_date TEXT NOT NULL,
    Movie_class TEXT NOT NULL,
    Movie_lang TEXT NOT NULL,
    Movie_length INTEGER NOT NULL,
    Movie_director TEXT NOT NULL,
    Movie_cast TEXT NOT NULL,
    Movie_location TEXT NOT NULL,
    Movie_price INTEGER NOT NULL,
    Movie_startHour INTEGER,
    Movie_startMinute INTEGER
);
CREATE TABLE Staff
(
    SID INTEGER PRIMARY KEY NOT NULL,
    Name TEXT NOT NULL,
    Username TEXT NOT NULL,
    Password TEXT NOT NULL
);
CREATE TABLE Transactions
(
    TID INTEGER PRIMARY KEY NOT NULL,
    CustomerId INTEGER NOT NULL,
    MovieId INTEGER NOT NULL,
    Seat TEXT NOT NULL,
    Total INTEGER NOT NULL,
    NumberOfTickets INTEGER NOT NULL,
    IsCancelled INTEGER NOT NULL,
    FOREIGN KEY (CustomerId) REFERENCES Customer (UID) DEFERRABLE INITIALLY DEFERRED,
    FOREIGN KEY (MovieId) REFERENCES Movie (MID) DEFERRABLE INITIALLY DEFERRED
);