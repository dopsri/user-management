insert into USER (USER_NAME, PASSWORD, LAST_NAME, FIRST_NAME, EMAIL_ID) values ('dsr', 'dsr', 'Doppalapudi', 'Srinivasa Rao', 'srinud@gmail.com'); 
insert into ROLE (ROLE_NAME) values ('USER');
insert into ROLE (ROLE_NAME) values ('ADMIN');
insert into USER_ROLES (USER_NAME, ROLE_NAME) values ('dsr', 'USER');
insert into USER_ROLES (USER_NAME, ROLE_NAME) values ('dsr','ADMIN');