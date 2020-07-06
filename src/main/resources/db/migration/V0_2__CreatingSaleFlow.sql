create table CLIENT(
    CPF varchar(11) NOT NULL,
    NAME varchar(120) NOT NULL,
    PET_NAME varchar(120) NOT NULL,
    PHONE_NUMBER varchar(22) NOT NULL,
    ADDRESS  varchar(120),
    PRIMARY KEY (CPF)

);

create table SALE(
    ID numeric NOT NULL,
    CLIENT_CPF varchar(11) NOT NULL,
    SALE_DATE time NOT NULL,
    VALUE numeric(16) NOT NULL,
    OBSERVATION varchar(300) ,
    TYPE numeric(1) NOT NULL,

    FOREIGN KEY (CLIENT_CPF) REFERENCES CLIENT (CPF),
    PRIMARY KEY (ID)
);

create sequence SALE_SEQ start 1;

create table service_order_item (

    sale_id numeric NOT NULL,
    service_order_id INT NOT NULL,

    FOREIGN KEY (sale_id) REFERENCES SALE (ID),
    FOREIGN KEY (service_order_id) REFERENCES SERVICE_ORDER (ID),
    PRIMARY KEY (sale_id,service_order_id)
);

create table sale_item (

    sale_id numeric NOT NULL,
    item_id INT NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES SALE (ID),
    FOREIGN KEY (item_id) REFERENCES ITEM (ID),

    PRIMARY KEY (sale_id,item_id)
);



