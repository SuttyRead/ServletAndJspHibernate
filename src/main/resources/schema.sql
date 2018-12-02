--drop table Role;
--drop table User;

-- CREATE TABLE IF NOT EXISTS role(
--       id BIGINT AUTO_INCREMENT primary key,
--        name varchar(255));
-- CREATE TABLE IF NOT EXISTS user(id BIGINT primary key AUTO_INCREMENT, login varchar(255), password varchar(255),
--       email varchar(255), first_name varchar(255), last_name varchar(255), birthday DATE, role_id BIGINT);

-- INSERT INTO role (name) VALUES ("ADMIN");
-- INSERT INTO role (name) VALUES ("USER");
-- INSERT INTO user (login, password, role_id) VALUES ("admin", "admin", 1);
-- INSERT INTO user (login, password, role_id) VALUES ("user", "user", 2);
-- INSERT INTO "PUBLIC"."ROLE" ("NAME") VALUES ('ADMIN');
-- INSERT INTO "PUBLIC"."ROLE" ("NAME") VALUES ('USER');
-- INSERT INTO "PUBLIC"."USER" ("LOGIN", "PASSWORD", "ROLE_ID") VALUES ('admin', 'admin', 1);
-- INSERT INTO "PUBLIC"."USER" ("LOGIN", "PASSWORD", "ROLE_ID") VALUES ('user', 'user', 2);
-- INSERT INTO "PUBLIC"."USER" ("LOGIN", "PASSWORD", "EMAIL", "FIRST_NAME", "LAST_NAME", "BIRTHDAY", "ROLE_ID") VALUES ('admin', 'admin', 'NULL', 'NULL', 'NULL', 'NULL', NULL);
-- INSERT INTO "PUBLIC"."USER" ("LOGIN", "PASSWORD", "EMAIL", "FIRST_NAME", "LAST_NAME", "BIRTHDAY", "ROLE_ID") VALUES ('user', 'user', 'NULL', 'NULL', 'NULL', 'NULL', NULL);

-- INSERT INTO "PUBLIC"."USER" ("LOGIN", "PASSWORD", "EMAIL", "FIRST_NAME", "LAST_NAME", "BIRTHDAY", "ROLE_ID") VALUES ('admin', 'admin', 'admin', 'admin', 'admin', '2018-12-12', 1);
-- INSERT INTO "PUBLIC"."USER" ("LOGIN", "PASSWORD", "EMAIL", "FIRST_NAME", "LAST_NAME", "BIRTHDAY", "ROLE_ID") VALUES ('qwerty2', 'qwerty2', 'qwerty2', 'qwerty2', 'qwerty2', '2018-12-07', 2);
-- INSERT INTO "PUBLIC"."USER" ("LOGIN", "PASSWORD", "EMAIL", "FIRST_NAME", "LAST_NAME", "BIRTHDAY", "ROLE_ID") VALUES ('qwerty3', 'qwerty3', 'qwerty3', 'qwerty3', 'qwerty3', '2018-12-14', 2);
-- INSERT INTO "PUBLIC"."USER" ("LOGIN", "PASSWORD", "EMAIL", "FIRST_NAME", "LAST_NAME", "BIRTHDAY", "ROLE_ID") VALUES ('qwerty4', 'qwerty4', 'qwerty4', 'qwerty4', 'qwerty4', '2014-12-23', 2);
-- INSERT INTO "PUBLIC"."USER" ("LOGIN", "PASSWORD", "EMAIL", "FIRST_NAME", "LAST_NAME", "BIRTHDAY", "ROLE_ID") VALUES ('qwerty', 'qwerty', 'qwerty', 'qwerty', 'qwerty', '2018-12-01', 2);
-- INSERT INTO "PUBLIC"."USER" ("LOGIN", "PASSWORD", "EMAIL", "FIRST_NAME", "LAST_NAME", "BIRTHDAY", "ROLE_ID") VALUES ('qwerty5', 'qwerty5', 'qwerty5', 'qwerty5', 'qwerty5', '2018-12-07', 2);