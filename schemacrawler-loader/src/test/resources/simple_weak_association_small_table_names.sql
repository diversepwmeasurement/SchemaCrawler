CREATE TABLE BOOK
(
  ID INTEGER NOT NULL,
  COL12 CHAR(25),
  COL13 VARCHAR(25),
  COL14 NUMERIC NOT NULL,
  COL15 VARCHAR(25),
  PRIMARY KEY (ID)
);

CREATE TABLE BOOKS
(
  ID INTEGER NOT NULL,
  COL12 CHAR(25),
  COL13 VARCHAR(25),
  COL14 NUMERIC NOT NULL,
  COL15 VARCHAR(25),
  PRIMARY KEY (ID)
);

CREATE TABLE AREF
(
  BOOK_ID INTEGER NOT NULL,
  COL22 CHAR(25),
  COL23 VARCHAR(25),
  COL24 NUMERIC NOT NULL,
  COL25 VARCHAR(25),
  PRIMARY KEY (BOOK_ID)
);
