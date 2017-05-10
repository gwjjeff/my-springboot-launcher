drop table if exists city;
drop table if exists hotel;

create table city (id int primary key auto_increment, name varchar(255), state varchar(255), country varchar(255));
create table hotel (city int, name varchar(255), address varchar(255), zip varchar(255));

insert into city (name, state, country) values ('San Francisco', 'CA', 'US');
insert into hotel(city, name, address, zip) values (1, 'Conrad Treasury Place', 'William & George Streets', '4001')
