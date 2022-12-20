create table projet12.user
(
    id         int auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    email      varchar(255) null,
    password   varchar(255) null,
    token      varchar(255) null,
    role       varchar(255) null
);

create table projet12.chateau
(
    id             int auto_increment
        primary key,
    name           varchar(255)  null,
    numero_adresse int           null,
    code_postal    int           null,
    adresse        varchar(255)  null,
    responsable_id int           null,
    description    varchar(255)  null,
    ville          varchar(255)  null,
    lat            varbinary(20) null,
    lng            varbinary(20) null,
    constraint FK_CHATEAU_ON_RESPONSABLE
        foreign key (responsable_id) references projet12.user (id)
);

create table projet12.commentaire
(
    id          int auto_increment
        primary key,
    commentaire varchar(255) null,
    created_at  datetime     null,
    chateau_id  int          null,
    user_id     int          null,
    constraint FK_COMMENTAIRE_ON_CHATEAU
        foreign key (chateau_id) references projet12.chateau (id),
    constraint FK_COMMENTAIRE_ON_USER
        foreign key (user_id) references projet12.user (id)
);

create table projet12.file
(
    id         int auto_increment
        primary key,
    name       varchar(255) null,
    type       varchar(255) null,
    data       longblob     null,
    chateau_id int          null,
    image      longtext     null,
    constraint file_id_uindex
        unique (id),
    constraint file_chateau_id_fk
        foreign key (chateau_id) references projet12.chateau (id)
);



