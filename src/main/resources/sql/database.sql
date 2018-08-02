-- Table: Wagon
CREATE TABLE wagon (
  id             INT          NOT NULL PRIMARY KEY,
  wagon_type     ENUM('COMMON', 'COUCHETTE', 'COMPARTMENT', 'BUSINESS') NOT NULL,
  numberofpassengers  INT NOT NULL,
  amountofluggage     INT NOT NULL
)
  ENGINE = InnoDB;
  -- Data Insert to table: wagon
INSERT INTO wagon VALUE (1, 'COMMON', 23, 20);
INSERT INTO wagon VALUE (2, 'BUSINESS', 6, 5);
INSERT INTO wagon VALUE (3, 'COUCHETTE', 12, 15);
INSERT INTO wagon VALUE (4, 'COUCHETTE', 7, 5);
INSERT INTO wagon VALUE (5, 'COMPARTMENT', 16, 21);
INSERT INTO wagon VALUE (6, 'COMMON', 7, 5);


