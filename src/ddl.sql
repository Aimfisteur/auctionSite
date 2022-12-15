CREATE DATABASE project;
USE project;

create table users(
	user_id int,
    first_name varchar(255),
    second_name varchar(255),
    email varchar(255),
    pw varchar(255),
    number_sold_items int,
    number_buy_items int,
    
    PRIMARY KEY (user_id)
    );

create table current_items(
	item_id int,
    seller_id int,
    current_buyer_id int,
    item_name varchar(255),
    item_category varchar(255),
    item_description text(65535),
    item_condition varchar(255),
    bid_price int,
    buy_it_now int,
    floor_price int,
    number_bid int,
    bid_history text(65535),
    date_posted date,
    date_end date,
    PRIMARY KEY (item_id));
create table sold_items(
	item_id int,
    seller_id int,
    buyer_id int,
    item_name varchar(255),
    item_category varchar(255),
    item_description text(65535),
    item_condition varchar(255),
    sold_price int,
    number_bid int,
    date_end date,
    PRIMARY KEY (item_id));
    



    
	