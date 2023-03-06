INSERT INTO user_roles (name) VALUES ('ADMIN') on conflict do nothing;
INSERT INTO user_roles (name) VALUES ('USER') on conflict do nothing;
INSERT INTO user_roles (name) VALUES ('VIP') on conflict do nothing;


