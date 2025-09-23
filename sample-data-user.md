Berikut sample data user dengan masing-masing password `test123`:

```sql
INSERT INTO "app_user" ("id", "deleted", "full_name", "password", "secure_id", "username") VALUES
	(1, 'false', 'Ucup Topekox', '$2a$12$Fv5TWmhz0UDNStlPh.FHuuUCvc5ZnhZC1uxyS4o.14rMTppJjLEnW', '6f9daede-6f9f-4d9f-a560-d88fe0b73bce', 'ucup'),
	(2, 'false', 'Ade Laksono', '$2a$12$Fv5TWmhz0UDNStlPh.FHuuUCvc5ZnhZC1uxyS4o.14rMTppJjLEnW', 'a381a3c2-43a4-4035-9d56-b75fc7f0e8b5', 'ade'),
	(3, 'false', 'Vivin Lusiana', '$2a$12$Fv5TWmhz0UDNStlPh.FHuuUCvc5ZnhZC1uxyS4o.14rMTppJjLEnW', 'b3c0e7b8-abf9-4fb8-bcc8-208aa2abb561', 'vivin');


INSERT INTO "role" ("id", "deleted", "name") VALUES
	(1, 'false', 'SUPERADMIN'),
	(2, 'false', 'ADMIN'),
	(3, 'false', 'USER'),
	(4, 'false', 'GUEST');

INSERT INTO "user_role" ("user_id", "role_id") VALUES
	(1, 1),
	(2, 2),
	(3, 4);
```