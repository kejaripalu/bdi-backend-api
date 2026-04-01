INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO app_user (id, username, password, full_name, secure_id) 
VALUES (1, 'testuser', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 'Test Administrator', 'test-secure-id');
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
