# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table campoentity (
  id                        bigint not null,
  id_jefe_campo             varchar(255),
  region                    varchar(9),
  constraint ck_campoentity_region check (region in ('ANDINA','CARIBE','PACIFICA','ORINOQUIA','AMAZONAS')),
  constraint pk_campoentity primary key (id))
;

create table pozo_entity (
  id                        bigint not null,
  lon                       bigint,
  lat                       bigint,
  estado                    varchar(10),
  campo_id                  bigint not null,
  constraint ck_pozo_entity_estado check (estado in ('ABIERTO','PRODUCCION','PARADO','CLAUSURADO')),
  constraint pk_pozo_entity primary key (id))
;

create table registro_sensor_barriles_entity (
  id_sensor_barriles        bigserial not null,
  pozo_id                   bigint not null,
  INFO                      float not null,
  TIMESTAMP                 timestamp not null,
  ENTRADAS                  integer not null,
  constraint pk_registro_sensor_barriles_enti primary key (id_sensor_barriles))
;

create table registro_sensor_emerg_entity (
  id_sensor_emerg           bigserial not null,
  pozo_id                   bigint not null,
  INFO                      varchar(255) not null,
  TIMESTAMP                 timestamp not null,
  constraint pk_registro_sensor_emerg_entity primary key (id_sensor_emerg))
;

create table registro_sensor_ener_entity (
  id_sensor_ener            bigserial not null,
  pozo_id                   bigint not null,
  INFO                      float not null,
  TIMESTAMP                 timestamp not null,
  ENTRADAS                  integer not null,
  constraint pk_registro_sensor_ener_entity primary key (id_sensor_ener))
;

create table registro_sensor_temp_entity (
  id_sensor_temp            bigint not null,
  pozo_id                   bigint not null,
  INFO                      float not null,
  TIMESTAMP                 timestamp not null,
  ENTRADAS                  integer not null,
  constraint pk_registro_sensor_temp_entity primary key (id_sensor_temp))
;

create table usuarioentity (
  username                  varchar(255) not null,
  type                      varchar(15) not null,
  password                  varchar(255) not null,
  constraint ck_usuarioentity_type check (type in ('JEFE_CAMPO','JEFE_PRODUCCION','PERSONAL')),
  constraint pk_usuarioentity primary key (username))
;

create sequence Campo;

create sequence Product;

create sequence registro_sensor_temp_entity_seq;

alter table pozo_entity add constraint fk_pozo_entity_campo_1 foreign key (campo_id) references campoentity (id);
create index ix_pozo_entity_campo_1 on pozo_entity (campo_id);
alter table registro_sensor_barriles_entity add constraint fk_registro_sensor_barriles_en_2 foreign key (pozo_id) references pozo_entity (id);
create index ix_registro_sensor_barriles_en_2 on registro_sensor_barriles_entity (pozo_id);
alter table registro_sensor_emerg_entity add constraint fk_registro_sensor_emerg_entit_3 foreign key (pozo_id) references pozo_entity (id);
create index ix_registro_sensor_emerg_entit_3 on registro_sensor_emerg_entity (pozo_id);
alter table registro_sensor_ener_entity add constraint fk_registro_sensor_ener_entity_4 foreign key (pozo_id) references pozo_entity (id);
create index ix_registro_sensor_ener_entity_4 on registro_sensor_ener_entity (pozo_id);
alter table registro_sensor_temp_entity add constraint fk_registro_sensor_temp_entity_5 foreign key (pozo_id) references pozo_entity (id);
create index ix_registro_sensor_temp_entity_5 on registro_sensor_temp_entity (pozo_id);



# --- !Downs

drop table if exists campoentity cascade;

drop table if exists pozo_entity cascade;

drop table if exists registro_sensor_barriles_entity cascade;

drop table if exists registro_sensor_emerg_entity cascade;

drop table if exists registro_sensor_ener_entity cascade;

drop table if exists registro_sensor_temp_entity cascade;

drop table if exists usuarioentity cascade;

drop sequence if exists Campo;

drop sequence if exists Product;

drop sequence if exists registro_sensor_temp_entity_seq;

