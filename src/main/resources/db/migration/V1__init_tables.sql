create table if not exists objects_for_rent(
    id INTEGER primary key auto_increment,
    object_name VARCHAR (50) not null,
    price DECIMAL not null,
    object_space INTEGER not null,
    description VARCHAR (100)
);

create table if not exists reservations(
    id INTEGER primary key auto_increment,
    days_booked INTEGER (50) not null,
    start_date DATE not null,
    end_date DATE not null,
    lessor_id INTEGER not null,
    tenant_id INTEGER not null,
    object_id INTEGER not null,
    cost INTEGER not null
);

alter table reservations
    add foreign key (object_id) references objects_for_rent (id);

create table if not exists users(
   id INTEGER primary key auto_increment,
   username VARCHAR (50) not null
);

