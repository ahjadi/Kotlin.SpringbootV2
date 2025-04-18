-- SQL code used to create the local postgres@17 DB
create table users (
	id serial primary key,
	name varchar(255) not null,
	email varchar(255) not null unique
);

create table orders (
	id serial primary key,
	user_id bigint not null,
	restaurant varchar(255) not null,
	foreign key (user_id) references users(id) on delete cascade
);

create table items (
id serial primary key,
order_id bigint not null,
name varchar(255) not null,
quantity int check(quantity >= 0) not null,
price decimal(10,3) check(price >= 0) not null,
foreign key (order_id) references orders(id) on delete cascade
)