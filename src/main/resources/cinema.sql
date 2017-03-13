BEGIN TRANSACTION;
CREATE TABLE "Transactions" (
	`TID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`StaffId`	INTEGER NOT NULL,
	`CustomerId`	INTEGER NOT NULL,
	`MovieId`	INTEGER NOT NULL,
	FOREIGN KEY(`StaffId`) REFERENCES `Staff`(`SID`),
	FOREIGN KEY(`CustomerId`) REFERENCES `Customer`(`UID`),
	FOREIGN KEY(`MovieId`) REFERENCES `Movie`(`MID`)
);
INSERT INTO `Transactions` (TID,StaffId,CustomerId,MovieId) VALUES (1,1,1,1);
INSERT INTO `Transactions` (TID,StaffId,CustomerId,MovieId) VALUES (2,1,1,2);
CREATE TABLE "Staff" (
	`SID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`Name`	TEXT NOT NULL,
	`Username`	TEXT NOT NULL,
	`Password`	TEXT NOT NULL
);
INSERT INTO `Staff` (SID,Name,Username,Password) VALUES (1,'Watson','waston123','qazplm');
CREATE TABLE Movie
(
  MID INTEGER NOT NULL,
  Movie_name TEXT NOT NULL,
  Movie_type TEXT NOT NULL,
  Movie_date TEXT NOT NULL,
  Movie_class TEXT NOT NULL,
  Movie_lang TEXT NOT NULL,
  Movie_length INTEGER NOT NULL,
  Movie_director TEXT NOT NULL,
  Movie_cast TEXT NOT NULL,
  Movie_location TEXT NOT NULL
);
INSERT INTO `Movie` (MID,Movie_name,Movie_type,Movie_date,Movie_class,Movie_lang,Movie_length,Movie_director,Movie_cast,Movie_location) VALUES (1,'KONG: SKULL ISLAND','Action / Adventure','09/03/2017','IIB','English with Chinese Subtitles',118,'Jordan Vogt-Roberts','	Tom Hiddleston , Samuel L. Jackson , Brie Larson , John Goodman , John C. Reilly','House A');
INSERT INTO `Movie` (MID,Movie_name,Movie_type,Movie_date,Movie_class,Movie_lang,Movie_length,Movie_director,Movie_cast,Movie_location) VALUES (2,'Logan','Drama','02/03/2017','III','English with Chinese Subtitles',137,'James Mangold','Hugh Jackman, Patrick Stewart','House B');
CREATE TABLE "Customer"
(
  UID        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  Name       TEXT                              NOT NULL,
  Salutation TEXT                              NOT NULL,
  Username   TEXT                              NOT NULL,
  Mobile     INTEGER                           NOT NULL,
  Email      TEXT                              NOT NULL,
  Password   TEXT                              NOT NULL,
  Birthday   TEXT                              NOT NULL
);
INSERT INTO `Customer` (UID,Name,Salutation,Username,Mobile,Email,Password,Birthday) VALUES (1,'Sherlock','Mr','sherlock123',23800000,'pizza@hut.com','qwerty','01/01/1990');
CREATE UNIQUE INDEX table_name_UID_uindex
  ON "Customer" (UID);
CREATE UNIQUE INDEX index_Movie_1 ON Movie (MID);
COMMIT;
