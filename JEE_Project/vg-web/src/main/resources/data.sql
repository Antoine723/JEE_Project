INSERT INTO console VALUES (1, "ps5", "PS5", 499, 12, 4.8, "2020-11-19");
INSERT INTO console VALUES (2, "xbox_series_x", "Xbox Series X", 499, 85, 4.5, "2020-11-10");
INSERT INTO console VALUES (3, "ps4", "PS4", 199, 545, 4.3, "2013-11-15");
INSERT INTO console VALUES (4, "xbox_one", "Xbox One", 199, 660, 4.2, "2013-11-22");
INSERT INTO console VALUES (5, "nintendo_switch", "Nintendo Switch", 269, 1980, 4.3, "2017-03-03");


INSERT INTO game VALUES (6, "cod_vanguard", "Call Of Duty : Vanguard", 70, 1870, 3.9, "2021-11-05", 2, 28);
INSERT INTO game VALUES (7, "assassins_creed_valhalla", "Assassin's Creed Valhalla", 35, 2568, 4.1, "2020-11-10", 1, 1);
INSERT INTO game VALUES (8, "pokemon_arceus","Légendes Pokémon : Arceus", 50, 4400, 4.5, "2022-01-28", 1, 2);

INSERT INTO users VALUES (1, "4, Rue Colbert Lille", "admin@hotmail.com", "Admin", "12345");

INSERT INTO comment VALUES (1, "Jamais déçus des consoles de sony !", 4.8, 1, 1);

INSERT INTO games_on_consoles
VALUES (6,1), (6,2), (6,3), (6,4), (7,1), (7,2), (7,3), (7,4), (8,5);