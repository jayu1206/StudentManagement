
CREATE TABLE IF NOT EXISTS `student` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`groupid`	INTEGER NOT NULL,
	`studFirstName`	TEXT NOT NULL,
	`grade`	INTEGER,
	`dob`	TEXT,
	`startDt`	TEXT,
	`teacher`	TEXT,
	`studLastName`	TEXT,
	`age`	TEXT,
	FOREIGN KEY(`groupid`) REFERENCES `groups`(`groupId`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `rate` (
	`rateId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`studId`	INTEGER NOT NULL,
	`date`	TEXT NOT NULL,
	`text`	REAL NOT NULL,
	`time`	INTEGER NOT NULL,
	`cwpm`	INTEGER NOT NULL,
	`errors`	INTEGER NOT NULL,
	`week`	INTEGER,
	FOREIGN KEY(`studId`) REFERENCES `student`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `groups` (
	`groupId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`groupName`	TEXT NOT NULL,
	`startDate`	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS `decoding` (
	`decoId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`studId`	INTEGER NOT NULL,
	`week`	INTEGER NOT NULL,
	`date`	TEXT NOT NULL,
	`book`	INTEGER NOT NULL,
	`lesson`	INTEGER NOT NULL,
	`form`	INTEGER NOT NULL,
	`score`	INTEGER NOT NULL,
	FOREIGN KEY(`studId`) REFERENCES `student`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `admin` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`userid`	TEXT NOT NULL,
	`password`	TEXT NOT NULL,
	`firstname`	TEXT NOT NULL,
	`lastname`	TEXT NOT NULL,
	`email`	TEXT
);

