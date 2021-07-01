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
create table users (user_id number not null, username varchar2(40),
                    password varchar2(40), name varchar2(25),
                    last_name varchar2(40), email varchar2(30),
                    address varchar2(200), phone varchar2(15),
                    is_admin varchar2(1)) tablespace system;

create table airplane_types (at_id varchar2(45), year number, model varchar2(20),
                            brand varchar2(20), passengers_quantity number,
                            rows_number number, columns_number number) tablespace system;

create table airplanes (id varchar2(20), airplane_type varchar2(45)) tablespace system;

create table countries (id varchar2(5), name varchar2(20)) tablespace system;
create table cities (id varchar2(5), name varchar2(20), country varchar2(5)) tablespace system;

create table schedules (id number not null,
                        departure_time date, arrival_time date) tablespace system;


create table routes (r_id varchar2(100),  origin varchar2(45), destination varchar2(45)) tablespace system;

create table ticket(ticket_id number not null, fila number, col number, reservation number, flight number) tablespace system;

create table reservation(res_id number not null, userid number, totalPrice number, seatQuantity number) tablespace system; 

create table flights(f_id number not null, ruta varchar2(100), departure_date varchar2(50), 
                      return_date varchar2(50), price number, available_seats number, airplane varchar2(20));

--==================== Sequences =====================                  
create sequence seq_id_users start with 1 increment by 1 cache 2;
create sequence seq_id_airplanes start with 1 increment by 1 cache 2;
create sequence seq_id_schedules start with 1 increment by 1 cache 2;
create sequence seq_id_reservation start with 1 increment by 1 cache 2;
create sequence seq_id_ticket start with 1 increment by 1 cache 2;
create sequence seq_id_flights start with 1 increment by 1 cache 2;


--==================== PKs =====================    
alter table users add constraint users_pk primary key(user_id) using index tablespace system;  
alter table airplane_types add constraint airplane_types_pk primary key(at_id) using index tablespace system;
alter table airplanes add constraint airplane_pk primary key(id) using index tablespace system;
alter table countries add constraint countries_pk primary key(id) using index tablespace system;
alter table cities add constraint cities_pk primary key(id) using index tablespace system;
alter table schedules add constraint schedules_pk primary key(id) using index tablespace system;
alter table routes add constraint routes_pk primary key(r_id) using index tablespace system;
alter table ticket add constraint ticket_pk primary key(ticket_id) using index tablespace system;
alter table reservation add constraint reservation_pk primary key(res_id) using index tablespace system;
alter table flights add constraint flights_pk primary key(f_id) using index tablespace system;

--==================== FKs =====================
alter table airplanes add constraint type_fk foreign key (airplane_type) references airplane_types on delete cascade;  
alter table cities add constraint country_fk foreign key (country) references countries;
--alter table routes add constraint origin_fk foreign key (origin) references cities;
--alter table routes add constraint destination_fk foreign key (destination) references cities;
alter table flights add constraint airplane_fk foreign key (airplane) references airplanes;
--alter table routes add constraint schedule_fk foreign key (schedule) references schedules;
alter table ticket add constraint reservation_fk foreign key (reservation) references reservation;
alter table ticket add constraint fligth_fk foreign key (flight) references flights;
alter table flights add constraint route_fk foreign key (ruta) references routes;

--==================== Inserts =====================    

insert into users values (123456789, 'kike00', 'chico500', 'Enrique',
                        'Mendez Cabezas', 'kike@gmail.com', 'Arriba de la UNA',
                        '9090-0909', 1);

insert into users values (117290193, 'gairaud00', 'chico600', 'Philippe',
                        'Gairaud Quesada', 'gairaud@gmail.com', 'Abajo de la UNA',
                        '8080-0808', 0);

insert into airplane_types values ('Airbus 320', 2000, '320',
                                  'Airbus', 132, 6, 22);

insert into airplane_types values ('Airbus 330', 2005, '330',
                                  'Airbus', 132, 6, 22);

insert into airplane_types values ('Airbus 340', 2010, '340',
                                  'Airbus', 132, 6, 22);

insert into airplane_types values ('Boeing 737', 2008, '737',
                                  'Boeing', 132, 6, 22);

insert into airplane_types values ('Boeing 747', 2006, '747',
                                  'Boeing', 132, 6, 22);





insert into airplanes values ('AVN-01', 'Airbus 320');
insert into airplanes values ('AVN-02', 'Airbus 330');
insert into airplanes values ('AVN-03', 'Airbus 340');
insert into airplanes values ('AVN-04', 'Boeing 737');
insert into airplanes values ('AVN-05', 'Boeing 747');


--insert into countries values ('CR', 'Costa Rica');
--insert into cities values ('SJO', 'San Jose', 'CR');

--insert into countries values ('USA', 'Estados Unidos');
--insert into cities values ('ATL', 'ATLANTA', 'USA');

--insert into schedules values (seq_id_schedules.nextval, TO_DATE('2021/12/19', 'yyyy/mm/dd'), 
                                ---TO_DATE('2021/12/25', 'yyyy/mm/dd'));
insert into routes values ('Costa Rica-Alemania', 'Costa Rica', 'Alemania');
insert into routes values ('Costa Rica-Rusia', 'Costa Rica', 'Rusia');
insert into routes values ('Estados Unidos-China', 'Estados Unidos', 'China');
insert into routes values ('Estados Unidos-Japon', 'Estados Unidos', 'Japon');
insert into routes values ('Estados Unidos-Grecia', 'Estados Unidos', 'Grecia');
insert into routes values ('Costa Rica-China', 'Costa Rica', 'China');

insert into reservation values(seq_id_reservation.nextval, 123456789, 1000, 3);
insert into reservation values(seq_id_reservation.nextval, 117290193, 1000, 3);

insert into flights values (seq_id_flights.nextval, 'Costa Rica-Alemania', '2021/11/19 20:00',
                            '2021/11/30 08:00', 1200, 200, 'AVN-01');
insert into flights values (seq_id_flights.nextval, 'Costa Rica-Rusia', '2021/05/22 20:00',
                            '2021/06/02 08:00', 1200, 200, 'AVN-02');
insert into flights values (seq_id_flights.nextval, 'Costa Rica-China', '2021/04/15 20:00',
                            '2021/04/30 08:00', 1600, 200, 'AVN-03');
insert into flights values (seq_id_flights.nextval, 'Estados Unidos-Japon', '2021/10/10 20:00',
                            '2021/10/20 08:00', 1200, 200, 'AVN-04');
insert into flights values (seq_id_flights.nextval, 'Estados Unidos-Grecia', '2021/07/15 20:00',
                            '2021/07/30 08:00', 1200, 200, 'AVN-05');

--insert into flights values (seq_id_flights.nextval, 'SJO-ATL', TO_DATE('2021/12/19', 'yyyy/mm/dd'),
                            --TO_DATE('2021/12/19', 'yyyy/mm/dd'), 300,   120);

--insert into ticket values (seq_id_ticket.nextval, 5,7,1,1);
--insert into ticket values (seq_id_ticket.nextval, 5,6,1,1);
--insert into ticket values (seq_id_ticket.nextval, 5,5,1,1);
--insert into ticket values (seq_id_ticket.nextval, 1000,1000,2,1);

commit;

PROMPT          Procedimientos USER
PROMPT =====================================
create or replace procedure prc_ins_user(Pid in number, PUser in varchar2, Ppassword in varchar2,
Pname in varchar2, Papellido in varchar2, Pemail in varchar2, Pdireccion in varchar2,
Pnumber in varchar2, Padmin in varchar2) is 
begin
  insert into users (user_id, username, password, name, last_name, email, address, phone, is_admin)
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
    user_id = Pid,
    username = PUser,
    password = Ppassword,
    name = Pname,
    last_name = Papellido,
    email = Pemail,
    address = Pdireccion,
    phone = Pnumber,
    is_admin = Padmin
 where 
 user_id = Pid;
end prc_upd_user;
/
show error


create or replace procedure prc_ins_aviontype(Pid in varchar2, Pyear in number, 
Pmodel in varchar2, Pbrand in varchar2, Ppassengers_quantity in number, 
Prows_number in number, Pcolumns_number in number) is 
begin
  insert into airplane_types (at_id, year, model, brand, passengers_quantity, rows_number, columns_number)
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
    at_id = Pid ,
    year = Pyear ,
    model = Pmodel ,
    brand = Pbrand ,
    passengers_quantity = Ppassengers_quantity ,
    rows_number = Prows_number ,
    columns_number = Pcolumns_number
 where 
 at_id = Pid;
end prc_upd_aviontype;
/
show error

create or replace procedure prc_ins_avion(Pid in varchar2, Pairplane_type in varchar2) is 
begin
  insert into airplanes(id, airplane_type)
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

--create or replace procedure prc_upd_schedule(Pid in number,
--                                            Pdeparture_time in date, Parrival_time in date) is
--begin
--update schedules
--   set
--    departure_time = Pdeparture_time, 
--    arrival_time = Parrival_time

-- where 
-- id = Pid;
--end prc_upd_schedule;
--/
--show error

create or replace procedure prc_ins_route(Pid in varchar2, Porigin in varchar2, Pdestination in varchar2) is 
begin
  insert into routes (r_id, origin, destination)
  values (Pid, Porigin, Pdestination);
  commit;
  exception
    when dup_val_on_index then
    null;
end prc_ins_route;
/
show error

create or replace procedure prc_upd_route(Pid in varchar2, Porigin in varchar2, Pdestination in varchar2) is
begin
update routes
  set 
    origin = Porigin,
    destination = Pdestination
 where 
 r_id = Pid;
end prc_upd_route;
/
show error

create or replace procedure prc_ins_ticket(Pfila in number, 
                                            Pcol in number, Preservation in varchar2, Pflight in number) is 
begin
  insert into ticket (ticket_id, fila, col, reservation, flight)
  values (seq_id_ticket.nextval, Pfila, Pcol, Preservation, Pflight);
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
 ticket_id = Pid;
end prc_upd_ticket;
/
show error


create or replace procedure prc_ins_reservation( Puserid in number, Ptotalprice in number,
PseatQuantity in number) is 
begin
  insert into reservation (res_id, userid, totalPrice, seatQuantity)
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
 res_id = Pid;
end prc_upd_reservation;
/
show error

create or replace procedure prc_ins_flights( Pruta in varchar2, Pdeparture_date in varchar2,
Preturn_date in varchar2, Pprice in number, Pavailable_seats in number, Pairplane in varchar2) is 
begin
  insert into flights (f_id, ruta, departure_date, return_date, price, available_seats, airplane)
  values (seq_id_flights.nextval, Pruta, Pdeparture_date, Preturn_date, Pprice, Pavailable_seats, Pairplane);
  commit;
  exception
--UK o PK
    when dup_val_on_index then
    null;
end PRC_INS_FLIGHTS;
/
show error

