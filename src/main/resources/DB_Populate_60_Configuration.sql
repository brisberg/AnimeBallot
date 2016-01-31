-- configuration

INSERT INTO configuration (id, week_start_day, week_start_hour, current_season_id,
                           current_week_index, date_created, last_updated)
VALUES (1, 5, 9, 1, 1, now(), now());

ALTER SEQUENCE configuration_id_seq RESTART WITH 9999;
