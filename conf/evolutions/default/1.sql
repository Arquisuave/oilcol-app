# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table campoentity (
  id                        bigint not null,
  id_jefe_campo_username    varchar(255),
  region                    varchar(9),
  constraint ck_campoentity_region check (region in ('ANDINA','CARIBE','PACIFICA','ORINOQUIA','AMAZONAS')),
  constraint uq_campoentity_id_jefe_campo_use unique (id_jefe_campo_username),
  constraint pk_campoentity primary key (id))
;

create table notification_entity (
  id                        bigint not null,
  username_username         varchar(255),
  message                   varchar(255) not null,
  register_id_sensor_emerg  bigint,
  resolved                  boolean not null,
  constraint uq_notification_entity_register_ unique (register_id_sensor_emerg),
  constraint pk_notification_entity primary key (id))
;

create table pozo_entity (
  id                        bigint not null,
  lon                       float,
  lat                       float,
  estado                    varchar(10),
  campo_id                  bigint not null,
  constraint ck_pozo_entity_estado check (estado in ('ABIERTO','PRODUCCION','PARADO','CLAUSURADO')),
  constraint pk_pozo_entity primary key (id))
;

create table registro_sensor_barriles_entity (
  id_sensor_barriles        bigint not null,
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
  tipo                      varchar(15) not null,
  constraint ck_registro_sensor_emerg_entity_tipo check (tipo in ('INCENDIO','BLOQUEO_POZO','FALLA_ELECTRICA')),
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
  constraint ck_usuarioentity_type check (type in ('JEFE_CAMPO','JEFE_PRODUCCION','PERSONAL','NONE')),
  constraint pk_usuarioentity primary key (username))
;

create sequence campoentity_seq;

create sequence Notification;

create sequence Product;

create sequence registro_sensor_barriles_entity_seq;

create sequence registro_sensor_temp_entity_seq;

alter table campoentity add constraint fk_campoentity_idJefeCampo_1 foreign key (id_jefe_campo_username) references usuarioentity (username);
create index ix_campoentity_idJefeCampo_1 on campoentity (id_jefe_campo_username);
alter table notification_entity add constraint fk_notification_entity_usernam_2 foreign key (username_username) references usuarioentity (username);
create index ix_notification_entity_usernam_2 on notification_entity (username_username);
alter table notification_entity add constraint fk_notification_entity_registe_3 foreign key (register_id_sensor_emerg) references registro_sensor_emerg_entity (id_sensor_emerg);
create index ix_notification_entity_registe_3 on notification_entity (register_id_sensor_emerg);
alter table pozo_entity add constraint fk_pozo_entity_campo_4 foreign key (campo_id) references campoentity (id);
create index ix_pozo_entity_campo_4 on pozo_entity (campo_id);
alter table registro_sensor_barriles_entity add constraint fk_registro_sensor_barriles_en_5 foreign key (pozo_id) references pozo_entity (id);
create index ix_registro_sensor_barriles_en_5 on registro_sensor_barriles_entity (pozo_id);
alter table registro_sensor_emerg_entity add constraint fk_registro_sensor_emerg_entit_6 foreign key (pozo_id) references pozo_entity (id);
create index ix_registro_sensor_emerg_entit_6 on registro_sensor_emerg_entity (pozo_id);
alter table registro_sensor_ener_entity add constraint fk_registro_sensor_ener_entity_7 foreign key (pozo_id) references pozo_entity (id);
create index ix_registro_sensor_ener_entity_7 on registro_sensor_ener_entity (pozo_id);
alter table registro_sensor_temp_entity add constraint fk_registro_sensor_temp_entity_8 foreign key (pozo_id) references pozo_entity (id);
create index ix_registro_sensor_temp_entity_8 on registro_sensor_temp_entity (pozo_id);



# --- !Downs

drop table if exists campoentity cascade;

drop table if exists notification_entity cascade;

drop table if exists pozo_entity cascade;

drop table if exists registro_sensor_barriles_entity cascade;

drop table if exists registro_sensor_emerg_entity cascade;

drop table if exists registro_sensor_ener_entity cascade;

drop table if exists registro_sensor_temp_entity cascade;

drop table if exists usuarioentity cascade;

drop sequence if exists campoentity_seq;

drop sequence if exists Notification;

drop sequence if exists Product;

drop sequence if exists registro_sensor_barriles_entity_seq;

drop sequence if exists registro_sensor_temp_entity_seq;

