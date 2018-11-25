create database auth;
create table user (username varchar(25) NOT NULL, password varchar(255) NOT NULL, role varchar(25) NOT NULL, PRIMARY KEY (username));
insert into user(username, password, role) values ('username', 'password', 1);