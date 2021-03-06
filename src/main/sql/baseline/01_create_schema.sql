create table people (
  person_id         int not null auto_increment primary key,
  first_name        varchar(20),
  last_name         varchar(20),
  phone_number      varchar(20),
  address_line_1    varchar(20),
  address_line_2    varchar(100),
  city              varchar(20),
  state             varchar(2),
  zip_code          varchar(5)
);

create table states (
  state             varchar(2) not null primary key,
  description       varchar(20)
);

alter table people add constraint fk_people_state
  foreign key (state) references states(state);


create table schema_version (
  schema_version_id int not null auto_increment primary key,
  script_name       varchar(255) not null,
  date_applied      TIMESTAMP  not null default CURRENT_TIMESTAMP,
  username          varchar(32) not null default 'system'
);

create view people_view as (
  select
    person_id,
    first_name,
    last_name,
    phone_number,
    address_line_1,
    address_line_2,
    city,
    state,
    zip_code
  from people
);
