create table user
(
    id            int auto_increment
        primary key,
    time          mediumtext                       not null,
    userName      text collate utf8mb4_general_ci  not null,
    minecraftName text collate utf8mb4_general_ci  not null,
    userPassword  text collate utf8mb4_general_ci  not null,
    userSignature text collate utf8mb3_croatian_ci not null,
    userTag       text collate utf8mb4_general_ci  null,
    lastLogin     mediumtext                       null,
    whiteList     text                             null,
    online        int                              null,
    probation     text                             not null,
    administrator int                              not null,
    slat          int                              not null,
    qq            text                             not null,
    avatar        text                             null
)
    collate = utf8mb3_bin;

