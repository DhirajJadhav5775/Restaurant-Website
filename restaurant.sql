create database restaurant;
use restaurant;

create table OrderFood(
	selectItem varchar(50) not null,
    Quantity int(11) not null,
    YourName varchar(50) not null,
    YourEmail varchar(30) not null,
    YourPhone varchar(15) not null,
    Address varchar(40) not null
);
select * from OrderFood;
select * from BookTable;

create table BookTable(
	FullName varchar(40) not null,
    EmailAddress varchar(30) not null,
    PhoneNumber varchar(15) not null,
    BookDate date not null,
    BookTIme time not null,
    NumberOfGuests int not null,
    SpecialRequest varchar(100)
);