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

drop table reserva;
drop table ticket;

drop sequence seq_id_reserva;
drop sequence seq_id_ticket;

drop procedure prc_ins_user;
drop procedure prc_upd_user;

--=================== Objects ===================== 
PROMPT ==========================
PROMPT OBJECTS 
PROMPT ==========================
--==================== Tables ===================== 
create table users (id number not null, username varchar2(40),
                    password varchar2(40), name varchar2(25),
                    last_name varchar2(40), email varchar2(30),
                    address varchar2(200), phone varchar2(15),
                    is_admin varchar2(1)) tablespace system;

create table airplane_types (id varchar2(45), year number, model varchar2(20),
                            brand varchar2(20), passengers_quantity number,
                            rows_number number, columns_number number) tablespace system;

create table airplanes (id varchar2(20), airplane_type varchar2(45)) tablespace system;

create table countries (id varchar2(5), name varchar2(20)) tablespace system;
create table cities (id varchar2(5), name varchar2(20), country varchar2(5)) tablespace system;

create table schedules (id number not null, day varchar2(30),
                        departure_time date, arrival_time date) tablespace system;


create table routes (id varchar2(20), duration date, origin varchar2(5), destination varchar2(5),
                     airplane varchar2(20), schedule number ) tablespace system;

create table ticket(id number not null, fila number, col number, ruta varchar2(20)) tablespace system;

create table reserva(id number not null, ticket number not null, userid number) tablespace system; 

--==================== Sequences =====================                  
create sequence seq_id_users start with 1 increment by 1 cache 2;
create sequence seq_id_airplanes start with 1 increment by 1 cache 2;
create sequence seq_id_schedules start with 1 increment by 1 cache 2;
create sequence seq_id_reserva start with 1 increment by 1 cache 2;
create sequence seq_id_ticket start with 1 increment by 1 cache 2;


--==================== PKs =====================    
alter table users add constraint users_pk primary key(id) using index tablespace system;  
alter table airplane_types add constraint airplane_types_pk primary key(id) using index tablespace system;
alter table airplanes add constraint airplane_pk primary key(id) using index tablespace system;
alter table countries add constraint countries_pk primary key(id) using index tablespace system;
alter table cities add constraint cities_pk primary key(id) using index tablespace system;
alter table schedules add constraint schedules_pk primary key(id) using index tablespace system;
alter table routes add constraint routes_pk primary key(id) using index tablespace system;
alter table ticket add constraint ticket_pk primary key(id) using index tablespace system;
alter table reserva add constraint reserva_pk primary key(id, ticket) using index tablespace system;

--==================== FKs =====================
alter table airplanes add constraint type_fk foreign key (airplane_type) references airplane_types;  
alter table cities add constraint country_fk foreign key (country) references countries;
alter table routes add constraint origin_fk foreign key (origin) references cities;
alter table routes add constraint destination_fk foreign key (destination) references cities;
alter table routes add constraint airplane_fk foreign key (airplane) references airplanes;
alter table routes add constraint schedule_fk foreign key (schedule) references schedules;
alter table reserva add constraint ticket_fk foreign key (ticket) references ticket;
alter table ticket add constraint route_fk foreign key (ruta) references routes;


--==================== Inserts =====================    

insert into users values (123456789, 'kike00', 'chico500', 'Enrique',
                        'Mendez Cabezas', 'kike@gmail.com', 'Arriba de la UNA',
                        '9090-0909', 1);

insert into airplane_types values ('Super airplane', 2000, 'Model 0',
                                  'Brand 0', 330, 6, 55);

insert into countries values ('CR', 'Costa Rica');
insert into cities values ('SJO', 'San Jose', 'CR');

commit;

create or replace procedure prc_ins_user(Pid in number, PUser in varchar2, Ppassword in varchar2,
Pname in varchar2, Papellido in varchar2, Pemail in varchar2, Pdireccion in varchar2,
Pnumber in varchar2, Padmin in varchar2)is
begin
  insert into users (id, username, password, name, last_name, email, address, phone, is_admin)
  values (Pid, PUser, Ppassword, Pname, Papellido, Pemail, Pdireccion, Pnumber, Padmin);
  commit;
  exception
--UK o PK
    when dup_val_on_index then
    null;
end prc_ins_user;
/
show error

-- create or replace procedure prc_upd_user(Pid in number, PUser in varchar2, Ppassword in varchar2,
-- Pname in varchar2, Papellido in varchar2, Pemail in varchar2, Pdireccion in varchar2,
-- Pnumber in varchar2, Padmin in varchar2)
-- begin
-- update users
--    set 
--     id = Pid,
--     username = PUser,
--     password = Ppassword,
--     name = Pname,
--     last_name = Papellido,
--     email = Pemail,
--     address = Pdireccion,
--     phone = Pnumber,
--     is_admin = Padmin
--  where 
--  id = Pid;
-- end prc_upd_user;
-- /
-- show error


