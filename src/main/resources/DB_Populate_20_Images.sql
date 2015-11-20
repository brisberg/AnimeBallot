-- images

INSERT INTO image (id, title, filename, path, width, height)
VALUES (1, 'Amex background', 'amex_c_100_100', 'site', 100, 100);

ALTER SEQUENCE image_id_seq RESTART WITH 9999;