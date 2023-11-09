CREATE DATABASE IF NOT EXISTS homework11;

create table if not exists Airport(
    id bigint auto_increment primary key,
    name varchar(255) not null
);

create table if not exists Flight(
    id bigint auto_increment primary key,
    departureLocation varchar(255) not null,
    destinationLocation varchar(255) not null,
    price int not null
);

create table if not exists AirportFlight(
    airport_id bigint not null,
    flight_id bigint not null,
    primary key (airport_id, flight_id),
    foreign key (airport_id) references Airport(id),
    foreign key (flight_id) references Flight(id)
);
