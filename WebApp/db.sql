drop database if EXISTS app;
create database app;
use app;

create table USER
(
  USER_ID            BIGINT not null,
  USER_NAME          VARCHAR(36) not null unique,
  EMAIL              VARCHAR(50) not null,
  PASSWORD           VARCHAR(128) not null,
  ENABLED            BIT not null,
  PRIMARY KEY (USER_ID)
);

create table TICKETS
  (
    TICKET_ID       int not null AUTO_INCREMENT,
    CREATED_BY		VARCHAR(36) not null,
    DESCRIPTION     VARCHAR(1000),
    PRIMARY KEY(TICKET_ID),
    foreign key (CREATED_BY) references USER (USER_NAME)
  );

create table ROLE
(
  ROLE_ID   BIGINT not null,
  ROLE_NAME VARCHAR(30) not null unique,
  PRIMARY KEY (ROLE_ID)
);  

create table USER_ROLE
(
  ID      BIGINT not null,
  USER_ID BIGINT not null unique,
  ROLE_ID BIGINT not null unique,
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
  FOREIGN KEY (ROLE_ID) REFERENCES ROLE(ROLE_ID)
);

-- Used by Spring Remember Me API.  
CREATE TABLE Persistent_Logins (

    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
);


insert into USER(USER_ID, USER_NAME, EMAIL, PASSWORD, ENABLED)
values (2, 'peter', 'peter@logcorp.tech','$2a$10$WF0yYWhl4kZDEWbvC3CoMeUFOB2AK/Yr0oMSO9UA3hYEb86vGRSFy', 1);
insert into USER(USER_ID, USER_NAME, EMAIL, PASSWORD, ENABLED)
values (1, 'admin', 'admin@support.logcorp.tech','$2a$10$RuJnBxAZMIK61Mr2Capsnu1SdeJIkM7s360lpCMS5HQY3ckQ1t8EO', 1);


insert into ROLE (ROLE_ID, ROLE_NAME) values (1, 'ROLE_ADMIN');
insert into ROLE (ROLE_ID, ROLE_NAME) values (2, 'ROLE_USER');


insert into USER_ROLE (ID, USER_ID, ROLE_ID) values (1, 1, 1);
insert into USER_ROLE (ID, USER_ID, ROLE_ID) values (3, 2, 2);

insert into TICKETS(CREATED_BY,DESCRIPTION) values('admin','This is a testing ticket!');
insert into TICKETS(CREATED_BY,DESCRIPTION) values('Peter','My current system used for developing does not work. HELP');
insert into TICKETS(CREATED_BY,DESCRIPTION) values('Peter','I dont know how, but i got sand in my computer');
insert into TICKETS(CREATED_BY,DESCRIPTION) values('admin','Migrate the webapplications logging functionality from Log4j to Logback');