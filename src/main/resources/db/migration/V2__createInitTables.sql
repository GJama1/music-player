create table if not exists mp_schema.artists (
    id varchar primary key,
    status varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    name varchar(255) not null,
    creation_date date,
    image varchar(255)
);

create table if not exists mp_schema.users (
    id varchar primary key,
    status varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    username varchar(255) not null,
    email varchar(255) not null
);

create table if not exists mp_schema.albums (
    id varchar primary key,
    status varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    name varchar(255) not null,
    release_date date,
    image varchar(255),
    artist_id varchar references artists(id)
);

create table if not exists mp_schema.genres (
    id varchar primary key,
    status varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    name varchar(255) not null
);

create table if not exists mp_schema.songs (
    id varchar primary key,
    status varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    name varchar(255) not null,
    releaseDate date,
    artist_id varchar references artists(id),
    album_id varchar references albums(id),
    genre_id varchar references genres(id)
);

create table if not exists mp_schema.user_songs (
    id varchar primary key,
    status varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    user_id varchar references users(id),
    song_id varchar references songs(id),
    is_favorite boolean not null
);