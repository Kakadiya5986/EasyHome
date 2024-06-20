INSERT INTO app_user (id,email, first_name, last_name, app_user_role, enabled, locked, password)
VALUES
  (1001,'pavan@example.com', 'Pavan', 'Kumar', 'USER', TRUE, FALSE, 'pavan'),
  (1002,'admin@example.com', 'Admin', 'User', 'ADMIN', TRUE, FALSE, '{ENCODED_PASSWORD_2}');