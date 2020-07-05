create table ITEM (
    ID INT NOT NULL ,
    NAME VARCHAR(120) NOT NULL,
    DESCRIPTION VARCHAR(300),
    VALUE numeric(16) NOT NULL,
    QUANTITY numeric(10) NOT NULL,
    IMAGE VARCHAR(8000),
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

create sequence ITEM_SEQ start 1;
create sequence SERVICES_SEQ start 1;

