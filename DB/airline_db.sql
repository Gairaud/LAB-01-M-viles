--===================== Drops ===================== 
PROMPT ==========================
PROMPT DROPS 
PROMPT ==========================
drop table users cascade constraints;
drop sequence seq_id_users;

drop table airplane_types cascade constraints;


drop table airplanes cascade constraints;
drop sequence seq_id_airplanes;

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

create table airplanes (id number not null, airplane_type varchar(45)) tablespace system;

--==================== Sequences =====================                  
create sequence seq_id_users start with 1 increment by 1 cache 2;
create sequence seq_id_airplanes start with 1 increment by 1 cache 2;


--==================== PKs =====================    
alter table users add constraint users_pk primary key(id) using index tablespace system;  
alter table airplane_types add constraint airplane_types_pk primary key(id) using index tablespace system;
alter table airplanes add constraint airplane_pk primary key(id) using index tablespace system;

--==================== FKs =====================
alter table airplanes add constraint type_fk foreign key (airplane_type) references airplane_types;  
--==================== Inserts =====================    

insert into users values (123456789, 'kike00', 'chico500', 'Enrique',
                        'Mendez Cabezas', 'kike@gmail.com', 'Arriba de la UNA',
                        '9090-0909', 1);

insert into airplane_types values ('Super airplane', 2000, 'Model 0',
                                  'Brand 0', 330, 6, 55);

commit;