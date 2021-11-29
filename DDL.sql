drop schema if exists PLANIT;
create schema if not exists PlanIt collate utf8mb4_0900_ai_ci;
use PlanIt;
create table if not exists PlanIt.Users
(
	id int auto_increment,
	username varchar(100) not null,
	email varchar(100) not null,
	password varchar(100) not null,
	constraint email_UNIQUE
		unique (email),
	constraint idUsers_UNIQUE
		unique (id)
);

create table if not exists PlanIt.Projects
(
	id int auto_increment,
	user_id varchar(45) not null,
	name varchar(100) not null,
	start datetime not null,
	finish datetime not null,
	budget int not null,
	constraint id_UNIQUE
		unique (id),
	constraint fkuser_id
		foreign key (id) references PlanIt.Users (id)
			on update cascade on delete cascade
);

alter table PlanIt.Projects
	add primary key (id);

create table if not exists PlanIt.Tasks
(
	id int auto_increment,
	project_id varchar(45) not null,
	name varchar(100) not null,
	start datetime not null,
	finish datetime not null,
	budget int not null,
	constraint id_UNIQUE
		unique (id),
	constraint fkproject_id
		foreign key (id) references PlanIt.Projects (id)
			on update cascade on delete cascade
);

alter table PlanIt.Tasks
	add primary key (id);

create table if not exists PlanIt.Subtasks
(
	id int auto_increment,
	task_id varchar(45) not null,
	name varchar(100) not null,
	start int not null,
	finish int not null,
	budget int not null,
	constraint id_UNIQUE
		unique (id),
	constraint fktask_id
		foreign key (id) references PlanIt.Tasks (id)
			on update cascade on delete cascade
);

create index fk_idx
	on PlanIt.Subtasks (task_id);

alter table PlanIt.Subtasks
	add primary key (id);

