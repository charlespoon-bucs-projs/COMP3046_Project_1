BEGIN TRANSACTION;
CREATE TABLE "Transactions" (
	`TID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`StaffId`	INTEGER NOT NULL,
	`CustomerId`	INTEGER NOT NULL,
	`MovieId`	INTEGER NOT NULL,
	`Seat`	TEXT NOT NULL,
	`Total`	INTEGER NOT NULL,
	`NumberOfTickets`	INTEGER NOT NULL,
	`IsCancelled`	INTEGER NOT NULL,
	FOREIGN KEY(`StaffId`) REFERENCES `Staff`(`SID`),
	FOREIGN KEY(`CustomerId`) REFERENCES `Customer`(`UID`),
	FOREIGN KEY(`MovieId`) REFERENCES `Movie`(`MID`)
);
INSERT INTO `Transactions` VALUES (1,1,1,1,'A1,A2,A3',240,3,0);
INSERT INTO `Transactions` VALUES (2,1,1,2,'B2,C3
',200,2,1);
CREATE TABLE "Staff" (
	`SID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`Name`	TEXT NOT NULL,
	`Username`	TEXT NOT NULL,
	`Password`	TEXT NOT NULL
);
INSERT INTO `Staff` VALUES (1,'Watson','waston123','qazplm');
CREATE TABLE "Movie" (
	`MID`	INTEGER NOT NULL,
	`Movie_name`	TEXT NOT NULL,
	`Movie_type`	TEXT NOT NULL,
	`Movie_date`	TEXT NOT NULL,
	`Movie_class`	TEXT NOT NULL,
	`Movie_lang`	TEXT NOT NULL,
	`Movie_length`	INTEGER NOT NULL,
	`Movie_director`	TEXT NOT NULL,
	`Movie_cast`	TEXT NOT NULL,
	`Movie_location`	TEXT NOT NULL,
	`Movie_price`	INTEGER NOT NULL
);
INSERT INTO `Movie` VALUES (1,'KONG: SKULL ISLAND','Action / Adventure','09/03/2017','IIB','English with Chinese Subtitles',118,'Jordan Vogt-Roberts','	Tom Hiddleston , Samuel L. Jackson , Brie Larson , John Goodman , John C. Reilly','House A',80);
INSERT INTO `Movie` VALUES (2,'Logan','Drama','02/03/2017','III','English with Chinese Subtitles',137,'James Mangold','Hugh Jackman, Patrick Stewart','House B',100);
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
INSERT INTO `Customer` VALUES (1,'Sherlock','Mr','sherlock123',23800000,'pizza@hut.com','qwerty','01/01/1990');
CREATE UNIQUE INDEX table_name_UID_uindex
  ON "Customer" (UID);
CREATE UNIQUE INDEX index_Movie_1 ON Movie (MID)


;
COMMIT;
