--===================== Drops ===================== 
PROMPT ==========================
PROMPT DROPS 
PROMPT ==========================
drop sequence seq_id_users;
drop sequence seq_id_airplanes;
drop sequence seq_id_schedules;
drop sequence seq_id_reservation;
drop sequence seq_id_ticket;
drop sequence seq_id_flights;


drop procedure prc_ins_user;
drop procedure prc_upd_user;

--
drop table users cascade constraints;
drop table airplane_types cascade constraints;
drop table airplanes cascade constraints;
drop table countries cascade constraints;
drop table cities cascade constraints;
drop table schedules cascade constraints;
drop table routes cascade constraints;
drop table reservation cascade constraints;
drop table ticket cascade constraints;
drop table flights cascade constraints;








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

create table schedules (id number not null,
                        departure_time date, arrival_time date) tablespace system;


create table routes (id varchar2(20), duration varchar2(25), origin varchar2(5), destination varchar2(5),
                     airplane varchar2(20), schedule number ) tablespace system;

create table ticket(id number not null, fila number, col number, reservation number) tablespace system;

create table reservation(id number not null, userid number, totalPrice number, seatQuantity number) tablespace system; 

create table flights(id number not null);

--==================== Sequences =====================                  
create sequence seq_id_users start with 1 increment by 1 cache 2;
create sequence seq_id_airplanes start with 1 increment by 1 cache 2;
create sequence seq_id_schedules start with 1 increment by 1 cache 2;
create sequence seq_id_reservation start with 1 increment by 1 cache 2;
create sequence seq_id_ticket start with 1 increment by 1 cache 2;
create sequence seq_id_flights start with 1 increment by 1 cache 2;


--==================== PKs =====================    
alter table users add constraint users_pk primary key(id) using index tablespace system;  
alter table airplane_types add constraint airplane_types_pk primary key(id) using index tablespace system;
alter table airplanes add constraint airplane_pk primary key(id) using index tablespace system;
alter table countries add constraint countries_pk primary key(id) using index tablespace system;
alter table cities add constraint cities_pk primary key(id) using index tablespace system;
alter table schedules add constraint schedules_pk primary key(id) using index tablespace system;
alter table routes add constraint routes_pk primary key(id) using index tablespace system;
alter table ticket add constraint ticket_pk primary key(id) using index tablespace system;
alter table reservation add constraint reservation_pk primary key(id) using index tablespace system;
alter table flights add constraint flights_pk primary key(id) using index tablespace system;

--==================== FKs =====================
alter table airplanes add constraint type_fk foreign key (airplane_type) references airplane_types;  
alter table cities add constraint country_fk foreign key (country) references countries;
alter table routes add constraint origin_fk foreign key (origin) references cities;
alter table routes add constraint destination_fk foreign key (destination) references cities;
alter table routes add constraint airplane_fk foreign key (airplane) references airplanes;
alter table routes add constraint schedule_fk foreign key (schedule) references schedules;
alter table ticket add constraint reservation_fk foreign key (reservation) references reservation;


--==================== Inserts =====================    

insert into users values (123456789, 'kike00', 'chico500', 'Enrique',
                        'Mendez Cabezas', 'kike@gmail.com', 'Arriba de la UNA',
                        '9090-0909', 1);

insert into airplane_types values ('Super airplane', 2000, 'Model 0',
                                  'Brand 0', 330, 6, 55);
insert into airplanes values ('Avion 1', 'Super airplane');

insert into countries values ('CR', 'Costa Rica');
insert into cities values ('SJO', 'San Jose', 'CR');

insert into countries values ('USA', 'Estados Unidos');
insert into cities values ('ATL', 'ATLANTA', 'USA');

insert into schedules values (seq_id_schedules.nextval, TO_DATE('2021/12/19', 'yyyy/mm/dd'), 
                                TO_DATE('2021/12/25', 'yyyy/mm/dd'));
insert into routes values ('SJO-ATL', '12 HORAS', 'SJO', 'ATL', 'Avion 1', 1);

insert into reservation values(seq_id_reservation.nextval, 123456789, 1000, 3);

insert into ticket values (seq_id_ticket.nextval, 5,7,1);
insert into ticket values (seq_id_ticket.nextval, 5,6,1);
insert into ticket values (seq_id_ticket.nextval, 5,5,1);



commit;

PROMPT          Procedimientos USER
PROMPT =====================================
create or replace procedure prc_ins_user(Pid in number, PUser in varchar2, Ppassword in varchar2,
Pname in varchar2, Papellido in varchar2, Pemail in varchar2, Pdireccion in varchar2,
Pnumber in varchar2, Padmin in varchar2) is 
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

create or replace procedure prc_upd_user(Pid in number, PUser in varchar2, Ppassword in varchar2,
Pname in varchar2, Papellido in varchar2, Pemail in varchar2, Pdireccion in varchar2,
Pnumber in varchar2, Padmin in varchar2) is
begin
update users
   set 
    id = Pid,
    username = PUser,
    password = Ppassword,
    name = Pname,
    last_name = Papellido,
    email = Pemail,
    address = Pdireccion,
    phone = Pnumber,
    is_admin = Padmin
 where 
 id = Pid;
end prc_upd_user;
/
show error


create or replace procedure prc_ins_aviontype(Pid in varchar2, Pyear in number, 
Pmodel in varchar2, Pbrand in varchar2, Ppassengers_quantity in number, 
Prows_number in number, Pcolumns_number in number) is 
begin
  insert into airplane_types (id, year, model, brand, passengers_quantity, rows_number, columns_number)
  values (Pid, Pyear, Pmodel, Pbrand, Ppassengers_quantity, Prows_number, Pcolumns_number);
  commit;
  exception
--UK o PK
    when dup_val_on_index then
    null;
end prc_ins_aviontype;
/
show error

create or replace procedure prc_upd_aviontype(Pid in varchar2, Pyear in number, 
Pmodel in varchar2, Pbrand in varchar2, Ppassengers_quantity in number, 
Prows_number in number, Pcolumns_number in number) is
begin
update airplane_types
   set 
    id = Pid ,
    year = Pyear ,
    model = Pmodel ,
    brand = Pbrand ,
    passengers_quantity = Ppassengers_quantity ,
    rows_number = Prows_number ,
    columns_number = Pcolumns_number
 where 
 id = Pid;
end prc_upd_aviontype;
/
show error

create or replace procedure prc_ins_avion(Pid in varchar2, Pairplane_type in varchar2) is 
begin
  insert into airplanes (id, airplane_type)
  values (Pid, Pairplane_type);
  commit;
  exception
--UK o PK
    when dup_val_on_index then
    null;
end prc_ins_avion;
/
show error

create or replace procedure prc_upd_avion(Pid in varchar2, Pairplane_type in varchar2) is
begin
update airplanes
   set 
    id = Pid,
    airplane_type = Pairplane_type
 where 
 id = Pid;
end prc_upd_avion;
/
show error

create or replace procedure prc_ins_country(Pid in varchar2, Pname in varchar2) is 
begin
  insert into countries (id, name)
  values (Pid, Pname);
  commit;
  exception
--UK o PK
    when dup_val_on_index then
    null;
end prc_ins_country;
/
show error

create or replace procedure prc_upd_country(Pid in varchar2, Pname in varchar2) is
begin
update countries
   set 
    id = Pid,
    name = Pname
 where 
 id = Pid;
end prc_upd_country;
/
show error

create or replace procedure prc_ins_city(Pid in varchar2, Pname in varchar2, Pcountry in varchar2) is 
begin
  insert into cities (id, name, country)
  values (Pid, Pname, Pcountry);
  commit;
  exception
--UK o PK
    when dup_val_on_index then
    null;
end prc_ins_city;
/
show error

create or replace procedure prc_upd_city(Pid in varchar2, Pname in varchar2, Pcountry in varchar2) is
begin
update cities
   set 
    id = Pid,
    name = Pname,
    country = Pcountry
 where id = Pid;
end prc_upd_city;
/
show error


create or replace procedure prc_ins_schedule(Pdeparture_time in date, Parrival_time in date) is 
begin
  insert into schedules (id, departure_time, arrival_time)
  values (seq_id_schedules.nextval, Pdeparture_time, Parrival_time);
  commit;
  exception
--UK o PK
    when dup_val_on_index then
    null;
end prc_ins_schedule;
/
show error

create or replace procedure prc_upd_schedule(Pid in number,
                                            Pdeparture_time in date, Parrival_time in date) is
begin
update schedules
   set
    departure_time = Pdeparture_time, 
    arrival_time = Parrival_time

 where 
 id = Pid;
end prc_upd_schedule;
/
show error

create or replace procedure prc_ins_route(Pid in varchar2, Pduration in date, Porigin in varchar2, 
                                Pdestination in varchar2, Pairplane in varchar2, Pschedule in number ) is 
begin
  insert into routes (id, duration, origin, destination, airplane, schedule)
  values (Pid, Pduration, Porigin, Pdestination, Pairplane, Pschedule);
  commit;
  exception
--UK o PK
    when dup_val_on_index then
    null;
end prc_ins_route;
/
show error

create or replace procedure prc_upd_route(Pid in varchar2, Pduration in date, Porigin in varchar2, 
                                Pdestination in varchar2, Pairplane in varchar2, Pschedule in number) is
begin
update routes
   set 
    duration = Pduration,
    origin = Porigin,
    destination = Pdestination,
    airplane = Pairplane,
    schedule = Pschedule
 where 
 id = Pid;
end prc_upd_route;
/
show error

create or replace procedure prc_ins_ticket(Pfila in number, 
                                            Pcol in number, Preservation in varchar2) is 
begin
  insert into ticket (id, fila, col, reservation )
  values (seq_id_ticket.nextval, Pfila, Pcol, Preservation);
  commit;
  exception
--UK o PK
    when dup_val_on_index then
    null;
end prc_ins_ticket;
/
show error

create or replace procedure prc_upd_ticket(Pid in number, Pfila in number, 
                                            Pcol in number, Preservation in varchar2) is
begin
update ticket
   set 
    fila = Pfila,
    col = Pcol,
    reservation = Preservation
 where 
 id = Pid;
end prc_upd_ticket;
/
show error


create or replace procedure prc_ins_reservation( Puserid in number, Ptotalprice in number,
PseatQuantity in number) is 
begin
  insert into reservation (id, userid, totalPrice, seatQuantity)
  values (seq_id_reservation.nextval, Puserid, Ptotalprice, PseatQuantity);
  commit;
  exception
--UK o PK
    when dup_val_on_index then
    null;
end prc_ins_reservation;
/
show error

create or replace procedure prc_upd_reservation(Pid in number, Puserid in number, Ptotalprice in number,
PseatQuantity in number) is
begin
update reservation
   set 
    userid = Puserid,
    totalPrice = Ptotalprice,
    seatQuantity = PseatQuantity
 where 
 id = Pid;
end prc_upd_reservation;
/
show error

