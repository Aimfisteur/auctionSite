use project;
select MAX(user_id) from users;
insert into users Values(7,"a","a","a","a",0,0,0,0);
select * from users;
select * from current_items;
SELECT * FROM current_items WHERE current_items.date_posted>'2022-11-19';
SELECT *
FROM current_items
INNER JOIN users ON current_items.seller_id=users.user_id where first_name="Richard" and second_name="Goudelin";
UPDATE current_items
SET current_buyer_id = 4, bid_price=26000,bid_history=CONCAT(bid_history, ";26000")
WHERE item_id = 5;
select * from current_items;
select * from users;

select count(item_id) from sold_items where item_category="Electronics"; 



