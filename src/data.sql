use project;
-- insert values in users
insert into users values(1,"admine","admine","admine@gmail.com","admin",0,0);
insert into users values(2,"Richard","Goudelin","richard@gmail.com","Richard1",1,2);
insert into users values(3,"Jean","Lafont","jean@gmail.com","Jean1",1,0);
insert into users values(4,"Cal","Dragon","cal@gmail.com","Cal1",1,0);

-- select * from users;

-- insert values in current_items

insert into current_items values(4,2,0,"beyblade","Home","little spinner to play with your friends","New",0,10000,5000,0,"",'2022-11-20','2022-12-25');
insert into current_items values(5,3,2,"handball","Sporting Goods","ball to play handball, from muzino","Like-New",25000,30000,20000,3,"22000;23000;25000",'2022-11-18','2022-01-12');

-- select * from current_items;

-- insert values in sold_items
insert into sold_items values(1,2,4,"handspinner","Home","little fidget toy to distress","Like-New",5000,2,'2022-11-20');

insert into sold_items values(2,3,2,"ipad","Electronics","tablet to work or design","New",100000,1,'2022-11-21');
insert into sold_items values(3,3,2,"chair","Home","modern leather chair","Used (Acceptable)",120000,2,'2020-09-13');
insert into sold_items values(4,2,4,"boomerang","Sporting Goods","i give away this boomerang to gain place","Like-New",1000,1,'2022-11-21');
insert into sold_items values(5,4,3,"Keyboard","Electronics","mecanical keyboard","Like-New",5000,2,'2022-11-20');
-- select * from sold_items;

