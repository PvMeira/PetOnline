insert into client(cpf,name,pet_name,phone_number,address)
     values ('40898342104','João Silva','Thor','4490909090','Rua Fulano, 444, predio1');

insert into client(cpf,name,pet_name,phone_number,address)
     values ('69307294690','Maria Joana','Silvia','5190909090','Rua Fulano, 444, predio1');

insert into client(cpf,name,pet_name,phone_number,address)
     values ('59168683758','Paulo Ferreira Vasques','Vermelho','5190909090','Rua Fulano, 444, predio2');


INSERT INTO item (id,name,description,value,quantity,category)
values (nextval('item_seq'),'Osso de Borracha P', 'Osso de borracha para cachorros de pequenos porte, 120gr', 8, 20,1);
INSERT INTO item (id,name,description,value,quantity,category)
values (nextval('item_seq'),'Osso de Borracha M', 'Osso de borracha para cachorros de médio porte, 180gr', 14, 30,1);
INSERT INTO item (id,name,description,value,quantity,category)
values (nextval('item_seq'),'Osso de Borracha G', 'Osso de borracha para cachorros de grande porte, 220gr', 21, 18,1);
INSERT INTO item (id,name,description,value,quantity,category)
values (nextval('item_seq'),'Bebedouro Pequeno Azul', 'Bebedouro azul para raças pequenas', 5, 10,1);
INSERT INTO item (id,name,description,value,quantity,category)
values (nextval('item_seq'),'Bebedouro Médio Azul', 'Bebedouro azul para raças de médio porte', 8, 20,1);
INSERT INTO item (id,name,description,value,quantity,category)
values (nextval('item_seq'),'Bebedouro Grande Azul', 'Bebedouro azul para raças grandes', 12, 5,1);
INSERT INTO item (id,name,description,value,quantity,category)
values (nextval('item_seq'),'Shampoo para Cachorros e Gatos NEUTRO', 'Shampoo Neutro para cães e gatos, ideal para todos os tipos de pelo', 33, 19,1);

INSERT INTO services VALUES(nextval('services_seq'),'Banho Cachorros Pequenos', 'Banho para Cachorros de até 8KG', 40,true);
INSERT INTO services VALUES(nextval('services_seq'),'Banho Cachorros Médios', 'Banho para Cachorros apartir de 8KG e de até 15KG', 50,true);
INSERT INTO services VALUES(nextval('services_seq'),'Banho Cachorros Grande', 'Banho para Cachorros apartir de 16KG', 70,true);
INSERT INTO services VALUES(nextval('services_seq'),'Banho Gato', 'Banho para Gatos', 50,true);
INSERT INTO services VALUES(nextval('services_seq'),'Tosa Cachorros Pequenos', 'Tosa para Cachorros de até 8KG', 20,true);
INSERT INTO services VALUES(nextval('services_seq'),'Tosa Cachorros Médios', 'Tosa para Cachorros apartir de 8KG e de até 15KG', 30,true);
INSERT INTO services VALUES(nextval('services_seq'),'Tosa Cachorros Grande', 'Tosa para Cachorros apartir de 16KG', 35,true);

