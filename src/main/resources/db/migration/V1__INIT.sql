
create table Book(
    id bigint primary key not null,
    name varchar (255),
    category_id bigint
);

create table Category (
    id bigint primary key not null,
    name varchar (255),
    category_id bigint
);