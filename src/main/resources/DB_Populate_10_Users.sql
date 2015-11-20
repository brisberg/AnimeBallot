-- users

INSERT INTO users (id, firstName, lastName, email, password, date_created, last_updated)
VALUES (1, 'Bob', 'Jones', 'bjones@gmail.com', '123456', now(), now());
INSERT INTO users (id, firstName, lastName, email, password, date_created, last_updated)
VALUES (2, 'Brandon', 'Risberg', 'brisberg@gmail.com', '123456', now(), now());

ALTER SEQUENCE users_id_seq RESTART WITH 9999;