create table if not exists contacts(
	id serial not null primary key,
	id_professional int not null,
	name varchar(255) not null,
	contact varchar(255) not null,
	deleted boolean not null default false,
	created_at timestamp not null default current_timestamp,
	foreign key (id_professional) references professionals(id)
);