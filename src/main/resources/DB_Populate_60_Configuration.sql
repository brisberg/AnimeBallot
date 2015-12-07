-- configuration

INSERT INTO configuration (id, week_start_time, current_season_id, current_week_index, date_created, last_updated)
VALUES (1, date('2015-11-02'), 1, 1, now(), now());

ALTER SEQUENCE configuration_id_seq RESTART WITH 9999;
