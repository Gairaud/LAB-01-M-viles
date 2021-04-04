--===================== Drops ===================== 
PROMPT ==========================
PROMPT DROPS 
PROMPT ==========================
drop table users cascade constraints;
drop sequence seq_id_users;

drop table airplane_types cascade constraints;


drop table airplanes cascade constraints;
drop sequence seq_id_airplanes;

drop table countries cascade constraints;
drop table cities cascade constraints;

drop table schedules cascade constraints;
drop sequence seq_id_schedules;

drop table routes cascade constraints;


--=================== Objects ===================== 
PROMPT ==========================
PROMPT OBJECTS 
PROMPT ==========================
--==================== Tables ===================== 
create table users (id number not null, username varchar(40),
                    password varchar(40), name varchar(25),
                    last_name varchar(40), email varchar(30),
                    addres varchar(200), phone varchar(15),
                    is_admin varchar(1)) tablespace system;

create table airplane_types (id varchar(45), year number, model varchar(20),
                            brand varchar(20), passengers_quantity number,
                            rows_number number, columns_number number) tablespace system;

create table airplanes (id varchar(20), airplane_type varchar(45)) tablespace system;

create table countries (id varchar(5), name varchar(20)) tablespace system;
create table cities (id varchar(5), name varchar(20), country varchar(5)) tablespace system;

create table schedules (id number not null, day varchar(30),
                        departure_time date, arrival_time date) tablespace system;


create table routes (id varchar(20), duration date, origin varchar(5), destination varchar(5),
                     airplane varchar(20), schedule number ) tablespace system;


--==================== Sequences =====================                  
create sequence seq_id_users start with 1 increment by 1 cache 2;
create sequence seq_id_airplanes start with 1 increment by 1 cache 2;
create sequence seq_id_schedules start with 1 increment by 1 cache 2;


--==================== PKs =====================    
alter table users add constraint users_pk primary key(id) using index tablespace system;  
alter table airplane_types add constraint airplane_types_pk primary key(id) using index tablespace system;
alter table airplanes add constraint airplane_pk primary key(id) using index tablespace system;
alter table countries add constraint countries_pk primary key(id) using index tablespace system;
alter table cities add constraint cities_pk primary key(id) using index tablespace system;
alter table schedules add constraint schedules_pk primary key(id) using index tablespace system;
alter table routes add constraint routes_pk primary key(id) using index tablespace system;

--==================== FKs =====================
alter table airplanes add constraint type_fk foreign key (airplane_type) references airplane_types;  
alter table cities add constraint country_fk foreign key (country) references countries;
alter table routes add constraint origin_fk foreign key (origin) references cities;
alter table routes add constraint destination_fk foreign key (destination) references cities;
alter table routes add constraint airplane_fk foreign key (airplane) references airplanes;
alter table routes add constraint schedule_fk foreign key (schedule) references schedules;

--==================== Inserts =====================    

insert into users values (123456789, 'kike00', 'chico500', 'Enrique',
                        'Mendez Cabezas', 'kike@gmail.com', 'Arriba de la UNA',
                        '9090-0909', 1);

insert into airplane_types values ('Super airplane', 2000, 'Model 0',
                                  'Brand 0', 330, 6, 55);

insert into countries values ('CR', 'Costa Rica');
insert into cities values ('SJO', 'San Jose', 'CR');

commit;