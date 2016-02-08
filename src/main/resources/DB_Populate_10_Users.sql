-- users

INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (1, 'bjones', 'Bob', 'Jones', 'bjones@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (2, 'brisberg', 'Brandon', 'Risberg', 'brisberg@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (3, 'jsmith', 'John', 'Smith', 'smith@gmail.com', '123456', now(), now());

INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (4, 'thill', 'Terry', 'Hill', 'thill@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (5, 'lpolk', 'Larry', 'Polk', 'lpolk@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (6, 'rwaters', 'Richard', 'Waters', 'rwaters@gmail.com', '123456', now(), now());

INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (7, 'janderson', 'Jon', 'Anderson', 'janderson@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (8, 'ashepard', 'Alan', 'Shepard', 'ashepard@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (9, 'jglenn', 'John', 'Glenn', 'jglenn@gmail.com', '123456', now(), now());

INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (10, 'kgarnett', 'Katrina', 'Garnett', 'kgarnett@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (11, 'dean', 'Dean', 'Anderson', 'dean@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (12, 'vivek', 'Vivek', 'Patil', 'vpatil@gmail.com', '123456', now(), now());

INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (13, 'danw', 'Dan', 'Wagner', 'danw@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (14, 'hthompson', 'Helen', 'Thompson', 'hthompson@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (15, 'susans', 'Susan', 'Smith', 'susans@gmail.com', '123456', now(), now());

ALTER SEQUENCE users_id_seq RESTART WITH 9999;