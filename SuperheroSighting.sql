drop database if exists superheroSighting;
create database superheroSighting;

use superherosighting;

create table heroes(
id int(6) primary key auto_increment,
`name` varchar(30) not null,
`description` varchar(280) not null);

create table locations(
id int(10) primary key auto_increment,
`name` varchar(30) not null,
`description` varchar(280) not null,
address varchar(100) default 'unknown',
latitude decimal(20, 6),
longitude decimal(20, 6));

create table organizations(
id int(5) primary key auto_increment,
`name` varchar(140) not null,
`description` varchar(280) not null,
locationId int(10),
phone varchar(20),
email varchar(100),
FOREIGN KEY (locationId) REFERENCES locations(id));

create table sightings (
id int(6) primary key auto_increment,
sightingDate date not null,
`description` varchar(500) not null,
locationId int(10) not null,
FOREIGN KEY (locationId) REFERENCES locations(id));

create table powers (
id int(6) primary key auto_increment,
`name` varchar(30) not null,
`description` varchar(280) not null);

create table heroSuperpowers (
id int(6) primary key auto_increment,
heroId int(6) not null,
powerId int(6) not null,
foreign key (heroId) references heroes(id),
foreign key (powerId) references powers(id));

create table heroAtSighting (
id int(6) primary key auto_increment,
heroId int(6) not null,
sightingId int(6) not null,
foreign key (heroId) references heroes(id),
foreign key (sightingId) references sightings(id));

create table organizationMembership (
id int(6) primary key auto_increment,
heroId int(6) not null,
organizationId int(6) not null,
foreign key (heroId) references heroes(id),
foreign key (organizationId) references organizations(id));


drop database if exists superheroSightingTest;
create database superheroSightingTest;

use superherosightingTest;

create table heroes(
id int(6) primary key auto_increment,
`name` varchar(30) not null,
`description` varchar(280) not null);

create table locations(
id int(10) primary key auto_increment,
`name` varchar(30) not null,
`description` varchar(280) not null,
address varchar(100) default 'unknown',
latitude decimal(20, 6),
longitude decimal(20, 6));

create table organizations(
id int(5) primary key auto_increment,
`name` varchar(140) not null,
`description` varchar(280) not null,
locationId int(10),
phone varchar(20),
email varchar(100),
FOREIGN KEY (locationId) REFERENCES locations(id));

create table sightings (
id int(6) primary key auto_increment,
sightingDate date not null,
`description` varchar(500) not null,
locationId int(10) not null,
FOREIGN KEY (locationId) REFERENCES locations(id));

create table powers (
id int(6) primary key auto_increment,
`name` varchar(30) not null,
`description` varchar(280) not null);

create table heroSuperpowers (
id int(6) primary key auto_increment,
heroId int(6) not null,
powerId int(6) not null,
foreign key (heroId) references heroes(id),
foreign key (powerId) references powers(id));

create table heroAtSighting (
id int(6) primary key auto_increment,
heroId int(6) not null,
sightingId int(6) not null,
foreign key (heroId) references heroes(id),
foreign key (sightingId) references sightings(id));

create table organizationMembership (
id int(6) primary key auto_increment,
heroId int(6) not null,
organizationId int(6) not null,
foreign key (heroId) references heroes(id),
foreign key (organizationId) references organizations(id));
