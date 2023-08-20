insert into owner(name) values('Junseok');
insert into owner(name) values('Hyuna');

insert into dog(owner_id, name, weight, age, sex) values(1, 'Bunt', 6.2, 4, 'male');
insert into dog(owner_id, name, weight, age, sex) values(1, 'Lana', 3, 10, 'female');
insert into dog(owner_id, name, weight, age, sex) values(2, 'Buntu', 6.2, 4, 'male');
insert into dog(owner_id, name, weight, age, sex) values(2, 'Hatu', 5.5, 3, 'male');

insert into event(date, time, latitude, longitude) values(20230816, 1530, 37.541, 126.986);

insert into dog_friends(dog_id, friends_id) values(1, 2);
insert into dog_friends(dog_id, friends_id) values(2, 1);