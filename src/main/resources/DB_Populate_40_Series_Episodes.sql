-- series

INSERT INTO series (id, season_id, title, episode_count, start_date, end_date, date_created, last_updated)
VALUES (1, 1, 'Asterisk', 12, '2015-10-10 00:00:00', '2015-12-26 00:00:00', now(), now());
INSERT INTO series (id, season_id, title, episode_count, start_date, end_date, date_created, last_updated)
VALUES (2, 1, 'Rakudai', 12, '2015-10-11 00:00:00', '2015-12-27 00:00:00', now(), now());
INSERT INTO series (id, season_id, title, episode_count, start_date, end_date, date_created, last_updated)
VALUES (3, 1, 'One-Punch Man', 11, '2015-10-12 00:00:00', '2015-12-21 00:00:00', now(), now());
INSERT INTO series (id, season_id, title, episode_count, start_date, end_date, date_created, last_updated)
VALUES (4, 1, 'K: Return of Kings', 10, '2015-10-18 00:00:00', '2015-12-20 00:00:00', now(), now());
INSERT INTO series (id, season_id, title, episode_count, start_date, end_date, date_created, last_updated)
VALUES (10, 2, 'Prison School', 13, '2015-10-10 00:00:00', '2016-1-3 00:00:00', now(), now());
INSERT INTO series (id, season_id, title, episode_count, start_date, end_date, date_created, last_updated)
VALUES (11, 2, 'Gakkou Gurashi', 13, '2015-10-10 00:00:00', '2016-1-3 00:00:00', now(), now());
INSERT INTO series (id, season_id, title, episode_count, start_date, end_date, date_created, last_updated)
VALUES (12, 2, 'Monster Musume', 12, '2015-10-15 00:00:00', '2015-12-29 00:00:00', now(), now());

-- episodes

-- Asterisk
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (1, 1, 1, 1, 1, 'Episode1', '2015-10-10 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (2, 1, 2, 1, 2, 'Episode2', '2015-10-17 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (3, 1, 3, 1, 3, 'Episode3', '2015-10-24 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (4, 1, 4, 1, 4, 'Episode4', '2015-10-31 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (5, 1, 5, 1, 5, '', '2015-11-7 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (6, 1, 6, 1, 6, '', '2015-11-14 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (7, 1, 7, 1, 7, '', '2015-11-21 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (8, 1, 8, 1, 8, '', '2015-11-28 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (9, 1, 9, 1, 9, '', '2015-12-5 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (10, 1, 10, 1, 10, '', '2015-12-12 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (11, 1, 11, 1, 11, '', '2015-12-19 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (12, 1, 12, 1, 12, '', '2015-12-26 00:00:00', now(), now());

-- Rakudai
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (21, 2, 1, 1, 1, '', '2015-10-11 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (22, 2, 2, 1, 2, '', '2015-10-18 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (23, 2, 3, 1, 3, '', '2015-10-25 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (24, 2, 4, 1, 4, '', '2015-11-1 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (25, 2, 5, 1, 5, '', '2015-11-8 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (26, 2, 6, 1, 6, '', '2015-11-15 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (27, 2, 7, 1, 7, '', '2015-11-22 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (28, 2, 8, 1, 8, '', '2015-11-29 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (29, 2, 9, 1, 9, '', '2015-12-6 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (30, 2, 10, 1, 10, '', '2015-12-13 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (31, 2, 11, 1, 11, '', '2015-12-20 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (32, 2, 12, 1, 12, '', '2015-12-27 00:00:00', now(), now());

-- One-Punch Man
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (41, 3, 1, 1, 1, '', '2015-10-12 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (42, 3, 2, 1, 2, '', '2015-10-19 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (43, 3, 3, 1, 3, '', '2015-10-26 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (44, 3, 4, 1, 4, '', '2015-11-2 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (45, 3, 5, 1, 5, '', '2015-11-9 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (46, 3, 6, 1, 6, '', '2015-11-16 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (47, 3, 7, 1, 7, '', '2015-11-23 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (48, 3, 8, 1, 8, '', '2015-11-30 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (49, 3, 9, 1, 9, '', '2015-12-7 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (40, 3, 10, 1, 10, '', '2015-12-14 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (51, 3, 11, 1, 11, '', '2015-12-21 00:00:00', now(), now());

-- K: Return of Kings
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (61, 4, 1, 1, 2, '', '2015-10-18 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (62, 4, 2, 1, 3, '', '2015-10-25 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (63, 4, 3, 1, 4, '', '2015-11-1 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (64, 4, 4, 1, 5, '', '2015-11-8 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (65, 4, 5, 1, 6, '', '2015-11-15 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (66, 4, 6, 1, 7, '', '2015-11-22 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (67, 4, 7, 1, 8, '', '2015-11-29 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (68, 4, 8, 1, 9, '', '2015-12-6 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (69, 4, 9, 1, 10, '', '2015-12-13 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (70, 4, 10, 1, 11, '', '2015-12-20 00:00:00', now(), now());

-- Prison School
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (81, 10, 1, 1, 1, '', '2015-10-10 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (82, 10, 2, 1, 2, '', '2015-10-17 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (83, 10, 3, 1, 3, '', '2015-10-24 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (84, 10, 4, 1, 4, '', '2015-11-1 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (85, 10, 5, 1, 5, '', '2015-11-8 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (86, 10, 6, 1, 6, '', '2015-11-15 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (87, 10, 7, 1, 7, '', '2015-11-22 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (88, 10, 8, 1, 8, '', '2015-11-29 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (89, 10, 9, 1, 9, '', '2015-12-6 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (90, 10, 10, 1, 10, '', '2015-12-13 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (91, 10, 11, 1, 11, '', '2015-12-20 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (92, 10, 12, 1, 12, '', '2015-12-27 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (93, 10, 13, 1, 13, '', '2016-1-3 00:00:00', now(), now());

-- Gakkou Gurashi
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (101, 11, 1, 1, 1, '', '2015-10-10 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (102, 11, 2, 1, 2, '', '2015-10-17 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (103, 11, 3, 1, 3, '', '2015-10-24 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (104, 11, 4, 1, 4, '', '2015-11-1 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (105, 11, 5, 1, 5, '', '2015-11-8 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (106, 11, 6, 1, 6, '', '2015-11-15 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (107, 11, 7, 1, 7, '', '2015-11-22 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (108, 11, 8, 1, 8, '', '2015-11-29 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (109, 11, 9, 1, 9, '', '2015-12-6 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (110, 11, 10, 1, 10, '', '2015-12-13 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (111, 11, 11, 1, 11, '', '2015-12-20 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (112, 11, 12, 1, 12, '', '2015-12-27 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (113, 11, 13, 1, 13, '', '2016-1-3 00:00:00', now(), now());

-- Monster Musume
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (121, 12, 1, 1, 1, '', '2015-10-15 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (122, 12, 2, 1, 2, '', '2015-10-22 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (123, 12, 3, 1, 3, '', '2015-10-29 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (124, 12, 4, 1, 4, '', '2015-11-5 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (125, 12, 5, 1, 5, '', '2015-11-12 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (126, 12, 6, 1, 6, '', '2015-11-19 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (127, 12, 7, 1, 7, '', '2015-11-26 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (128, 12, 8, 1, 8, '', '2015-12-3 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (129, 12, 9, 1, 9, '', '2015-12-10 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (130, 12, 10, 1, 10, '', '2015-12-17 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (131, 12, 11, 1, 11, '', '2015-12-22 00:00:00', now(), now());
INSERT INTO episode (id, series_id, episode_index, season_id, week_index, title, air_date, date_created, last_updated)
VALUES (132, 12, 12, 1, 12, '', '2015-12-29 00:00:00', now(), now());