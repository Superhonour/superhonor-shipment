create table if not exists user (
id int(20) not null primary key auto_increment,
username varchar(100),
password varchar(100));

create table if not exists user_role (
id int(20) not null primary key auto_increment,
userId int(20),
roleCode varchar(100));