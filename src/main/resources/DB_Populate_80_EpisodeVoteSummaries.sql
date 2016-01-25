-- Episode Vote Summaries

INSERT INTO episode_vote_summaries (id, season_id, week_index, series_id, episode_index, rank,
                                    score_raw, score_norm, percentage, change, date_created, last_updated)
VALUES (1, 1, 1, 1, 1, 1, 8, 2.6, 1.0, 0, now(), now());
INSERT INTO episode_vote_summaries (id, season_id, week_index, series_id, episode_index, rank,
                                    score_raw, score_norm, percentage, change, date_created, last_updated)
VALUES (2, 1, 1, 2, 1, 2, 6, 2.0, 1.0, 0, now(), now());
INSERT INTO episode_vote_summaries (id, season_id, week_index, series_id, episode_index, rank,
                                    score_raw, score_norm, percentage, change, date_created, last_updated)
VALUES (3, 1, 1, 3, 1, 3, 4, 1.3, 1.0, 0, now(), now());


INSERT INTO episode_vote_summaries (id, season_id, week_index, series_id, episode_index, rank,
                                    score_raw, score_norm, percentage, change, date_created, last_updated)
VALUES (11, 1, 2, 1, 2, 3, 2, 0.6, 1.0, -2, now(), now());
INSERT INTO episode_vote_summaries (id, season_id, week_index, series_id, episode_index, rank,
                                    score_raw, score_norm, percentage, change, date_created, last_updated)
VALUES (12, 1, 2, 2, 2, 1, 8, 2.6, 1.0, 1, now(), now());
INSERT INTO episode_vote_summaries (id, season_id, week_index, series_id, episode_index, rank,
                                    score_raw, score_norm, percentage, change, date_created, last_updated)
VALUES (13, 1, 2, 3, 2, 2, 6, 2.0, 1.0, 1, now(), now());


INSERT INTO episode_vote_summaries (id, season_id, week_index, series_id, episode_index, rank,
                                    score_raw, score_norm, percentage, change, date_created, last_updated)
VALUES (21, 1, 3, 1, 3, 3, 1, 0.3, 1.0, 0, now(), now());
INSERT INTO episode_vote_summaries (id, season_id, week_index, series_id, episode_index, rank,
                                    score_raw, score_norm, percentage, change, date_created, last_updated)
VALUES (22, 1, 3, 2, 3, 2, 7, 2.0, 1.0, -1, now(), now());
INSERT INTO episode_vote_summaries (id, season_id, week_index, series_id, episode_index, rank,
                                    score_raw, score_norm, percentage, change, date_created, last_updated)
VALUES (23, 1, 3, 3, 3, 1, 8, 2.2, 1.0, 1, now(), now());


ALTER SEQUENCE episode_vote_summaries_id_seq RESTART WITH 9999;
