# --- !Ups

insert into apartment_type (id,name) values (1,'Finca');
insert into apartment_type (id,name) values (2,'Apartment');
insert into apartment_type (id,name) values (3,'Vacation Home');

insert into facility (id,name) values (1,'POOL');
insert into facility (id,name) values (2,'TV');
insert into facility (id,name) values (3,'GARDEN');
insert into facility (id,name) values (4,'TERRACE');
insert into facility (id,name) values (5,'BALCONY');
insert into facility (id,name) values (6,'KITCHEN');


insert into apartment (id,name,apartment_type_id,latitude,longitude) values (1, 'Finca on Mallorca 1', 1, 39.61893, 2.9169003);
insert into apartment (id,name,apartment_type_id,latitude,longitude) values (2, 'Apartment on Mallorca 2', 2, 39.62893, 2.9269003);
insert into apartment (id,name,apartment_type_id,latitude,longitude) values (3, 'Vacation Home on Mallorca 3', 3, 39.63893, 2.9369003);
insert into apartment (id,name,apartment_type_id,latitude,longitude) values (4, 'Finca on Mallorca 4', 1, 39.64893, 2.9469003);
insert into apartment (id,name,apartment_type_id,latitude,longitude) values (5, 'Apartment on Mallorca 5', 2, 39.65893, 2.9569003);
insert into apartment (id,name,apartment_type_id,latitude,longitude) values (6, 'Vacation Home on Mallorca 6', 3, 39.66893, 2.9669003);
insert into apartment (id,name,apartment_type_id,latitude,longitude) values (7, 'Finca on Mallorca 7', 1, 39.67893, 2.9769003);
insert into apartment (id,name,apartment_type_id,latitude,longitude) values (8, 'Apartment on Mallorca 8', 2, 39.68893, 2.9869003);
insert into apartment (id,name,apartment_type_id,latitude,longitude) values (9, 'Vacation Home on Mallorca 9', 3, 39.69893, 2.9969003);

insert into apartment_facility (apartment_id,facility_id) values (1,1);
insert into apartment_facility (apartment_id,facility_id) values (1,2);
insert into apartment_facility (apartment_id,facility_id) values (1,3);
insert into apartment_facility (apartment_id,facility_id) values (1,4);
insert into apartment_facility (apartment_id,facility_id) values (1,5);
insert into apartment_facility (apartment_id,facility_id) values (1,6);

insert into apartment_facility (apartment_id,facility_id) values (2,1);
insert into apartment_facility (apartment_id,facility_id) values (2,2);

insert into apartment_facility (apartment_id,facility_id) values (3,1);
insert into apartment_facility (apartment_id,facility_id) values (3,2);
insert into apartment_facility (apartment_id,facility_id) values (3,3);
insert into apartment_facility (apartment_id,facility_id) values (3,4);

insert into apartment_facility (apartment_id,facility_id) values (4,1);
insert into apartment_facility (apartment_id,facility_id) values (4,2);
insert into apartment_facility (apartment_id,facility_id) values (4,6);

insert into apartment_facility (apartment_id,facility_id) values (5,1);
insert into apartment_facility (apartment_id,facility_id) values (5,2);
insert into apartment_facility (apartment_id,facility_id) values (5,3);

insert into apartment_facility (apartment_id,facility_id) values (6,4);
insert into apartment_facility (apartment_id,facility_id) values (6,5);
insert into apartment_facility (apartment_id,facility_id) values (6,6);

insert into apartment_facility (apartment_id,facility_id) values (7,1);
insert into apartment_facility (apartment_id,facility_id) values (7,2);

insert into apartment_facility (apartment_id,facility_id) values (8,5);
insert into apartment_facility (apartment_id,facility_id) values (8,6);

insert into apartment_facility (apartment_id,facility_id) values (9,1);
insert into apartment_facility (apartment_id,facility_id) values (9,6);

# --- !Downs

delete from apartment;
delete from apartment_type;
