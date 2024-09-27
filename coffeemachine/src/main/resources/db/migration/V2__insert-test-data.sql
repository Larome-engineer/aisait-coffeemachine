insert into drinks (drink_name) values ('эспрессо');
insert into drinks (drink_name) values ('американо');
insert into drinks (drink_name) values ('капучино');

insert into recipes (recipe_desc, recipe_drink_id) values ('Ароматный эспрессо это просто', 1);
insert into recipes (recipe_desc, recipe_drink_id) values ('Вкуснейший капучино за 1 секунду', 3);
insert into recipes (recipe_desc, recipe_drink_id) values ('Простейший американо', 2);

insert into inventories(inv_name, inv_num, inv_unit) values ('молоко', 3000, 'мл');
insert into inventories(inv_name, inv_num, inv_unit) values ('кофе', 3000, 'гр');
insert into inventories(inv_name, inv_num, inv_unit) values ('вода', 5000, 'мл');

insert into recipe_inventory (ri_recipe_id, ri_invent_id, ri_num) values (1, 2, 100);
insert into recipe_inventory (ri_recipe_id, ri_invent_id, ri_num) values (1, 3, 100);

insert into recipe_inventory (ri_recipe_id, ri_invent_id, ri_num) values (2, 2, 100);
insert into recipe_inventory (ri_recipe_id, ri_invent_id, ri_num) values (2, 1, 200);

insert into recipe_inventory (ri_recipe_id, ri_invent_id, ri_num) values (3, 2, 200);
insert into recipe_inventory (ri_recipe_id, ri_invent_id, ri_num) values (3, 3, 400);