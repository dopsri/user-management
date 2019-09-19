create table USER (
   USER_NAME varchar(255) not null,
   PASSWORD varchar(255) not null,
   LAST_NAME varchar(255) not null,
   FIRST_NAME varchar(255) not null,   
   EMAIL_ID varchar(255),
   primary key (USER_NAME),
   unique (EMAIL_ID)
);

create table ROLE (
   ROLE_NAME varchar(255) not null,
   primary key (ROLE_NAME)
);


create table USER_ROLES (
  USER_NAME varchar(255) not null,
  ROLE_NAME varchar(255) not null,
  primary key (USER_NAME, ROLE_NAME),
  key USER_NAME (USER_NAME),
  constraint user_role_user_name 
   FOREIGN KEY (USER_NAME) REFERENCES USER (USER_NAME) ON DELETE CASCADE ON UPDATE CASCADE,
  constraint user_role_role_name  
   FOREIGN KEY (ROLE_NAME) REFERENCES ROLE (ROLE_NAME) ON DELETE CASCADE ON UPDATE CASCADE
);