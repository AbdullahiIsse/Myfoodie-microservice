drop table if exists user_account cascade;

CREATE TABLE IF NOT EXISTS user_account
(
    user_id       varchar(255) NOT NULL PRIMARY KEY,
    username      varchar(255) NOT NULL,
    email         varchar(255) NOT NULL,
    last_seen     timestamp    NOT NULL
);
