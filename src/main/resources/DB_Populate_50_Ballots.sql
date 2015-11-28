-- ballots

INSERT INTO ballot (id, season_id, week_index, user_id, comment, submit_date, date_created, last_updated)
VALUES (1, 1, 1, 1, 'This is the first comment', now(), now(), now());
INSERT INTO ballot (id, season_id, week_index, user_id, comment, submit_date, date_created, last_updated)
VALUES (2, 1, 1, 2, 'This is the second comment', now(), now(), now());

ALTER SEQUENCE ballot_id_seq RESTART WITH 9999;

-- ballotVotes

INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (1, 1, 1, 5, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (2, 1, 21, 5, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (3, 1, 41, 4, now(), now());

INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (11, 2, 1, 5, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (12, 2, 21, 3, now(), now());
INSERT INTO ballot_vote (id, ballot_id, episode_id, score, date_created, last_updated)
VALUES (13, 2, 41, 2, now(), now());

ALTER SEQUENCE ballot_vote_id_seq RESTART WITH 9999;