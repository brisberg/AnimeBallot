-- seasons

INSERT INTO season (id, title, year, quarter, start_date, end_date, date_created, last_updated)
VALUES (1, 'Fall 2015', 2015, 'fall', '2015-10-10 00:00:00', '2016-01-09 00:00:00', now(), now());
INSERT INTO season (id, title, year, quarter, start_date, end_date, date_created, last_updated)
VALUES (2, 'Summer 2015', 2015, 'summer', '2015-07-02 00:00:00', '2015-10-05 00:00:00', now(), now());

ALTER SEQUENCE season_id_seq RESTART WITH 9999;

-- -- weeks
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (1, 1, 1, '2015-10-10 00:00:00', '2015-10-17 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (2, 1, 2, '2015-10-17 00:00:00', '2015-10-24 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (3, 1, 3, '2015-10-24 00:00:00', '2015-10-31 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (4, 1, 4, '2015-10-31 00:00:00', '2015-11-7 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (5, 1, 5, '2015-11-7 00:00:00', '2015-11-14 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (6, 1, 6, '2015-11-14 00:00:00', '2015-11-21 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (7, 1, 7, '2015-11-21 00:00:00', '2015-11-28 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (8, 1, 8, '2015-11-28 00:00:00', '2015-12-5 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (9, 1, 9, '2015-12-5 00:00:00', '2015-12-12 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (10, 1, 10, '2015-12-12 00:00:00', '2015-12-19 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (11, 1, 11, '2015-12-19 00:00:00', '2015-12-26 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (12, 1, 12, '2015-12-26 00:00:00', '2016-1-2 00:00:00', now(), now());
-- INSERT INTO week (id, season_id, index, start_date, end_date, date_created, last_updated)
-- VALUES (13, 1, 13, '2016-1-2 00:00:00', '2016-1-8 00:00:00', now(), now());