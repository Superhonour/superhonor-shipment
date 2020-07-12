create table if not exists user (
id int(20) not null primary key auto_increment,
username varchar(100),
password varchar(100));

create table if not exists user_role (
id int(20) not null primary key auto_increment,
userId int(20),
roleCode varchar(100));

create table if not exists shipment (
id int(20) not null primary key auto_increment,
supplierid int(20),
total decimal (10, 4));

create table if not exists shipment_item (
id int(20) not null primary key auto_increment,
shipmentid int(20),
amount decimal(10, 4));