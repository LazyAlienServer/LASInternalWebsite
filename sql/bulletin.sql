create table bulletin
(
    id int auto_increment
        primary key ,
    publisher text not null ,
    level int not null ,
    time text not null ,
    title text not null ,
    body longtext not null,
    sticky boolean not null
)
    collate = utf8mb3_bin