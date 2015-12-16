-- ballots

INSERT INTO ballot (id, season_id, week_index, user_id, comment, submit_date, date_created, last_updated)
VALUES (1, 1, 1, 1, 'User1 comment for week 1', now(), now(), now());
INSERT INTO ballot (id, season_id, week_index, user_id, comment, submit_date, date_created, last_updated)
VALUES (2, 1, 1, 3, 'User3 comment for week 1', now(), now(), now());

INSERT INTO ballot (id, season_id, week_index, user_id, comment, submit_date, date_created, last_updated)
VALUES (11, 1, 2, 1, 'User1 comment for week 2', now(), now(), now());
INSERT INTO ballot (id, season_id, week_index, user_id, comment, submit_date, date_created, last_updated)
VALUES (12, 1, 2, 3, 'User3 comment for week 2', now(), now(), now());

ALTER SEQUENCE ballot_id_seq RESTART WITH 9999;

-- ballotVotes

INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (1, 1, 1, 2, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (2, 1, 21, 5, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (3, 1, 41, 4, now(), now());

INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (11, 2, 1, 4, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (12, 2, 21, 3, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (13, 2, 41, 2, now(), now());

INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (21, 11, 1, 5, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (22, 11, 21, 2, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (23, 11, 41, 3, now(), now());

INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (31, 12, 1, 2, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (32, 12, 21, 2, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (33, 12, 41, 5, now(), now());

ALTER SEQUENCE ballot_vote_id_seq RESTART WITH 9999;
