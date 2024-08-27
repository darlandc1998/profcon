create table if not exists professionals(
	id serial not null primary key,
	name varchar(255) not null,
	role varchar(255) not null,
	birth date not null,
	deleted boolean not null default false,
	created_at timestamp not null default current_timestamp
);