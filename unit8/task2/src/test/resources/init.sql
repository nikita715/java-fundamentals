CREATE TABLE Books (
  id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  UNIQUE (name)
);

INSERT INTO Books (name) VALUES ('The Selfish Gene');
INSERT INTO Books (name) VALUES ('The Blind Watchmaker');
INSERT INTO Books (name) VALUES ('The God Delusion');