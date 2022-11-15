create table if not exists feed
(
    follower  varchar(255) not null
        primary key,
    author    varchar(255),
    content   varchar(255),
    timestamp timestamp,
    title     varchar(255)
);

create table if not exists post
(
    id        uuid not null
        primary key,
    author    varchar(255),
    content   varchar(255),
    timestamp timestamp,
    title     varchar(255)
);

create table if not exists subscription
(
    author   varchar(255) not null,
    follower varchar(255) not null,
    primary key (author, follower)
);
