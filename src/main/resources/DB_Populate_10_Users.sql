-- users

INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (1, 'bjones', 'Bob', 'Jones', 'bjones@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (2, 'brisberg', 'Brandon', 'Risberg', 'brisberg@gmail.com', '123456', now(), now());
INSERT INTO users (id, name, firstName, lastName, email, password, date_created, last_updated)
VALUES (3, 'jsmith', 'John', 'Smith', 'smith@gmail.com', '123456', now(), now());

ALTER SEQUENCE users_id_seq RESTART WITH 9999;