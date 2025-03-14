INSERT INTO  movie(name,poster,created_at,updated_at) VALUES
                 ("Avengers End Game","Avengers End Game Poster",Now(),Now());



INSERT INTO city (name, created_at, updated_at) VALUES
                 ('C1', NOW(), NOW()),
                 ('C2', NOW(), NOW());

INSERT INTO theatre (name, address, city_id, created_at, updated_at) VALUES
                    ('T1', 'A1', 1, NOW(), NOW()),
                    ('T2', 'A2', 2, NOW(), NOW());

INSERT INTO auditorium (name, capacity, theatre_id, created_at, updated_at) VALUES
                       ('Screen 1', 200, 1, NOW(), NOW()),
                       ('Screen 2', 150, 1, NOW(), NOW()),
                       ('Screen 1', 180, 2, NOW(), NOW());


INSERT INTO seat (seat_number, row, column_value, seat_type, auditorium_id, created_at, updated_at) VALUES
                 ('A1', 1, 1, 0, 1, NOW(), NOW()),
                 ('A2', 1, 2, 0, 1, NOW(), NOW()),
                 ('B1', 2, 1, 1, 2, NOW(), NOW()),
                 ('B2', 2, 2, 1, 2, NOW(), NOW());


INSERT INTO movie (name, poster, created_at, updated_at) VALUES
                  ('Inception', 'inception.jpg', NOW(), NOW()),
                  ('Interstellar', 'interstellar.jpg', NOW(), NOW());

INSERT INTO shows (movie_id, start_time, end_time, auditorium_id, created_at, updated_at) VALUES
                  (2, '2025-03-15 14:00:00', '2025-03-15 16:30:00', 1, NOW(), NOW()),
                  (3, '2025-03-15 18:00:00', '2025-03-15 21:00:00', 2, NOW(), NOW());


INSERT INTO show_seat (show_id, seat_id, status, created_at, updated_at) VALUES
                      (3, 1, 0, NOW(), NOW()),
                      (3, 2, 1, NOW(), NOW()),
                      (4, 3, 0, NOW(), NOW()),
                      (4, 4, 2, NOW(), NOW());

INSERT INTO user (name, email, created_at, updated_at) VALUES
                                                           ('Vamsi', 'vamsi@gmail.com', NOW(), NOW()),
                                                           ('Raju', 'raju@gmail.com', NOW(), NOW());