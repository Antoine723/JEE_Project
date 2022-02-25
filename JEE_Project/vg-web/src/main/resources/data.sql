INSERT INTO console VALUES (1, "ps5", "PS5", 499, 12, 5, "2020-11-19");
INSERT INTO console (id, img, name, price, quantity, release_date) VALUES (2, "xbox_series_x", "Xbox Series X", 499, 85, "2020-11-10");
INSERT INTO console (id, img, name, price, quantity, release_date) VALUES (3, "ps4", "PS4", 199, 545, "2013-11-15");
INSERT INTO console (id, img, name, price, quantity, release_date) VALUES (4, "xbox_one", "Xbox One", 199, 660, "2013-11-22");
INSERT INTO console (id, img, name, price, quantity, release_date) VALUES (5, "nintendo_switch", "Switch", 269, 1980, "2017-03-03");


INSERT INTO game (id, name, price, quantity, release_date, offline_players_number, online_players_number)
VALUES (6, "Call Of Duty : Vanguard", 70, 1870, "2021-11-05", 2, 28);
INSERT INTO game (id, name, price, quantity, release_date, offline_players_number, online_players_number)
VALUES (7, "Assassin's Creed Valhalla", 35, 2568, "2020-11-10", 1, 1);
INSERT INTO game (id, name, price, quantity, release_date, offline_players_number, online_players_number)
VALUES (8,"Légendes Pokémon : Arceus", 50, 4400, "2022-01-28", 1, 2);

INSERT INTO users VALUES (1, "4, Rue Colbert Lille", "admin@hotmail.com", "Admin", "12345");

INSERT INTO comment VALUES (1, "Jamais déçu des consoles de sony !", 5, 1, 1);

INSERT INTO game_console
VALUES (6,1,"cod_vanguard_ps5", 70), (6,2,"cod_vanguard_xbox_series_x", 70),
       (6,3,"cod_vanguard_ps4", 60),(6,4, "cod_vanguard_xbox_one", 60),
       (7,1, "assassins_creed_valhalla_ps5", 35), (7,2, "assassins_creed_valhalla_xbox_series_x", 35),
       (7,3, "assassins_creed_valhalla_ps4", 30), (7,4, "assassins_creed_valhalla_xbox_one", 30),
       (8,5, "pokemon_arceus_switch", 50);