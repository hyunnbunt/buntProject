insert into owner(name) values('Junseok');
insert into owner(name) values('Hyuna');
insert into owner(name) values('Kat');
insert into owner(name) values('Jiwook');
insert into owner(name) values('Chih');
insert into owner(name) values('Danbi');

insert into dog(owner_id, name, weight, age, sex) values(1, 'Lana', 3.0, 10l, 'female');
insert into dog(owner_id, name, weight, age, sex) values(2, 'Bunt', 6.2, 4l, 'male');
insert into dog(owner_id, name, weight, age, sex) values(3, 'Biscuit', 4.5, 2l, 'male');
insert into dog(owner_id, name, weight, age, sex) values(4, 'Hatu', 5.5, 3l, 'male');
insert into dog(owner_id, name, weight, age, sex) values(5, 'Latte', 8.0, 3l, 'male');

insert into event(date, time, latitude, longitude) values(20230816, 1530, 37.541, 126.986);
insert into event(date, time, latitude, longitude) values(20231009, 1900, 25.66, 166.2);
insert into event(date, time, latitude, longitude) values(20231101, 2050, 11.11, 55.16);

insert into location(latitude, longitude) values(111.1, 222.2);
insert into location(latitude, longitude) values(222.1, 111.1);

insert into location_walking_dogs(walk_locations_id, walking_dogs_id) values(1l, 3l);
insert into location_walking_dogs(walk_locations_id, walking_dogs_id) values(1l, 4l);

insert into dog_friends(dog_id, friends_id) values(1, 2);
insert into dog_friends(dog_id, friends_id) values(2, 1);