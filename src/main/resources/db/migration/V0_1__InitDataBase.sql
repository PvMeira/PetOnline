create table ITEM (
    ID INT NOT NULL ,
    NAME VARCHAR(120) NOT NULL,
    DESCRIPTION VARCHAR(300),
    VALUE numeric(16) NOT NULL,
    QUANTITY numeric(10) NOT NULL,
    IMAGE VARCHAR(10485760)  ,
    CATEGORY numeric(1) NOT NULL,
    PRIMARY KEY (ID)
);

create table SERVICES (
    ID INT NOT NULL ,
    NAME VARCHAR(120) NOT NULL,
    DESCRIPTION VARCHAR(300),
    VALUE numeric(16) NOT NULL,
    IS_ACTIVE boolean not NULL,
    PRIMARY KEY (ID)
);

create table SERVICE_ORDER (
    ID INT NOT NULL ,
    SERVICES_ID INT NOT NULL,
    DISCOUNT_VALUE numeric(16),
    SERVICE_DATE date NOT NULL,
    OBSERVATION VARCHAR(300),
    FOREIGN KEY (SERVICES_ID) REFERENCES SERVICES (ID),
    PRIMARY KEY (ID)

);

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




create sequence ITEM_SEQ start 1;
create sequence SERVICES_SEQ start 1;
create sequence SERVICE_ORDER_SEQ start 1;


