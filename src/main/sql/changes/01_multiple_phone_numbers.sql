create table phone_numbers (
  phone_number_id   int not null auto_increment primary key,
  phone_number      varchar(20),
  phone_type        char(1),
  person_id         int not null
);

create table phone_type (
  phone_type        char(1),
  description       varchar(20)
);

insert into phone_type (phone_type, description) values ('H', 'Home');

alter table phone_numbers add constraint fk_phone_numbers_people
  foreign key (person_id) references people(person_id);

insert into phone_numbers (phone_number, phone_type, person_id)
  select phone_number, 'H', person_id from people;

alter table people drop column phone_number;


insert into schema_version (script_name) values ('01_multiple_phone_numbers.sql');
