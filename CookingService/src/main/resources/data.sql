INSERT INTO user_roles (name)
VALUES ('ADMIN')
on conflict do nothing;
INSERT INTO user_roles (name)
VALUES ('USER')
on conflict do nothing;
INSERT INTO user_roles (name)
VALUES ('VIP')
on conflict do nothing;

INSERT INTO users(name, password) -- login and password = 123
values ('123', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3')
ON CONFLICT DO NOTHING;
INSERT INTO user_usersrole(user_id, roles_id)
SELECT u.id, r.id
FROM users u,
     user_roles r
WHERE u.name = '123'
  AND r.name IN ('USER', 'ADMIN')
ON CONFLICT DO NOTHING;




