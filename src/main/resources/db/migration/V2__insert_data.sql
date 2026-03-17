INSERT INTO countries (name, continent) VALUES
                                            ('Macedonia', 'Europe'),
                                            ('Germany', 'Europe'),
                                            ('France', 'Europe'),
                                            ('Italy', 'Europe'),
                                            ('Spain', 'Europe');

INSERT INTO hosts (name, surname, country_id) VALUES
                                                  ('Marko', 'Markovski', 1),
                                                  ('Ana', 'Andonova', 1),
                                                  ('Hans', 'Mueller', 2),
                                                  ('Pierre', 'Dupont', 3),
                                                  ('Marco', 'Rossi', 4);

INSERT INTO accommodations (name, category, condition, host_id, num_rooms, is_rented) VALUES
                                                                                          ('Skopje Center Apartment', 'APARTMENT', 'GOOD', 1, 3, false),
                                                                                          ('Lake House Ohrid', 'HOUSE', 'GOOD', 2, 5, false),
                                                                                          ('Berlin Studio', 'FLAT', 'GOOD', 3, 1, true),
                                                                                          ('Paris Hotel Room', 'ROOM', 'GOOD', 4, 1, false),
                                                                                          ('Rome Motel', 'MOTEL', 'GOOD', 5, 2, false),
                                                                                          ('Skopje Hotel', 'HOTEL', 'BAD', 1, 50, false);