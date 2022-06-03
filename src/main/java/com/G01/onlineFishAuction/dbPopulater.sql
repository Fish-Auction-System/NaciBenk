-- Note that this sql script is only for populating the local db machine.
CREATE
DATABASE fish;
USE fish;

CREATE TABLE customer
(
    address  VARCHAR(255) NOT NULL,
    mail     VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    surname  VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (username),
    UNIQUE (username),
    UNIQUE (mail)
);
CREATE TABLE cooperativehead
(
    mail     VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (username),
    UNIQUE (username)
);
CREATE TABLE cooperativemember
(
    mail     VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (username),
    UNIQUE (username),
    UNIQUE (mail)
);
CREATE TABLE fisherman
(
    mail     VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    iban     VARCHAR(255) NOT NULL,
    owner    VARCHAR(255) NOT NULL,
    PRIMARY KEY (username),
    UNIQUE (username),
    UNIQUE (mail),
    UNIQUE (iban)
);
CREATE TABLE code
(
    membercode VARCHAR(255) NOT NULL,
    PRIMARY KEY (membercode)
);
CREATE TABLE auction
(
    date  float        null,
    name  varchar(255) null,
    id    int auto_increment primary key,
    quota int          null
);
create table fish
(
    id          int auto_increment primary key,
    type        varchar(255) not null,
    status      varchar(255) not null,
    weight      float        not null,
    price       float        not null,
    fishermanid varchar(255) not null,
    auctionid   varchar(255) not null,
    constraint fish_auctionId_uindex unique (auctionid),
    constraint fish_id_uindex unique (id)
);

INSERT INTO `auction` (`date`, `name`, `id`, `quota`)
VALUES (123123, 'Benekli Ayhan Mezatı', '123123', 12),
       (1212310, 'Ananas', '3', 31),
       (123123, 'mezgit', 'wıodfsopıdjf', 12);
INSERT INTO `cooperativemember` (`mail`, `username`, `password`)
VALUES ('member234@email.com', 'Member_123', 'random_psw_234');
INSERT INTO `customer` (`address`, `mail`, `username`, `name`, `surname`, `password`)
VALUES ('almanya', 'mertkaraca1999@hotmail.com', 'bigibigo', 'bilgehan', 'ay', 'bibi'),
       ('Iyte Bilgisayar', 'canrollas@gmail.com', 'canrollas', 'Can', 'Rollas', '123can123'),
       ('randomAddress', 'random@gmail.com', 'randomUser', 'cagatay', 'iba', 'random_psw_123');
INSERT INTO `fisherman` (`mail`, `username`, `password`, `iban`, `owner`) VALUES
    ('randomFm@gmail.com', 'Ali_Kaptan', 'random_psw_567', '11111111111111111111111111', 'ali kuscu');

INSERT INTO cooperativehead
VALUES ('admin@gmail.com', 'Admin', 'lorem_ipsum');


