use app;

create table APP_USER
(
  USER_ID            BIGINT not null,
  USER_NAME          VARCHAR(36) not null,
  EMAIL              VARCHAR(50) not null,
  PASSWORD           VARCHAR(128) not null,
  ENABLED            BIT not null  
);

create table TICKETS
  (
    TICKET_ID       VARCHAR(6) not null,
    CREATED_BY      VARCHAR(25) not null,
    DESCRIPTION     VARCHAR(1000)
  );

alter table TICKETS
  add constraint TICKET_PK primary key (TICKET_ID);

  insert into TICKETS(TICKET_ID,CREATED_BY,DESCRIPTION) values(1,'admin','This is a testing ticket!');
  
alter table APP_USER
  add constraint APP_USER_PK primary key (USER_ID);

alter table APP_USER
  add constraint APP_USER_UK unique (USER_NAME);


create table APP_ROLE
(
  ROLE_ID   BIGINT not null,
  ROLE_NAME VARCHAR(30) not null
);  

alter table APP_ROLE
  add constraint APP_ROLE_PK primary key (ROLE_ID);

alter table APP_ROLE
  add constraint APP_ROLE_UK unique (ROLE_NAME);


create table USER_ROLE
(
  ID      BIGINT not null,
  USER_ID BIGINT not null,
  ROLE_ID BIGINT not null
);
  
alter table USER_ROLE
  add constraint USER_ROLE_PK primary key (ID);

alter table USER_ROLE
  add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);

alter table USER_ROLE
  add constraint USER_ROLE_FK1 foreign key (USER_ID)
  references APP_USER (USER_ID);

alter table USER_ROLE
  add constraint USER_ROLE_FK2 foreign key (ROLE_ID)
  references APP_ROLE (ROLE_ID);


-- Used by Spring Remember Me API.  
CREATE TABLE Persistent_Logins (

    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
    
);


insert into APP_USER(USER_ID, USER_NAME, EMAIL, PASSWORD, ENABLED)
values (2, 'peter', 'peter@logcorp.tech','$2a$10$WF0yYWhl4kZDEWbvC3CoMeUFOB2AK/Yr0oMSO9UA3hYEb86vGRSFy', 1);

insert into APP_USER(USER_ID, USER_NAME, EMAIL, PASSWORD, ENABLED)
values (1, 'admin', 'admin@support.logcorp.tech','$2a$10$RuJnBxAZMIK61Mr2Capsnu1SdeJIkM7s360lpCMS5HQY3ckQ1t8EO', 1);


insert into APP_ROLE (ROLE_ID, ROLE_NAME)
values (1, 'ROLE_ADMIN');

insert into APP_ROLE (ROLE_ID, ROLE_NAME)
values (2, 'ROLE_USER');


insert into USER_ROLE (ID, USER_ID, ROLE_ID)
values (1, 1, 1);

insert into USER_ROLE (ID, USER_ID, ROLE_ID)
values (2, 1, 2);

insert into USER_ROLE (ID, USER_ID, ROLE_ID)
values (3, 2, 2);