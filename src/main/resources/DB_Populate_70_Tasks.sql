-- tasks

INSERT INTO tasks (id, user_id, due_date, done, name, date_created, last_updated)
VALUES (10, 1, date('2015-12-02'), false, 'Vote for week 1', now(), now());

INSERT INTO tasks (id, user_id, due_date, done, name, date_created, last_updated)
VALUES (20, 2, date('2015-12-02'), true, 'Vote for week 1', now(), now());

INSERT INTO tasks (id, user_id, due_date, done, name, date_created, last_updated)
VALUES (21, 2, date('2015-12-31'), false, 'Onboard new reviewer', now(), now());

INSERT INTO tasks (id, user_id, due_date, done, name, date_created, last_updated)
VALUES (30, 3, date('2015-12-12'), false, 'Confirm new season of shows', now(), now());

INSERT INTO tasks (id, user_id, due_date, done, name, date_created, last_updated)
VALUES (31, 3, date('2015-12-12'), false, 'Correct the episode names for Rakudai season 2', now(), now());

ALTER SEQUENCE tasks_id_seq RESTART WITH 9999;
