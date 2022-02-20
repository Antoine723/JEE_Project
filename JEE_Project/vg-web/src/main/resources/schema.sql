SET FOREIGN_KEY_CHECKS = 0;
drop table comment;
drop table console;
drop table game;
drop table games_on_consoles;
drop table hibernate_sequence;
drop table orders;
drop table orders_products;
drop table users;
SET FOREIGN_KEY_CHECKS = 1;

create table comment (id bigint not null auto_increment, content varchar(255), rating integer, product_id bigint, user_id bigint, primary key (id));
create table console (id bigint not null auto_increment, img varchar(255), name varchar(255), price float, quantity integer, rating float, release_date date, primary key (id));
create table game (id bigint not null auto_increment, img varchar(255), name varchar(255), price float, quantity integer, rating float, release_date date, offline_players_number integer, online_players_number integer, primary key (id));
create table games_on_consoles (game_id bigint not null, console_id bigint not null);
create table hibernate_sequence (next_val bigint);
create table orders (id bigint not null auto_increment, address varchar(255), order_number binary(255), user_id bigint, primary key (id));
create table orders_products (order_id bigint not null, product_id bigint not null);
create table users (id bigint not null auto_increment, address varchar(255), mail varchar(255), name varchar(255), password varchar(255), primary key (id));

insert into hibernate_sequence values ( 1 );

alter table comment add constraint FK8kcum44fvpupyw6f5baccx25c foreign key (user_id) references user (id);
alter table games_on_consoles add constraint FKfkdp40taw91m0hd9nrrcy8vyr foreign key (console_id) references console (id);
alter table games_on_consoles add constraint FKc9rccrnuycxbrjqbg5ydhoytp foreign key (game_id) references game (id);
alter table orders add constraint FKel9kyl84ego2otj2accfd8mr7 foreign key (user_id) references user (id);
alter table orders_products add constraint FKe4y1sseio787e4o5hrml7omt5 foreign key (order_id) references orders (id);