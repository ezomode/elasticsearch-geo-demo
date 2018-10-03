# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table apartment (
  id                            bigint not null,
  name                          varchar(255),
  latitude                      double,
  longitude                     double,
  apartment_type_id             bigint,
  constraint pk_apartment primary key (id)
);
create sequence apartment_seq;

create table apartment_facility (
  apartment_id                  bigint not null,
  facility_id                   bigint not null,
  constraint pk_apartment_facility primary key (apartment_id,facility_id)
);

create table apartment_type (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_apartment_type primary key (id)
);
create sequence apartment_type_seq;

create table facility (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_facility primary key (id)
);
create sequence facility_seq;

alter table apartment add constraint fk_apartment_apartment_type_id foreign key (apartment_type_id) references apartment_type (id) on delete restrict on update restrict;
create index ix_apartment_apartment_type_id on apartment (apartment_type_id);

alter table apartment_facility add constraint fk_apartment_facility_apartment foreign key (apartment_id) references apartment (id) on delete restrict on update restrict;
create index ix_apartment_facility_apartment on apartment_facility (apartment_id);

alter table apartment_facility add constraint fk_apartment_facility_facility foreign key (facility_id) references facility (id) on delete restrict on update restrict;
create index ix_apartment_facility_facility on apartment_facility (facility_id);


# --- !Downs

alter table apartment drop constraint if exists fk_apartment_apartment_type_id;
drop index if exists ix_apartment_apartment_type_id;

alter table apartment_facility drop constraint if exists fk_apartment_facility_apartment;
drop index if exists ix_apartment_facility_apartment;

alter table apartment_facility drop constraint if exists fk_apartment_facility_facility;
drop index if exists ix_apartment_facility_facility;

drop table if exists apartment;
drop sequence if exists apartment_seq;

drop table if exists apartment_facility;

drop table if exists apartment_type;
drop sequence if exists apartment_type_seq;

drop table if exists facility;
drop sequence if exists facility_seq;

