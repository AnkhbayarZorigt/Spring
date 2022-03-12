
create table cart(
                     id bigint primary key not null,
                     total_sum int
);

create table cart_item (
                          id bigint primary key not null,
                          name varchar (255),
                          price int,
                          vat int default 15,
                          price_including_vat int,
                          cart_id bigint
);